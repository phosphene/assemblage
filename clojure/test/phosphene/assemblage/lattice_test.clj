(ns phosphene.assemblage.lattice-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure.spec.alpha :as s]
            [phosphene.assemblage.lattice :as lat]
            [phosphene.seme.pheme :as pheme]))

;; Set up specs for test domain
(s/def :dynamics/homeostatic-bounds boolean?)
(s/def :dynamics/configuration-determines-successor? true?)
(s/def :dynamics/uncertainty-response keyword?)

(deftest unit-vertex-poset-projection-test
  (testing "Units of reason project into valid analytical pheme vertices"
    (let [unit-ashby {:unit/id :unit/CR-WISDOM-U020
                      :unit/title "Ashby Deterministic Machine"
                      :unit/traits #{:dynamics/homeostatic-bounds
                                     :dynamics/configuration-determines-successor?}}
          v (lat/unit->vertex unit-ashby)]
      (is (pheme/vertex? v))
      (is (= :unit/CR-WISDOM-U020 (:vertex/id v)))
      (is (contains? (:vertex/req v) :dynamics/homeostatic-bounds))
      (is (contains? (:vertex/req v) :dynamics/configuration-determines-successor?)))))

(deftest trait-subsumption-and-meet-test
  (testing "Poset meet computes greatest lower bound (consensus) across models"
    (let [v-ashby (pheme/vertex :vertex/ashby-machine
                                [:dynamics/homeostatic-bounds
                                 :dynamics/configuration-determines-successor?])
          v-mackay (pheme/vertex :vertex/mackay-machine
                                 [:dynamics/homeostatic-bounds
                                  :dynamics/uncertainty-response])
          meet (lat/unit-meet v-ashby v-mackay)]
      (is (pheme/vertex? meet))
      (is (= #{:dynamics/homeostatic-bounds} (:vertex/req meet)))
      ;; In keyset lattices, more specific models (larger required keyset) subsume the meet
      (is (lat/trait-subsumes? v-ashby meet))
      (is (lat/trait-subsumes? v-mackay meet)))))

(deftest wisdom-ashby-tension-evaluation-test
  (testing "Empirical biological referent falsifying deterministic succession generates proof-carrying tension"
    (let [v-ashby (pheme/vertex :vertex/ashby-machine
                                [:dynamics/homeostatic-bounds
                                 :dynamics/configuration-determines-successor?])
          human-referent {:organism/species :homo-sapiens
                          :dynamics/homeostatic-bounds true
                          :dynamics/configuration-determines-successor? false
                          :dynamics/uncertainty-response :mackay-stochastic}
          tension (lat/evaluate-tension v-ashby human-referent)]
      (is (some? tension))
      (is (= :structural-provocation (:tension/type tension)))
      (is (= :vertex/ashby-machine (:tension/theoretical-model tension)))
      (is (= :vertex/ashby-machine-retracted (:tension/retracted-model tension)))
      (is (= #{:dynamics/configuration-determines-successor?} (:tension/falsified-axioms tension)))
      (is (= #{:dynamics/homeostatic-bounds} (:tension/preserved-axioms tension)))
      (is (= 0.5 (:tension/residual-metric tension)))
      
      ;; Verify retracted vertex preserves sound lower bound
      (let [retracted (:tension/retracted-vertex tension)]
        (is (= #{:dynamics/homeostatic-bounds} (:vertex/req retracted)))))))
