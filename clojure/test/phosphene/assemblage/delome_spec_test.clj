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

(deftest test-nested-sequence-splicing-hygiene
  (testing "Antipattern 1: wrapping inner sequence specs in (s/spec ...) preserves nested boundaries"
    ;; Unwrapped sequence spec splices flatly into outer sequence
    (let [unwrapped-inner (s/cat :tag keyword? :val any?)
          unwrapped-outer (s/cat :head keyword? :nested unwrapped-inner)
          ;; Wrapped sequence spec treats nested sequence as a discrete collection element
          wrapped-outer (s/cat :head keyword? :nested (s/spec unwrapped-inner))]
      ;; Unwrapped matches flat stream [1 2 3], FAILS on nested collection [1 [2 3]]
      (is (s/valid? unwrapped-outer [:cmd :tag "val"]))
      (is (not (s/valid? unwrapped-outer [:cmd [:tag "val"]])))

      ;; Wrapped correctly matches nested collection [:cmd [:tag "val"]] and rejects flat stream
      (is (s/valid? wrapped-outer [:cmd [:tag "val"]]))
      (is (not (s/valid? wrapped-outer [:cmd :tag "val"])))
      (is (= {:head :cmd, :nested {:tag :tag, :val "val"}}
             (s/conform wrapped-outer [:cmd [:tag "val"]])))))

  (testing "Delome clauses in vector are discrete sequential forms, not flat spliced tokens"
    (let [clauses '[(invariant :computation/wartime-pressure boolean?)
                    (rule :test-rule (and true false))]]
      ;; ::delome-clause uses (s/spec ::delome-invariant) and (s/spec ::delome-rule)
      ;; ensuring each clause is validated as an atomic AST node rather than being spliced flatly
      (is (s/valid? ::aspec/delome-thirdness clauses))
      (let [conformed (s/conform ::aspec/delome-thirdness clauses)]
        (is (= :declarative-vector (first conformed)))
        (is (= 2 (count (second conformed))))
        (is (= :invariant (first (first (second conformed)))))
        (is (= :rule (first (second (second conformed)))))))))
