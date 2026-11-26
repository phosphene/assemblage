(ns phosphene.assemblage.ast
  "In-process Markdown AST parsing using nextjournal/markdown, with out-of-band
   YAML frontmatter metadata anchoring (Hickey/Tellman semantic identity).
   
   Adheres to Phosphene Initiative I-012 (T-637), T-430, and Registry Architecture:
   - Frontmatter is attached out-of-band via `with-meta`, preserving clean algebraic
     equality (= doc-a doc-b) on the document's structural AST.
   - Two-Tier Boundary Lexer (Pattern 1 & T-430):
       Tier 1: Minimal, stateless boundary regex scanner emitting flat tagged tokens.
       Tier 2: clojure.spec.alpha sequence grammar (s/cat, s/alt, s/*) resolving
               contextual structure and conforming frontmatter metadata."
  (:require [clojure.spec.alpha :as s]
            [clojure.string :as str]
            [nextjournal.markdown :as md]))

;; --- Tier 1: Stateless Boundary Lexer ---

(def ^:private line-re
  #"^(?:(?<fence>---)\s*|(?<blank>\s*)|(?<h2title>##\s+.*)|(?<h1title>#\s+.*)|(?<bullet>\s*-\s+.*)|(?<subkey>\s{2,}[a-zA-Z0-9_\-\./]+:\s*.*)|(?<key>[a-zA-Z0-9_\-\./]+:\s*.*)|(?<text>.*))$")

(defn- parse-kw [k]
  (let [trimmed (str/trim k)]
    (if (str/includes? trimmed "/")
      (let [[ns-part name-part] (str/split trimmed #"/" 2)]
        (keyword ns-part name-part))
      (keyword trimmed))))

(defn- clean-val [v]
  (let [trimmed (str/trim (or v ""))]
    (str/replace trimmed #"^[\"']|[\"']$" "")))

(defn- parse-kv-str [s]
  (let [idx (str/index-of s ":")]
    [(subs s 0 idx) (subs s (inc idx))]))

(defn lex-line
  "Stateless, single-line token scanner. Emits tagged token tuples:
   [:tok/fence], [:tok/blank], [:tok/h1 ...], [:tok/h2 ...], [:tok/bullet ...],
   [:tok/sub-kv k v], [:tok/section-key k], [:tok/kv k v], or [:tok/text ...]."
  [line]
  (let [m (re-matcher line-re line)]
    (if (.matches m)
      (cond
        (.group m "fence")   [:tok/fence]
        (.group m "blank")   [:tok/blank]
        (.group m "h2title") [:tok/h2 (str/trim (subs line 2))]
        (.group m "h1title") [:tok/h1 (str/trim (subs line 1))]
        (.group m "bullet")  (let [idx (str/index-of line "-")]
                               [:tok/bullet (clean-val (subs line (inc idx)))])
        (.group m "subkey")  (let [[k v] (parse-kv-str (str/trim line))]
                               [:tok/sub-kv (parse-kw k) (clean-val v)])
        (.group m "key")     (let [[k v] (parse-kv-str (str/trim line))
                                   val (clean-val v)]
                               (if (empty? val)
                                 [:tok/section-key (parse-kw k)]
                                 [:tok/kv (parse-kw k) val]))
        :else                [:tok/text line])
      [:tok/text line])))

(defn lex-document
  "Transform a raw markdown string into a flat vector of tagged tokens."
  [raw-text]
  (let [lines (str/split (str/replace (or raw-text "") #"\r\n" "\n") #"\n")]
    (mapv lex-line lines)))

;; --- Tier 2: Sequence Grammar over Tokens (clojure.spec.alpha) ---

(s/def :token/kv (s/and vector? #(= (first %) :tok/kv)))
(s/def :token/section-key (s/and vector? #(= (first %) :tok/section-key)))
(s/def :token/bullet (s/and vector? #(= (first %) :tok/bullet)))
(s/def :token/sub-kv (s/and vector? #(= (first %) :tok/sub-kv)))
(s/def :token/fence (s/and vector? #(= (first %) :tok/fence)))
(s/def :token/blank (s/and vector? #(= (first %) :tok/blank)))
(s/def :token/h1 (s/and vector? #(= (first %) :tok/h1)))
(s/def :token/h2 (s/and vector? #(= (first %) :tok/h2)))
(s/def :token/text (s/and vector? #(= (first %) :tok/text)))

(s/def ::fm-list-block
  (s/cat :head :token/section-key
         :items (s/+ :token/bullet)))

(s/def ::fm-map-block
  (s/cat :head :token/section-key
         :entries (s/+ :token/sub-kv)))

(s/def ::fm-entry
  (s/alt :scalar :token/kv
         :list-block ::fm-list-block
         :map-block ::fm-map-block
         :blank :token/blank))

(s/def ::frontmatter
  (s/cat :open :token/fence
         :entries (s/* ::fm-entry)
         :close :token/fence))

(s/def ::body-token
  (s/alt :h1 :token/h1
         :h2 :token/h2
         :hr :token/fence
         :prose (s/alt :text :token/text
                       :kv-as-text :token/kv
                       :bullet-as-text :token/bullet
                       :sub-kv-as-text :token/sub-kv
                       :section-key-as-text :token/section-key)
         :blank :token/blank))

(s/def ::document-stream
  (s/cat :frontmatter (s/? ::frontmatter)
         :body (s/* ::body-token)))

(defn hydrate-frontmatter
  "Transform conformed ::frontmatter data into a pure Clojure metadata map."
  [conformed-fm]
  (when conformed-fm
    (reduce (fn [acc entry]
              (let [[kind data] entry]
                (case kind
                  :scalar (let [[_ k v] data]
                            (assoc acc k v))
                  :list-block (let [{:keys [head items]} data
                                    [_ k] head
                                    vals (mapv (fn [item] (second item)) items)]
                                (assoc acc k vals))
                  :map-block (let [{:keys [head entries]} data
                                   [_ k] head
                                   sub-map (reduce (fn [m sub-entry]
                                                     (let [[_ sub-k sub-v] sub-entry]
                                                       (assoc m sub-k sub-v)))
                                                   {}
                                                   entries)]
                               (assoc acc k sub-map))
                  :blank acc)))
            {}
            (:entries conformed-fm))))

(defn parse-document-stream
  "Run the two-tier boundary lexer and spec grammar across raw text.
   Returns {:metadata {...} :has-frontmatter? boolean}."
  [raw-text]
  (let [tokens (lex-document raw-text)]
    (if (s/valid? ::document-stream tokens)
      (let [conformed (s/conform ::document-stream tokens)
            fm-data (:frontmatter conformed)]
        {:metadata (if fm-data (hydrate-frontmatter fm-data) {})
         :has-frontmatter? (some? fm-data)})
      {:metadata {}
       :has-frontmatter? false})))

;; --- Primary AST Ingestion with Out-of-Band Metadata ---

(defn split-frontmatter
  "Split raw markdown content into optional frontmatter string and body string."
  [raw-str]
  (let [normalized (str/replace (or raw-str "") #"\r\n" "\n")]
    (if (str/starts-with? normalized "---\n")
      (let [after-first (subs normalized 4)]
        (if-let [idx (str/index-of after-first "\n---\n")]
          {:frontmatter (subs after-first 0 idx)
           :body (subs after-first (+ idx 5))}
          {:frontmatter nil :body normalized}))
      {:frontmatter nil :body normalized})))

(defn parse-markdown
  "Parse markdown string into nextjournal CommonMark AST in-process.
   Attaches parsed frontmatter out-of-band via `with-meta` using the
   Two-Tier Boundary Lexer (T-430)."
  [raw-str]
  (let [{:keys [frontmatter body]} (split-frontmatter raw-str)
        ast (md/parse body)
        stream-parsed (parse-document-stream raw-str)
        meta-map (assoc (:metadata stream-parsed) :source/raw-frontmatter frontmatter)]
    (with-meta ast meta-map)))

(defn doc-metadata
  "Retrieve out-of-band metadata attached to a parsed markdown AST."
  [ast-doc]
  (meta ast-doc))

(defn query-nodes
  "Traverse AST and return all nodes satisfying (pred node)."
  [ast pred]
  (letfn [(walk-node [node acc]
            (let [new-acc (if (pred node) (conj acc node) acc)]
              (reduce (fn [inner-acc child]
                        (if (map? child)
                          (walk-node child inner-acc)
                          inner-acc))
                      new-acc
                      (:content node))))]
    (walk-node ast [])))

(defn code-blocks
  "Return all code block nodes from the AST, optionally filtered by language."
  ([ast]
   (query-nodes ast (fn [node] (= (:type node) :code))))
  ([ast lang]
   (query-nodes ast (fn [node]
                      (and (= (:type node) :code)
                           (= (:language node) lang))))))

(defn headings
  "Return all heading nodes from the AST, optionally filtered by level."
  ([ast]
   (query-nodes ast (fn [node] (= (:type node) :heading))))
  ([ast level]
   (query-nodes ast (fn [node]
                      (and (= (:type node) :heading)
                           (= (:heading-level node) level))))))
