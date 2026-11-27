(ns phosphene.assemblage.lens
  "Morphic lenses: late-bound selection sets projecting ASTs into conformed EDN
   projections as specified in the Phosphene Registry Architecture.
   
   Adheres to the Open-World Assumption (OWA):
   - Selection over Validation: Documents exist in a superposition of conventions.
   - Morphisms (f: AST -> conformed EDN) project what is necessary for a given
     research inquiry without complecting structural schema with rigid global types."
  (:require [clojure.edn :as edn]
            [clojure.spec.alpha :as s]
            [clojure.string :as str]
            [phosphene.assemblage.ast :as ast]))

;; --- Lens 1: Semiotic Close-Read Triad Projection (:convention/close-read) ---

(defn read-edn-forms
  "Reads all EDN forms from a string stream until EOF."
  [s]
  (let [pbr (java.io.PushbackReader. (java.io.StringReader. s))
        sentinel (Object.)]
    (loop [acc []]
      (let [form (edn/read {:eof sentinel} pbr)]
        (if (identical? form sentinel)
          acc
          (recur (conj acc form)))))))

(defn extract-code-edn
  "Extract and merge all clojure/edn code blocks and forms from the Markdown AST.
   Supports decomposing semiotic triads and conceptual models into atomic S-expressions
   distributed across Markdown sections (T-437).
   If read fails, returns a typed anomaly map rather than failing silently (E2)."
  [ast]
  (let [blocks (concat (ast/code-blocks ast "clojure")
                       (ast/code-blocks ast "edn"))
        target-blocks (if (seq blocks)
                        blocks
                        (ast/code-blocks ast))]
    (when (seq target-blocks)
      (reduce (fn [acc b]
                (let [text-content (->> (:content b)
                                        (filter (fn [n] (= (:type n) :text)))
                                        (map :text)
                                        str/join)]
                  (try
                    (let [forms (read-edn-forms text-content)]
                      (reduce (fn [m form]
                                (cond
                                  (map? form) (merge m form)
                                  :else m))
                              acc
                              forms))
                    (catch Exception e
                      (reduced {:anomaly/type :edn-parse-failure
                                :anomaly/message (ex-message e)})))))
              {}
              target-blocks))))

(defn project-close-read
  "Lens morphism projecting a parsed Markdown AST into a conformed Semiotic Close-Read Triad.
   Adheres to T-435 (Presence vs. Sense):
   - Invariant Sense: validated universally via bare (s/keys) open-world gate.
   - Point-of-Use Presence: extracts required keys via local destructuring.
   Provenance precedence: canonical frontmatter metadata strictly anchors the projection (E3)."
  [ast-doc]
  (let [doc-meta (ast/doc-metadata ast-doc)
        edn-data (extract-code-edn ast-doc)]
    (when (map? edn-data)
      (if (:anomaly/type edn-data)
        ;; Return conformed anomaly projection
        (with-meta {:anomaly/lens :close-read
                    :anomaly/detail edn-data}
                   doc-meta)
        ;; Local point-of-use destructuring for canonical anchoring
        (let [{:close-read/keys [id title source] :as _triad-data} edn-data
              {:keys [id title source]
               reg-id :registry.core/id} doc-meta
              canonical-id (or id (:close-read/id edn-data) reg-id)
              canonical-title (or title (:title ast-doc) (:close-read/title edn-data))
              canonical-source (or source (:close-read/source edn-data))
              merged (merge edn-data
                            (cond-> {}
                              canonical-id (assoc :close-read/id canonical-id)
                              canonical-title (assoc :close-read/title canonical-title)
                              canonical-source (assoc :close-read/source canonical-source)))]
          ;; Universal Open-World Gate: assert all present qualified attributes satisfy their global Senses
          (when-not (s/valid? (s/keys) merged)
            (throw (ex-info "Semiotic Close-Read Sense violation"
                            {:explain (s/explain-data (s/keys) merged)})))
          (with-meta merged doc-meta))))))

;; --- Lens 2: Topological Assemblage Morphism (:convention/topological-assemblage) ---

(defn project-assemblage-elements
  "Lens morphism projecting a close-read AST into discrete topological nodes and relations.
   Adheres to T-435:
   - Destructures only required Presence ({:close-read/keys [id] terms :term/definitions})
   - Extracts anchors polymorphically across qualified and unqualified keys
   - Emits both qualified attributes and unqualified keys for compatibility,
     validated universally via bare (s/keys)."
  [ast-doc]
  (let [triad (project-close-read ast-doc)]
    (when (and triad (not (:anomaly/lens triad)))
      (let [{:close-read/keys [id]
             terms :term/definitions} triad
            secondness (:pheme/secondness triad)
            anchors (or (:pheme/historical-anchors secondness)
                        (:historical-anchors secondness)
                        [])
            unit-kw (keyword (str "unit/" id))
            unit-traits (set (map (fn [t] (if (map? t) (or (:term/name t) (:name t)) t)) terms))
            unit-node {:id unit-kw
                       :node/id unit-kw
                       :traits unit-traits
                       :node/traits unit-traits
                       :level 1
                       :node/level 1}
            anchor-nodes (map (fn [a]
                                [a {:id a
                                    :node/id a
                                    :traits #{:historical-anchor}
                                    :node/traits #{:historical-anchor}
                                    :level 0
                                    :node/level 0}])
                              anchors)
            nodes (into {}
                        (concat [[unit-kw unit-node]]
                                anchor-nodes))
            edges (set (map (fn [a] [unit-kw a :historical-lineage]) anchors))
            elements {:nodes nodes
                      :assemblage/nodes nodes
                      :edges edges
                      :assemblage/edges edges
                      :hierarchy {}
                      :assemblage/hierarchy {}}]
        ;; Universal Open-World Gate: assert emitted assemblage structure satisfies global Senses
        (when-not (s/valid? (s/keys) elements)
          (throw (ex-info "Topological Assemblage Elements Sense violation"
                          {:explain (s/explain-data (s/keys) elements)})))
        elements))))

;; --- Lens 3: Verbatim Witness Morphism (:convention/epistemic-witness) ---

(defn blockquotes
  "Return all blockquote nodes from the AST."
  [ast]
  (ast/query-nodes ast (fn [node] (= (:type node) :blockquote))))

(defn- extract-clean-witness-text
  "Extract the verbatim text from a blockquote node, stripping trailing citation
   lines like '(§1, p. 1)' or '(Wisdom 1951, p. 5)' and bounding quotation marks."
  [bq-node]
  (let [raw-lines (->> (ast/query-nodes bq-node (fn [n] (= (:type n) :text)))
                       (map :text)
                       (mapcat #(str/split % #"\n"))
                       (map str/trim)
                       (remove str/blank?))
        witness-lines (remove (fn [l]
                                (or (re-find #"^\([§p\d]" l)
                                    (re-find #"^\(Wisdom" l)
                                    (re-find #"^\*Source:" l)))
                              raw-lines)
        joined (str/join " " witness-lines)]
    (cond
      (and (str/starts-with? joined "\"") (str/ends-with? joined "\"") (> (count joined) 1))
      (subs joined 1 (dec (count joined)))
      (and (str/starts-with? joined "'") (str/ends-with? joined "'") (> (count joined) 1))
      (subs joined 1 (dec (count joined)))
      :else joined)))

(defn project-verbatim-witnesses
  "Extract clean verbatim witness passages from blockquotes in the document.
   Strips inline citation annotations so the emitted witness matches primary text (T-438 / Req 3)."
  [ast-doc]
  (let [quotes (blockquotes ast-doc)]
    (mapv (fn [bq]
            (let [text (extract-clean-witness-text bq)]
              {:witness/text text
               :witness/char-count (count text)
               :witness/zero-ellipses? (not (str/includes? text "..."))}))
          quotes)))

;; --- Lens 4: Genealogical Network Morphism (:convention/genealogy) ---

(defn project-genealogy-metadata
  "Extract structured genealogical metadata from EDN code blocks in the document AST."
  [ast-doc]
  (let [blocks (concat (ast/code-blocks ast-doc "clojure")
                       (ast/code-blocks ast-doc "edn"))
        edn-forms (mapcat (fn [b]
                            (let [raw-text (or (:text b)
                                               (->> (:content b)
                                                    (filter (fn [n] (= (:type n) :text)))
                                                    (map :text)
                                                    str/join))]
                              (if (str/blank? raw-text)
                                []
                                (try (read-edn-forms raw-text)
                                     (catch Exception _ [])))))
                          blocks)]
    (first (filter (fn [form] (and (map? form) (:genealogy/id form))) edn-forms))))

(defn project-genealogy-network
  "Projects an entire collection of parsed AST documents into a unified genealogical graph."
  [ast-docs]
  (let [units (keep project-genealogy-metadata ast-docs)]
    {:network/units (into {} (map (juxt :genealogy/id identity) units))
     :network/roots (filter (fn [u] (or (= :dual-genesis (:genealogy/epistemic-status u))
                                        (empty? (:genealogy/precursors u))))
                            units)
     :network/count (count units)}))
