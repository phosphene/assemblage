(ns phosphene.assemblage.atomic-blocks-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure.spec.alpha :as s]
            [phosphene.assemblage.ast :as ast]
            [phosphene.assemblage.lens :as lens]
            [phosphene.assemblage.spec :as aspec]))

(def sample-atomic-markdown
  "---
id: CR-WISDOM-U099
title: Decomposed Unit Example
---

# Decomposed Unit Example

Prose introducing the phenomenological firstness...

## 1. Firstness: Perceptual Metaphor

```clojure
{:seme/firstness
 {:quality/phenomenon :error-compensation-oscillation
  :perceptual/metaphor \"A rider balancing on a bicycle by continuous correction\"}}
```

Prose discussing the secondness empirical encounter...

## 2. Secondness: Empirical Apparatus

```clojure
{:pheme/secondness
 {:encounter :bicycle-steering-mechanism
  :historical-anchors [:wisdom-1951-bicycle-balance :ashby-1948-homeostat]}}
```

Prose outlining the formal delome invariants...

## 3. Thirdness: Formal Invariants & Rules

```clojure
{:delome/thirdness
 [(invariant :system/equilibrium #{:stable :unstable :metastable})
  (rule :corrective-countersteering
    (implies (= :fall-direction :right) (steer :right)))]}
```

## 4. Definitions

```clojure
{:term/definitions
 [#:term{:name :error-compensation, :def \"Continuous corrective feedback countering displacement\"}]}
```
")

(deftest test-multi-block-atomic-composition
  (testing "Lens cleanly extracts and merges distributed atomic S-expressions across markdown sections"
    (let [doc (ast/parse-markdown sample-atomic-markdown)
          triad (lens/project-close-read doc)]
      (is (map? triad))
      (is (nil? (:anomaly/lens triad)))
      (is (= "CR-WISDOM-U099" (:close-read/id triad)))
      (is (= :error-compensation-oscillation (get-in triad [:seme/firstness :quality/phenomenon])))
      (is (= :bicycle-steering-mechanism (get-in triad [:pheme/secondness :encounter])))
      (is (= 2 (count (get-in triad [:pheme/secondness :historical-anchors]))))
      (is (= 2 (count (:delome/thirdness triad))))
      (is (= 1 (count (:term/definitions triad))))
      (is (s/valid? (s/keys) triad)))))
