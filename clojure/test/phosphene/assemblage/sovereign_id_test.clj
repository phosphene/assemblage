(ns phosphene.assemblage.sovereign-id-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as gen]
            [clojure.string :as str]
            [phosphene.assemblage.spec :as aspec]))

;; Generator for clean lowercase keyword corpus names
(def corpus-generator
  (gen/fmap (fn [chars] (keyword (str/lower-case (apply str chars))))
            (gen/vector (gen/elements [\a \b \c \d \e \w \i \s \d \o \m]) 3 8)))

(deftest test-character-level-sequence-grammar
  (testing "Character-level grammar validates valid close-read ID sequences"
    (is (s/valid? ::aspec/close-read-id-chars (seq "CR-WISDOM-U001")))
    (is (s/valid? ::aspec/close-read-id-chars (seq "CR-WISDOM-U013")))
    (is (s/valid? ::aspec/close-read-id-chars (seq "CR-ASHBY-U999"))))

  (testing "Character-level grammar rejects invalid format strings"
    (is (not (s/valid? ::aspec/close-read-id-chars (seq "WISDOM-U001"))))     ;; missing prefix
    (is (not (s/valid? ::aspec/close-read-id-chars (seq "CR-wisdom-U001"))))   ;; lowercase corpus
    (is (not (s/valid? ::aspec/close-read-id-chars (seq "CR-WISDOM-001"))))    ;; missing 'U'
    (is (not (s/valid? ::aspec/close-read-id-chars (seq "CR-WISDOM-U1"))))))   ;; not 3 digits

(deftest test-bidirectional-conformation-and-unforming
  (testing "Symmetric round-trip: String -> Conformed Record -> String"
    (let [raw-id "CR-WISDOM-U013"
          conformed (aspec/conform-close-read-id raw-id)]
      (is (= {:id/type :close-read
              :id/corpus :wisdom
              :id/unit 13}
             conformed))
      (is (s/valid? ::aspec/structured-close-read-id conformed))
      (let [unformed (aspec/unform-close-read-id conformed)]
        (is (= raw-id unformed)))))

  (testing "Round-trip across all 21 Wisdom close-read IDs in corpus"
    (doseq [n (range 1 22)]
      (let [raw-id (str "CR-WISDOM-U" (format "%03d" n))
            conformed (aspec/conform-close-read-id raw-id)
            unformed (aspec/unform-close-read-id conformed)]
        (is (not= ::s/invalid conformed))
        (is (= raw-id unformed))))))

(deftest test-generative-property-round-trip
  (testing "Property: for all generated valid structured IDs, unform -> conform is lossless"
    (let [corpus-samples (gen/sample corpus-generator 30)
          unit-samples (gen/sample (s/gen (s/int-in 1 1000)) 30)]
      (doseq [[c u] (map vector corpus-samples unit-samples)]
        (let [record {:id/type :close-read
                      :id/corpus c
                      :id/unit u}
              id-str (aspec/unform-close-read-id record)
              re-conformed (aspec/conform-close-read-id id-str)]
          (is (= record re-conformed)
              (str "Failed roundtrip on " record " -> " id-str)))))))
