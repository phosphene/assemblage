(ns phosphene.assemblage.core-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure.spec.alpha :as s]
            [phosphene.assemblage.core :as asm]
            [phosphene.assemblage.spec :as aspec]))

(deftest assemblage-spec-conformance-test
  (testing "make-assemblage returns valid ::aspec/assemblage"
    (let [empty-asm (asm/make-assemblage)]
      (is (s/valid? ::aspec/assemblage empty-asm))))

  (testing "add-node preserves spec validity and attributes"
    (let [a (-> (asm/make-assemblage)
                (asm/add-node :A #{:homeostatic :feedback} 1)
                (asm/add-node :B #{:feedback :open-system} 1))]
      (is (s/valid? ::aspec/assemblage a))
      (is (= 2 (count (:nodes a))))
      (is (= #{:feedback} (asm/shared-traits a :A :B)))))

  (testing "add-relation preserves spec validity and discovers transversal cross-links"
    (let [a (-> (asm/make-assemblage)
                (asm/add-node :A #{:homeostatic} 1)
                (asm/add-node :B #{:feedback} 1)
                (asm/add-relation :A :B :transversal-cross-link))]
      (is (s/valid? ::aspec/assemblage a))
      (is (= 1 (count (:edges a))))
      (is (= 1 (count (asm/transversal-cross-links a)))))))

(deftest cycles-detection-test
  (testing "Detecting bidirectional cyclic feedback conforms to spec"
    (let [a (-> (asm/make-assemblage)
                (asm/add-node :X #{} 0)
                (asm/add-node :Y #{} 0)
                (asm/add-relation :X :Y :feedback-cycle)
                (asm/add-relation :Y :X :feedback-cycle))]
      (is (s/valid? ::aspec/assemblage a))
      (is (= 1 (count (asm/cycles a)))))))
