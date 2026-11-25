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

(defn extract-code-edn
  "Extract and read the first clojure/edn code block from the AST."
  [ast]
  (let [blocks (or (seq (ast/code-blocks ast "clojure"))
                   (seq (ast/code-blocks ast "edn"))
                   (seq (ast/code-blocks ast)))]
    (when-let [first-block (first blocks)]
      (let [text-content (->> (:content first-block)
                              (filter (fn [n] (= (:type n) :text)))
                              (map :text)
                              str/join)]
        (try
          (edn/read-string text-content)
          (catch Exception _ nil))))))

(defn project-close-read
  "Lens morphism projecting a parsed Markdown AST into a conformed Semiotic Close-Read Triad.
   Carries document out-of-band metadata into the conformed result."
  [ast-doc]
  (let [doc-meta (ast/doc-metadata ast-doc)
        edn-data (extract-code-edn ast-doc)]
    (when (map? edn-data)
      (with-meta
        (merge
         {:close-read/id (or (:close-read/id edn-data)
                             (:id doc-meta)
                             (:registry.core/id doc-meta))
          :close-read/title (or (:close-read/title edn-data)
                                (:title doc-meta)
                                (:title ast-doc))
          :close-read/source (:source doc-meta)}
         edn-data)
        doc-meta))))

;; --- Lens 2: Topological Assemblage Morphism (:convention/topological-assemblage) ---

(defn project-assemblage-elements
  "Lens morphism projecting a close-read AST into discrete topological nodes and relations.
   Extracts named entities from :term/definitions and delome state rules."
  [ast-doc]
  (let [triad (project-close-read ast-doc)]
    (when triad
      (let [unit-id (:close-read/id triad)
            terms (:term/definitions triad)
            anchors (get-in triad [:pheme/secondness :historical-anchors] [])
            nodes (into {}
                        (concat
                         [[(keyword (str "unit/" unit-id))
                           {:id (keyword (str "unit/" unit-id))
                            :traits (set (map (fn [t] (if (map? t) (:term/name t) t)) terms))
                            :level 1}]]
                         (map (fn [a]
                                [a {:id a :traits #{:historical-anchor} :level 0}])
                              anchors)))
            edges (set
                   (map (fn [a]
                          [(keyword (str "unit/" unit-id)) a :historical-lineage])
                        anchors))]
        {:nodes nodes
         :edges edges
         :hierarchy {}}))))

;; --- Lens 3: Verbatim Witness Morphism (:convention/epistemic-witness) ---

(defn blockquotes
  "Return all blockquote nodes from the AST."
  [ast]
  (ast/query-nodes ast (fn [node] (= (:type node) :blockquote))))

(defn project-verbatim-witnesses
  "Extract verbatim witness passages from blockquotes in the document."
  [ast-doc]
  (let [quotes (blockquotes ast-doc)]
    (mapv (fn [bq]
            (let [text (->> (ast/query-nodes bq (fn [n] (= (:type n) :text)))
                            (map :text)
                            str/join
                            str/trim)]
              {:witness/text text
               :witness/char-count (count text)
               :witness/zero-ellipses? (not (str/includes? text "..."))}))
          quotes)))
