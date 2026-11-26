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

(deftest ast-token-vocabulary-test
  (testing "Token vocabulary defined as sovereign namespaced senses"
    (is (s/valid? :phosphene.assemblage.ast.tok/fence [:tok/fence]))
    (is (s/valid? :phosphene.assemblage.ast.tok/blank [:tok/blank]))
    (is (s/valid? :phosphene.assemblage.ast.tok/h1 [:tok/h1 "Heading"]))
    (is (s/valid? :phosphene.assemblage.ast.tok/h2 [:tok/h2 "Subheading"]))
    (is (s/valid? :phosphene.assemblage.ast.tok/bullet [:tok/bullet "item"]))
    (is (s/valid? :phosphene.assemblage.ast.tok/sub-kv [:tok/sub-kv :author "Wisdom"]))
    (is (s/valid? :phosphene.assemblage.ast.tok/section-key [:tok/section-key :tags]))
    (is (s/valid? :phosphene.assemblage.ast.tok/kv [:tok/kv :uri "registry://foo"]))
    (is (s/valid? :phosphene.assemblage.ast.tok/text [:tok/text "prose line"]))

    ;; Structural type rejections
    (is (not (s/valid? :phosphene.assemblage.ast.tok/kv [:tok/kv "not-a-kw" "val"])))
    (is (not (s/valid? :phosphene.assemblage.ast.tok/fence [:tok/fence "extra"])))
    (is (not (s/valid? :phosphene.assemblage.ast.tok/bullet [:tok/bullet 123])))))

(deftest ast-two-tier-lexer-and-grammar-test
  (testing "Tier 1: Document lexer emits tagged tokens in a single stateless pass"
    (let [tokens (ast/lex-document sample-markdown)]
      (is (vector? tokens))
      (is (= [:tok/fence] (first tokens)))
      (is (some #(= [:tok/fence] %) (rest tokens)))
      ;; In body, Note: this is body text is emitted as [:tok/kv ...] without breaking lexer
      (is (some #(and (= (first %) :tok/kv) (= (second %) :Note)) tokens))
      ;; Every token conforms to ::ast/token
      (is (s/valid? ::ast/token-stream tokens))))

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

(deftest ast-context-disambiguation-test
  (testing "A key: val line after closing fence is conformed as body prose, not metadata"
    (let [doc-text "---\ntitle: Real Title\n---\nkey: val in body\n"
          tokens (ast/lex-document doc-text)
          conformed (s/conform ::ast/document-stream tokens)]
      (is (s/valid? ::ast/document-stream tokens))
      ;; Frontmatter contains only title
      (let [fm (ast/hydrate-frontmatter (:frontmatter conformed))]
        (is (= {:title "Real Title"} fm)))
      ;; Body contains the key: val line as body prose
      (let [body-entries (:body conformed)]
        (is (some (fn [[kind entry]]
                    (and (= kind :prose)
                         (= (first entry) :kv-as-text)
                         (= (second entry) [:tok/kv :key "val in body"])))
                  body-entries))))))

(deftest ast-optional-frontmatter-and-failure-test
  (testing "Document with no frontmatter is valid via s/? falling through to body"
    (let [doc-text "# Plain Document\n\nNo frontmatter here at all.\n"
          tokens (ast/lex-document doc-text)]
      (is (s/valid? ::ast/document-stream tokens))
      (let [conformed (s/conform ::ast/document-stream tokens)]
        (is (nil? (:frontmatter conformed)))
        (is (seq (:body conformed))))))

  (testing "Document with unclosed frontmatter fails ::ast/frontmatter spec and pinpoints error"
    (let [bad-doc-text "---\ntitle: Broken Document\nauthor: Wisdom\n"
          tokens (ast/lex-document bad-doc-text)]
      ;; When evaluated against ::ast/frontmatter directly, fails because closing fence is missing
      (is (not (s/valid? ::ast/frontmatter tokens)))
      (let [problems (s/explain-data ::ast/frontmatter tokens)]
        (is (some? problems))
        ;; Pinpoints failure at path [:close] with 'Insufficient input'
        (let [p (first (:clojure.spec.alpha/problems problems))]
          (is (= [:close] (:path p)))
          (is (= "Insufficient input" (:reason p))))))))

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
