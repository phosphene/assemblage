(ns phosphene.assemblage.presence-sense-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure.spec.alpha :as s]
            [phosphene.assemblage.ast :as ast]
            [phosphene.assemblage.lens :as lens]
            [phosphene.assemblage.projector :as proj]))

(def sample-markdown
  "---
registry.core/id: 550e8400-e29b-41d4-a716-446655440000
title: \"Presence vs Sense Test\"
---

# Close Read Document

```clojure
{:close-read/id \"CR-TEST-001\"
 :extra.unregistered/annotation \"allowed-by-open-world\"
 :seme/firstness {:quality/phenomenon :testing-presence-vs-sense}
 :pheme/secondness {:encounter :test-phenomenological-encounter
                    :historical-anchors [:wiener-1948 :ashby-1952]}
 :term/definitions [{:term/name :open-world-fact :term/def \"An attribute whose Sense is global and Presence is local\"}]}
```
")

(deftest test-bare-skeys-open-world-fact-validation
  (testing "Universal bare (s/keys) validates any map carrying registered qualified attributes"
    (let [doc (ast/parse-markdown sample-markdown)
          triad (lens/project-close-read doc)]
      ;; Validates universally via bare (s/keys)
      (is (s/valid? (s/keys) triad))
      ;; Open-world accretion: unregistered extra attributes are preserved and do not cause failures
      (is (= "allowed-by-open-world" (:extra.unregistered/annotation triad)))))

  (testing "Registered attribute with invalid value is immediately caught by bare (s/keys)"
    ;; :close-read/id requires (s/or :str string? :kw keyword?)
    ;; An integer ID violates its global Sense!
    (let [bad-triad {:close-read/id 12345
                     :seme/firstness {:quality/phenomenon :foo}}]
      (is (not (s/valid? (s/keys) bad-triad))))))

(deftest test-point-of-use-presence-destructuring
  (testing "Consumer functions extract only required Presence while remaining open to extra attributes"
    (let [doc (ast/parse-markdown sample-markdown)
          elements (lens/project-assemblage-elements doc)]
      (is (map? elements))
      ;; Emitted elements satisfy bare (s/keys)
      (is (s/valid? (s/keys) elements))
      ;; Destructured units and anchors
      (is (contains? (:nodes elements) :unit/CR-TEST-001))
      (is (contains? (:nodes elements) :wiener-1948))
      (is (contains? (:nodes elements) :ashby-1952))
      ;; Directed edges
      (is (= 2 (count (:edges elements)))))))
