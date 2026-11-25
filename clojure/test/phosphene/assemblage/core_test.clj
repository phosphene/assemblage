(ns phosphene.assemblage.core-test
  (:require [clojure.test :refer [deftest is testing]]
            [phosphene.assemblage.core :as asm]))

(deftest assemblage-construction-test
  (testing "Creation of nodes and relations"
    (let [a (-> (asm/make-assemblage)
                (asm/add-node :A #{:homeostatic :feedback} 1)
                (asm/add-node :B #{:feedback :open-system} 1)
                (asm/add-relation :A :B :transversal-cross-link))]
      (is (= 2 (count (:nodes a))))
      (is (= 1 (count (:edges a))))
      (is (= #{:feedback} (asm/shared-traits a :A :B)))
      (is (= 1 (count (asm/transversal-cross-links a)))))))

(deftest cycles-detection-test
  (testing "Detecting bidirectional cyclic feedback"
    (let [a (-> (asm/make-assemblage)
                (asm/add-node :X #{} 0)
                (asm/add-node :Y #{} 0)
                (asm/add-relation :X :Y :feedback)
                (asm/add-relation :Y :X :feedback))]
      (is (= 1 (count (asm/cycles a)))))))
