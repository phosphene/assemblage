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
tags:
  - cybernetics
  - deep-read
source:
  author: \"J. O. Wisdom\"
  year: 1951
---

# The Homeostat and Circular Causality

In a stable arrangement, the four magnets resist displacement.
Note: this is body text.

```clojure
{:close-read/id \"CR-WISDOM-U013\"
 :seme/firstness {:quality/phenomenon :resist-versus-diverge}
 :pheme/secondness {:encounter :ashby-homeostat-1948}}
```

> Ashby describes the homeostat in some detail. Four magnets are suspended in an electric field.
")

(deftest ast-two-tier-lexer-and-grammar-test
  (testing "Tier 1: Document lexer emits tagged tokens in a single stateless pass"
    (let [tokens (ast/lex-document sample-markdown)]
      (is (vector? tokens))
      (is (= [:tok/fence] (first tokens)))
      (is (some #(= [:tok/fence] %) (rest tokens)))
      ;; In body, Note: this is body text is emitted as [:tok/kv ...] without breaking lexer
      (is (some #(and (= (first %) :tok/kv) (= (second %) :Note)) tokens))))

  (testing "Tier 2: Spec sequence grammar conforms frontmatter and body cleanly"
    (let [tokens (ast/lex-document sample-markdown)]
      (is (s/valid? ::ast/document-stream tokens))
      (let [conformed (s/conform ::ast/document-stream tokens)]
        (is (contains? conformed :frontmatter))
        (is (contains? conformed :body))
        (let [fm-map (ast/hydrate-frontmatter (:frontmatter conformed))]
          (is (= "550e8400-e29b-41d4-a716-446655440000" (:registry.core/id fm-map)))
          (is (= "102248" (:cybernetics.topic/id fm-map)))
          (is (= "read-together" (:basis_tier fm-map)))
          (is (= ["cybernetics" "deep-read"] (:tags fm-map)))
          (is (= {:author "J. O. Wisdom" :year "1951"} (:source fm-map))))))))

(deftest parse-markdown-with-frontmatter-test
  (testing "Parsing markdown produces CommonMark AST with out-of-band metadata"
    (let [doc (ast/parse-markdown sample-markdown)
          metadata (ast/doc-metadata doc)]
      (is (map? doc))
      (is (= :doc (:type doc)))
      (is (vector? (:content doc)))

      ;; Out-of-band metadata validation
      (is (map? metadata))
      (is (= "550e8400-e29b-41d4-a716-446655440000" (:registry.core/id metadata)))
      (is (= "102248" (:cybernetics.topic/id metadata)))
      (is (= "read-together" (:basis_tier metadata)))
      (is (= ["cybernetics" "deep-read"] (:tags metadata)))
      (is (= {:author "J. O. Wisdom" :year "1951"} (:source metadata)))

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
