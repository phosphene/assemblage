(ns phosphene.assemblage.delome-spec-test
  (:require [clojure.test :refer :all]
            [clojure.spec.alpha :as s]
            [phosphene.assemblage.spec :as aspec]))

(deftest test-delome-thirdness-declarative-grammar
  (testing "invariant forms conform to ::aspec/delome-invariant"
    (let [form '(invariant :computation/wartime-pressure boolean?)]
      (is (s/valid? ::aspec/delome-invariant form))
      (let [c (s/conform ::aspec/delome-invariant form)]
        (is (= 'invariant (:tag c)))
        (is (= [:kw :computation/wartime-pressure] (:coord c))))))

  (testing "rule forms conform to ::aspec/delome-rule"
    (let [form '(rule :overcomes-latency-bottleneck
                  (implies (and (= :computation/medium :electronic-machine)
                                (<= :computation/solution-time-hours :tactical-horizon-hours))
                           :tactical-viability))]
      (is (s/valid? ::aspec/delome-rule form))
      (let [c (s/conform ::aspec/delome-rule form)]
        (is (= 'rule (:tag c)))
        (is (= [:kw :overcomes-latency-bottleneck] (:id c))))))

  (testing "taxonomy forms conform to ::aspec/delome-taxonomy"
    (let [form '(taxonomy :system-order
                  {:first-order {:variables [:magnet-positions]}
                   :second-order {:variables [:magnet-positions :feed-back-settings]}})]
      (is (s/valid? ::aspec/delome-taxonomy form))))

  (testing "isomorphism forms conform to ::aspec/delome-isomorphism"
    (let [form '(isomorphism :all-or-none-principle
                  {:electronic-machine {:gate-state #{:open :closed}}
                   :neuron {:firing-state #{:fires :does-not-fire}}})]
      (is (s/valid? ::aspec/delome-isomorphism form))))

  (testing "full declarative thirdness vector conforms"
    (let [thirdness '[(invariant :computation/wartime-pressure boolean?)
                      (invariant :computation/latency-mode #{:discrete-hours :continuous-months})
                      (rule :overcomes-latency-bottleneck
                        (implies (= :computation/medium :electronic-machine)
                                 :tactical-viability))]]
      (is (s/valid? ::aspec/delome-thirdness thirdness)))))
