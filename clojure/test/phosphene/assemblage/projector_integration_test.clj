(ns phosphene.assemblage.projector-integration-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure.spec.alpha :as s]
            [phosphene.assemblage.projector :as proj]
            [phosphene.assemblage.ast :as ast]
            [clojure.java.io :as io]))

(deftest combined-assemblage-corpus-projection-test
  (testing "Assembling combined spaces-of-reasoning and genealogy units"
    (let [res (proj/assemble-corpus ["assemblages/wisdom-1951/spaces-of-reasoning"
                                     "assemblages/wisdom-1951/genealogy"])
          graph (:assemblage/graph res)]
      ;; 21 reasoning units + 9 genealogy units = 30 total units
      (is (= 30 (:assemblage/total-units res)))
      (is (pos? (:assemblage/total-traits res)))
      (is (pos? (:assemblage/total-anchors res)))
      (is (pos? (:assemblage/total-witnesses res)))
      ;; Verified verbatim witnesses
      (is (= (:assemblage/total-witnesses res) (:assemblage/verified-verbatim-count res)))
      ;; Graph validity
      (is (map? graph))
      (is (pos? (count (:nodes graph))))
      (is (pos? (count (:edges graph))))
      ;; Manifest generation test
      (let [manifest (proj/generate-assemblage-manifest res)]
        (is (string? manifest))
        (is (.contains manifest "30"))))))
