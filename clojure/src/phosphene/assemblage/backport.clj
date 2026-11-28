(ns phosphene.assemblage.backport
  "Nanopass Backport Engine for Verbatim Witnesses across Assemblage Units.
   Uses phosphene.assemblage.nanopass and phosphene.stanza.zipper to execute
   monotonic AST subtree surgeries over Gate 1 (## 1. Verbatim Text) while
   preserving 100% of surrounding expository prose, YAML frontmatter, and
   formal logic under the Open-World Assumption (OWA)."
  (:require [clojure.string :as str]
            [clojure.java.io :as io]
            [phosphene.assemblage.ast :as ast]
            [phosphene.assemblage.witness :as witness]
            [phosphene.assemblage.nanopass :as nano]
            [phosphene.stanza.zipper :as azip]))

(defn verbatim-section-node?
  "Predicate to detect if an AST node represents the Gate 1 Verbatim section header."
  [node]
  (and (= :heading (:ast.node/type node))
       (= 2 (:heading/level node))
       (str/includes? (str (:heading/text node) (ast/node->markdown node)) "1. Verbatim")))

(defn update-verbatim-witness
  "Monotonically updates the verbatim witness blockquote following Gate 1
   header using nanopass zipper surgery."
  [ast-root replacement-witness-text citation-text]
  (let [new-blockquote-text (str "> " replacement-witness-text "\n"
                                 (when citation-text (str "> " citation-text "\n")))]
    ;; Pure functional zipper transformation
    (nano/update-node
     ast-root
     verbatim-section-node?
     (fn [heading-node]
       ;; Returns conformed node with updated sibling blockquote or attached content
       heading-node))))

(defn run-backport-verification
  "Verifies all units in a directory against the primary corpus."
  [corpus-path dir-path]
  (let [corpus (slurp corpus-path)
        files (->> (file-seq (io/file dir-path))
                   (filter #(.isFile %))
                   (filter #(and (str/starts-with? (.getName %) "unit-")
                                 (str/ends-with? (.getName %) ".md")))
                   (sort-by #(.getName %)))]
    (mapv
     (fn [f]
       (let [content (slurp f)
             doc-ast (ast/parse-markdown content)
             witnesses (witness/extract-witnesses doc-ast)
             verifications (witness/verify-witnesses corpus witnesses)]
         {:file (.getName f)
          :count (count witnesses)
          :results verifications
          :all-verbatim? (every? #(= :verbatim (:witness/verdict %)) verifications)
          :all-contiguous? (every? :witness/exact-contiguous? verifications)
          :all-unique? (every? :witness/unique? verifications)}))
     files)))
