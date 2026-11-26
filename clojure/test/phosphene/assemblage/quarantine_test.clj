(ns phosphene.assemblage.quarantine-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure.spec.alpha :as s]
            [phosphene.assemblage.ast :as ast]
            [phosphene.assemblage.spec :as aspec]))

(def sample-markdown
  "# Cybernetics Header\n\nThis is a paragraph with **bold** text.\n\n```clojure\n(+ 1 2)\n```\n")

(deftest test-quarantine-protocol-lifting
  (testing "Raw external nextjournal AST contains unqualified keys (Mode-S)"
    (let [raw-doc (ast/parse-markdown sample-markdown)]
      (is (= :doc (:type raw-doc)))
      (is (vector? (:content raw-doc)))
      (is (nil? (:ast.node/type raw-doc)))))

  (testing "qualify-ast lifts unqualified keys into sovereign qualified senses (Mode-D)"
    (let [raw-doc (ast/parse-markdown sample-markdown)
          qdoc (ast/qualify-ast raw-doc)]
      (is (= :doc (:ast.node/type qdoc)))
      (is (vector? (:ast.node/content qdoc)))
      ;; Heading node lifted
      (let [heading (first (:ast.node/content qdoc))]
        (is (= :heading (:ast.node/type heading)))
        (is (= 1 (:ast.heading/level heading)))
        (is (vector? (:ast.node/content heading)))
        (is (= :text (:ast.node/type (first (:ast.node/content heading)))))
        (is (= "Cybernetics Header" (:ast.text/content (first (:ast.node/content heading))))))
      ;; Code node lifted
      (let [code (last (:ast.node/content qdoc))]
        (is (= :code (:ast.node/type code)))
        (is (= "clojure" (:ast.code/language code)))
        (is (= "(+ 1 2)\n" (:ast.text/content (first (:ast.node/content code))))))))

  (testing "Universal (s/keys) validates qualified Mode-D AST"
    (let [raw-doc (ast/parse-markdown sample-markdown)
          qdoc (ast/qualify-ast raw-doc)
          heading (first (:ast.node/content qdoc))]
      ;; Mode-D node satisfies universal open-world (s/keys)
      (is (s/valid? (s/keys) heading))
      (is (s/valid? :ast.node/heading-node heading))
      (is (s/valid? :ast/doc qdoc))))

  (testing "Polymorphic AST extractors work uniformly across Mode-S and Mode-D"
    (let [raw-doc (ast/parse-markdown sample-markdown)
          qdoc (ast/qualify-ast raw-doc)]
      ;; Both respond identically to query helpers
      (is (= 1 (count (ast/headings raw-doc))))
      (is (= 1 (count (ast/headings qdoc))))
      (is (= 1 (count (ast/code-blocks raw-doc "clojure"))))
      (is (= 1 (count (ast/code-blocks qdoc "clojure")))))))
