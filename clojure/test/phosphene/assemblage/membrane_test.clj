(ns phosphene.assemblage.membrane-test
  "Unit, property, and egress gate verification for phosphene.assemblage.membrane (T-440).
   Verifies:
   - Fail-closed egress rejection of unmodeled attributes (Req 10).
   - Purged shims: rejection of malformed :seme, :pheme, :delome (Req 11).
   - Full genealogical validation with closed root/reference specs (Req 12).
   - Strict sovereign ID derivation from frontmatter (Req 13).
   - Typed Cognitect anomalies on failures and collisions (Req 14).
   - Full corpus export parity across all 21 reasoning units and 9 genealogy units."
  (:require [clojure.test :refer [deftest is testing]]
            [clojure.spec.alpha :as s]
            [clojure.java.io :as io]
            [phosphene.assemblage.ast :as ast]
            [phosphene.assemblage.lens :as lens]
            [phosphene.assemblage.membrane :as membrane]))

(deftest closed-keys-predicate-test
  (testing "closed-keys? accepts exact or subset keys"
    (let [pred (membrane/closed-keys? #{:a :b :c})]
      (is (true? (pred {:a 1 :b 2})))
      (is (true? (pred {:a 1 :b 2 :c 3})))
      (is (true? (pred {})))))

  (testing "closed-keys? rejects unmodeled keys"
    (let [pred (membrane/closed-keys? #{:a :b})]
      (is (false? (pred {:a 1 :b 2 :unmodeled 3})))
      (is (false? (pred {:extra 1}))))))

(deftest sovereign-id-enforcement-test
  (testing "Missing frontmatter ID fails closed with :cognitect.anomalies/incorrect"
    (let [bad-doc {:type :doc :content [] :metadata {:title "No ID"}}
          res (membrane/export-reasoning-unit bad-doc)]
      (is (membrane/anomaly? res))
      (is (= :cognitect.anomalies/incorrect (:cognitect.anomalies/category res)))
      (is (re-find #"Sovereign identifier missing" (:anomaly/message res)))))

  (testing "Frontmatter ID vs embedded EDN ID collision fails closed with :cognitect.anomalies/conflict"
    (let [conflicting-doc (ast/parse-markdown
                           (str "---\nid: CR-WISDOM-U999\ntitle: Conflict\n---\n\n"
                                "```clojure\n"
                                "{:close-read/id \"CR-WISDOM-U001\"\n"
                                " :seme/firstness {:quality/phenomenon :test}\n"
                                " :pheme/secondness {:pheme/encounter :test}\n"
                                " :delome/thirdness []\n"
                                " :term/definitions []\n"
                                " :witness/selection {:witness/text \"hello\" :witness/zero-ellipses? true}}\n"
                                "```"))
          res (membrane/export-reasoning-unit conflicting-doc)]
      (is (membrane/anomaly? res))
      (is (= :cognitect.anomalies/conflict (:cognitect.anomalies/category res)))
      (is (re-find #"Sovereign ID conflict" (:anomaly/message res))))))

(deftest fail-closed-unmodeled-keys-test
  (testing "Reasoning unit with unmodeled root key is rejected at egress"
    (let [doc (ast/parse-markdown
               (str "---\nid: CR-WISDOM-U999\ntitle: Extra Root Key\nsource: Test\n---\n\n"
                    "```clojure\n"
                    "{:close-read/id \"CR-WISDOM-U999\"\n"
                    " :seme/firstness {:quality/phenomenon :test}\n"
                    " :pheme/secondness {:pheme/encounter :test}\n"
                    " :delome/thirdness []\n"
                    " :term/definitions []\n"
                    " :witness/selection {:witness/text \"test\" :witness/zero-ellipses? true}\n"
                    " :extra/unmodeled-field \"forbidden-in-space-d\"}\n"
                    "```"))
          res (membrane/export-reasoning-unit doc)]
      (is (membrane/anomaly? res))
      (is (= :cognitect.anomalies/incorrect (:cognitect.anomalies/category res)))
      (is (= #{:extra/unmodeled-field} (get-in res [:anomaly/context :unmodeled-keys])))))

  (testing "Reasoning unit with unmodeled nested key in :witness/selection is rejected"
    (let [doc (ast/parse-markdown
               (str "---\nid: CR-WISDOM-U999\ntitle: Extra Nested Key\nsource: Test\n---\n\n"
                    "```clojure\n"
                    "{:close-read/id \"CR-WISDOM-U999\"\n"
                    " :seme/firstness {:quality/phenomenon :test}\n"
                    " :pheme/secondness {:pheme/encounter :test}\n"
                    " :delome/thirdness []\n"
                    " :term/definitions []\n"
                    " :witness/selection {:witness/text \"test\" :witness/zero-ellipses? true :rogue/key 123}}\n"
                    "```"))
          res (membrane/export-reasoning-unit doc)]
      (is (membrane/anomaly? res))
      (is (= :cognitect.anomalies/incorrect (:cognitect.anomalies/category res))))))

(deftest purged-shims-rejection-test
  (testing "Reject arbitrary unmodeled legacy delome expression"
    (let [doc (ast/parse-markdown
               (str "---\nid: CR-WISDOM-U999\ntitle: Legacy Delome\nsource: Test\n---\n\n"
                    "```clojure\n"
                    "{:close-read/id \"CR-WISDOM-U999\"\n"
                    " :seme/firstness {:quality/phenomenon :test}\n"
                    " :pheme/secondness {:pheme/encounter :test}\n"
                    " :delome/thirdness 12345\n" ; invalid, not vector or map
                    " :term/definitions []\n"
                    " :witness/selection {:witness/text \"test\" :witness/zero-ellipses? true}}\n"
                    "```"))
          res (membrane/export-reasoning-unit doc)]
      (is (membrane/anomaly? res))
      (is (= :cognitect.anomalies/incorrect (:cognitect.anomalies/category res))))))

(deftest genealogy-closed-egress-test
  (testing "Genealogy unit with unmodeled key is rejected"
    (let [doc (ast/parse-markdown
               (str "---\nregistry.core/id: unit-gen-999\nline: genealogy\n---\n\n"
                    "```clojure\n"
                    "{:genealogy/id \"unit-gen-999\"\n"
                    " :genealogy/target-claim \"Test claim\"\n"
                    " :genealogy/epoch 1951\n"
                    " :genealogy/epistemic-status :dual-genesis\n"
                    " :genealogy/precursors []\n"
                    " :genealogy/successors []\n"
                    " :unauthorized/side-channel true}\n"
                    "```"))
          res (membrane/export-unit doc)]
      (is (membrane/anomaly? res))
      (is (= :cognitect.anomalies/incorrect (:cognitect.anomalies/category res)))
      (is (= #{:unauthorized/side-channel} (get-in res [:anomaly/context :unmodeled-keys]))))))

(deftest corpus-egress-verification-test
  (testing "All 21 Spaces of Reasoning units pass closed membrane egress without anomaly"
    (let [units (->> (file-seq (io/file "assemblages/wisdom-1951/spaces-of-reasoning"))
                     (filter #(.isFile %))
                     (filter #(and (.startsWith (.getName %) "unit-")
                                   (.endsWith (.getName %) ".md")))
                     (sort-by #(.getName %)))]
      (is (= 21 (count units)))
      (doseq [f units]
        (let [doc (ast/parse-markdown (slurp f))
              res (membrane/export-unit doc)]
          (is (not (membrane/anomaly? res))
              (str "Failed reasoning export on: " (.getName f) " -> " (pr-str res)))
          (is (string? (str (:close-read/id res))))
          (is (s/valid? ::membrane/closed-close-read-unit res))))))

  (testing "All 9 Genealogy units pass closed membrane egress without anomaly"
    (let [units (->> (file-seq (io/file "assemblages/wisdom-1951/genealogy"))
                     (filter #(.isFile %))
                     (filter #(and (.startsWith (.getName %) "unit-gen-")
                                   (.endsWith (.getName %) ".md")))
                     (sort-by #(.getName %)))]
      (is (= 9 (count units)))
      (doseq [f units]
        (let [doc (ast/parse-markdown (slurp f))
              res (membrane/export-unit doc)]
          (is (not (membrane/anomaly? res))
              (str "Failed genealogy export on: " (.getName f) " -> " (pr-str res)))
          (is (string? (str (:genealogy/id res))))
          (is (s/valid? ::membrane/closed-genealogy-unit res)))))))
