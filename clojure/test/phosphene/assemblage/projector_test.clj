(ns phosphene.assemblage.projector-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure.spec.alpha :as s]
            [phosphene.assemblage.projector :as proj]
            [phosphene.assemblage.spec :as spec]
            [phosphene.assemblage.core :as asm]))

(deftest hermetic-assemblage-projection-test
  (testing "Assembling corpus directly from local Markdown files in repo"
    (let [res (proj/assemble-corpus "assemblages/wisdom-1951/spaces-of-reasoning")
          graph (:assemblage/graph res)]
      ;; Validates 18 units conformed
      (is (= 21 (:assemblage/total-units res)))
      (is (= 64 (:assemblage/total-traits res)))
      (is (s/valid? ::spec/assemblage graph))
      (is (= 57 (count (:nodes graph))))
      (is (= 54 (count (:edges graph))))
      
      ;; Verify trait intersections
      (is (contains? (:algebra/trait-intersections res)
                     [:unit/CR-WISDOM-U006 :unit/CR-WISDOM-U005]))
      (is (= #{:negative-feed-back}
             (get (:algebra/trait-intersections res) [:unit/CR-WISDOM-U006 :unit/CR-WISDOM-U005])))
      
      ;; Manifest generation test
      (let [manifest (proj/generate-assemblage-manifest res)]
        (is (string? manifest))
        (is (.contains manifest "Self-Assembling Fact Registry Manifest"))
        (is (.contains manifest "21"))))))
