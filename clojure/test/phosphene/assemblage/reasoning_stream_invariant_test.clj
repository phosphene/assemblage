(ns phosphene.assemblage.reasoning-stream-invariant-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure.spec.alpha :as s]
            [phosphene.assemblage.spec :as aspec]))

(deftest test-reasoning-stream-sand-invariants
  (testing "Valid reasoning stream satisfies sequence grammar and all global invariants via s/&"
    (let [valid-stream [{:unit/id :unit/u1
                         :unit/anchors #{:ashby-1948}
                         :unit/witnesses [{:witness/text "text A"}]}
                        {:unit/id :unit/u2
                         :unit/anchors #{}
                         :unit/witnesses []}]]
      (is (s/valid? ::aspec/reasoning-stream valid-stream))
      (let [conformed (s/conform ::aspec/reasoning-stream valid-stream)]
        (is (map? conformed))
        (is (= 2 (count (:units conformed)))))))

  (testing "Invariant 1 Violation: Stream with zero empirical anchors fails s/&"
    (let [unanchored-stream [{:unit/id :unit/u1
                             :unit/anchors #{}
                             :unit/witnesses [{:witness/text "text A"}]}
                            {:unit/id :unit/u2
                             :unit/anchors #{}
                             :unit/witnesses []}]]
      (is (not (s/valid? ::aspec/reasoning-stream unanchored-stream)))
      (let [ed (s/explain-data ::aspec/reasoning-stream unanchored-stream)]
        (is (seq (:clojure.spec.alpha/problems ed))))))

  (testing "Invariant 2 Violation: Stream with duplicate unit IDs (potential cycle/aliasing) fails s/&"
    (let [duplicate-stream [{:unit/id :unit/u1
                            :unit/anchors #{:ashby-1948}
                            :unit/witnesses [{:witness/text "text A"}]}
                           {:unit/id :unit/u1
                            :unit/anchors #{:wiener-1948}
                            :unit/witnesses [{:witness/text "text B"}]}]]
      (is (not (s/valid? ::aspec/reasoning-stream duplicate-stream)))
      (let [ed (s/explain-data ::aspec/reasoning-stream duplicate-stream)]
        (is (seq (:clojure.spec.alpha/problems ed))))))

  (testing "Invariant 3 Violation: Stream with zero verbatim witnesses fails s/&"
    (let [unwitnessed-stream [{:unit/id :unit/u1
                              :unit/anchors #{:ashby-1948}
                              :unit/witnesses []}
                             {:unit/id :unit/u2
                              :unit/anchors #{:wiener-1948}
                              :unit/witnesses []}]]
      (is (not (s/valid? ::aspec/reasoning-stream unwitnessed-stream)))
      (let [ed (s/explain-data ::aspec/reasoning-stream unwitnessed-stream)]
        (is (seq (:clojure.spec.alpha/problems ed)))))))
