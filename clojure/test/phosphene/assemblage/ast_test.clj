(ns phosphene.assemblage.ast-test
  (:require [clojure.spec.alpha :as s]
            [clojure.test :refer [deftest is testing]]
            [phosphene.assemblage.ast :as ast]
            [phosphene.assemblage.spec :as spec]))

(def sample-markdown
  "---
registry.core/id: 550e8400-e29b-41d4-a716-446655440000
cybernetics.topic/id: 102248
title: \"The Homeostat and Circular Causality\"
basis_tier: read-together
---

# The Homeostat and Circular Causality

In a stable arrangement, the four magnets resist displacement.

```clojure
{:close-read/id \"CR-WISDOM-U013\"
 :seme/firstness {:quality/phenomenon :resist-versus-diverge}
 :pheme/secondness {:encounter :ashby-homeostat-1948}}
```

> Ashby describes the homeostat in some detail. Four magnets are suspended in an electric field.
")

(deftest ast-parsing-and-metadata-anchoring-test
  (testing "In-process markdown parsing and out-of-band frontmatter anchoring"
    (let [doc (ast/parse-markdown sample-markdown)
          metadata (ast/doc-metadata doc)]
      ;; Structural AST properties
      (is (= :doc (:type doc)))
      (is (= "The Homeostat and Circular Causality" (:title doc)))
      (is (vector? (:content doc)))
      (is (s/valid? :ast/doc doc))

      ;; Out-of-band metadata preservation (Hickey/Tellman invariant)
      (is (map? metadata))
      (is (= "550e8400-e29b-41d4-a716-446655440000" (:registry.core/id metadata)))
      (is (= "102248" (:cybernetics.topic/id metadata)))
      (is (= "read-together" (:basis_tier metadata)))

      ;; Structural equality invariant: two identical body ASTs with different metadata are equal
      (let [doc-copy (with-meta doc {:different "meta"})]
        (is (= doc doc-copy))
        (is (not= (meta doc) (meta doc-copy)))))))

(deftest ast-query-helpers-test
  (testing "Extracting headings and code blocks from AST"
    (let [doc (ast/parse-markdown sample-markdown)
          headings (ast/headings doc)
          code-blocks (ast/code-blocks doc "clojure")]
      (is (= 1 (count headings)))
      (is (= 1 (:heading-level (first headings))))
      (is (= 1 (count code-blocks)))
      (is (= "clojure" (:language (first code-blocks)))))))
