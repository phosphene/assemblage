(ns phosphene.assemblage.ast
  "In-process Markdown AST parsing using nextjournal/markdown, with out-of-band
   YAML frontmatter metadata anchoring (Hickey/Tellman semantic identity).
   
   Adheres to Phosphene Initiative I-012 (T-637) and Registry Architecture:
   - Frontmatter is attached out-of-band via `with-meta`, preserving clean algebraic
     equality (= doc-a doc-b) on the document's structural AST.
   - Nodes are immutable Clojure maps and vectors conforming to clojure.spec.alpha."
  (:require [clojure.string :as str]
            [nextjournal.markdown :as md]))

;; --- Frontmatter Splitting & Anchor Extraction ---

(defn parse-yaml-frontmatter
  "Extract simple key-value pairs from YAML frontmatter lines.
   Converts namespaced keys like 'registry.core/id' or 'uri' into keywords."
  [frontmatter-str]
  (reduce
   (fn [acc line]
     (let [trimmed (str/trim line)]
       (if (or (empty? trimmed) (str/starts-with? trimmed "#"))
         acc
         (if-let [[_ k v] (re-matches #"^([a-zA-Z0-9_\-\./]+)\s*:\s*(.*)$" trimmed)]
           (let [k-kw (if (str/includes? k "/")
                        (let [[ns-part name-part] (str/split k #"/" 2)]
                          (keyword ns-part name-part))
                        (keyword k))
                 v-clean (-> v
                             str/trim
                             (str/replace #"^[\"']|[\"']$" ""))]
             (assoc acc k-kw v-clean))
           acc))))
   {}
   (str/split-lines frontmatter-str)))

(defn split-frontmatter
  "Split raw markdown content into optional frontmatter string and body string."
  [raw-str]
  (let [normalized (str/replace raw-str #"\r\n" "\n")]
    (if (str/starts-with? normalized "---\n")
      (let [after-first (subs normalized 4)]
        (if-let [idx (str/index-of after-first "\n---\n")]
          {:frontmatter (subs after-first 0 idx)
           :body (subs after-first (+ idx 5))}
          {:frontmatter nil :body normalized}))
      {:frontmatter nil :body normalized})))

;; --- Primary AST Ingestion with Out-of-Band Metadata ---

(defn parse-markdown
  "Parse markdown string into nextjournal CommonMark AST in-process.
   Attaches parsed frontmatter out-of-band via `with-meta`."
  [raw-str]
  (let [{:keys [frontmatter body]} (split-frontmatter raw-str)
        ast (md/parse body)
        meta-map (if frontmatter
                   (parse-yaml-frontmatter frontmatter)
                   {})]
    (with-meta ast (assoc meta-map :source/raw-frontmatter frontmatter))))

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
