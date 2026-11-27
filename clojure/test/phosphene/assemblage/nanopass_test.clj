(ns phosphene.assemblage.nanopass-test
  (:require [clojure.test :refer [deftest is testing]]
            [phosphene.assemblage.ast :as ast]
            [phosphene.assemblage.nanopass :as nano]))

(deftest test-monotonic-subtree-rewriting
  (testing "Surgically updating an AST node via zipper without touching siblings"
    (let [md-text "# Title\n\nFirst paragraph.\n\n```clojure\n{:status :draft}\n```\n\nFinal prose."
          doc-ast (ast/parse-markdown md-text)
          updated-ast (nano/update-node
                       doc-ast
                       (fn [node] (= (:type node) :code))
                       (fn [node] (assoc node :language "edn" :text "{:status :verified}")))
          code-nodes (ast/query-nodes updated-ast (fn [n] (= (:type n) :code)))]
      (is (= 1 (count code-nodes)))
      (is (= "edn" (:language (first code-nodes))))
      (is (= "{:status :verified}" (:text (first code-nodes))))
      ;; Paragraphs preserved
      (let [paras (ast/query-nodes updated-ast (fn [n] (= (:type n) :paragraph)))]
        (is (= 2 (count paras)))))))
