(ns phosphene.assemblage.projector-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure.spec.alpha :as s]
            [phosphene.assemblage.projector :as proj]
            [phosphene.assemblage.spec :as spec]
            [phosphene.assemblage.core :as asm]))

(deftest hermetic-assemblage-spaces-of-reasoning-test
  (testing "Assembling spaces-of-reasoning corpus directly from local Markdown files in repo"
    (let [res (proj/assemble-corpus "assemblages/wisdom-1951/spaces-of-reasoning")
          graph (:assemblage/graph res)]
      ;; Validates 21 units conformed
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
      
      ;; Computed witness verification
      (is (= 21 (:assemblage/total-witnesses res)))
      (is (= 21 (:assemblage/verified-verbatim-count res)))

      ;; Manifest generation test
      (let [manifest (proj/generate-assemblage-manifest res)]
        (is (string? manifest))
        (is (.contains manifest "Self-Assembling Fact Registry Manifest"))
        (is (.contains manifest "21"))))))

(deftest unified-multi-lensed-corpus-test
  (testing "Composing 21 spaces-of-reasoning units and 9 genealogy units into unified graph (T-442)"
    (let [res (proj/assemble-corpus ["assemblages/wisdom-1951/spaces-of-reasoning"
                                    "assemblages/wisdom-1951/genealogy"])
          graph (:assemblage/graph res)]
      ;; 21 reasoning units + 9 genealogy units = 30 units (excluding non-unit markdown)
      (is (= 30 (:assemblage/total-units res)))
      (is (= 64 (:assemblage/total-traits res)))
      (is (s/valid? ::spec/assemblage graph))
      (is (= 68 (count (:nodes graph))))
      (is (= 73 (count (:edges graph))))
      
      ;; All verbatim witnesses pass the computed empirical gate
      (is (= 56 (:assemblage/total-witnesses res)))
      (is (= 56 (:assemblage/verified-verbatim-count res)))

      ;; Manifest generation reflects computed outputs
      (let [manifest (proj/generate-assemblage-manifest res)]
        (is (string? manifest))
        (is (.contains manifest "56 / 56 witnesses verified exact contiguous substrings"))
        (is (.contains manifest "Zero Ellipses Citation Integrity:** Verified across 100%"))))))
