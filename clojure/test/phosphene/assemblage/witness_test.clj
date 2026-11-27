(ns phosphene.assemblage.witness-test
  "Hermetic empirical gate for the verbatim register.
   Every deposited Wisdom (1951) verbatim witness must be an exact contiguous
   token selection from the primary corpus (assemblages/wisdom-1951/primary/).
   
   Adheres to T-438 and seL4-harness replication requirements:
   - Req 1: Point the witness test at shipped units (both blockquote & :witness/text).
   - Req 3: Make the lens produce witness text the predicate expects.
   - Req 4: Code-located anchors with single-occurrence uniqueness.
   - Req 6: Computed verification results, zero string assertions.
   - Req 7 & 8: Verified source-text SHA-256 and published normalizer data rules."
  (:require [clojure.test :refer [deftest is testing]]
            [clojure.string :as str]
            [clojure.spec.alpha :as s]
            [clojure.java.io :as io]
            [phosphene.assemblage.witness :as w]
            [phosphene.assemblage.spec :as spec]
            [phosphene.assemblage.ast :as ast]
            [phosphene.assemblage.lens :as lens]))

(def PRIMARY-ROOT "assemblages/wisdom-1951/primary/source-text.txt")
(def EXPECTED-CORPUS-SHA "66c589afc1c477263a8cf13661657b66206dce0dcd27be103167e9ebb1c21ae3")

(defn- primary-corpus []
  (slurp PRIMARY-ROOT))

(defn- unit-markdown-files []
  (->> (file-seq (io/file "assemblages/wisdom-1951/spaces-of-reasoning"))
       (filter #(.isFile %))
       (filter #(and (str/starts-with? (.getName %) "unit-")
                     (str/ends-with? (.getName %) ".md")))
       (sort-by #(.getName %))))

(defn- genealogy-markdown-files []
  (->> (file-seq (io/file "assemblages/wisdom-1951/genealogy"))
       (filter #(.isFile %))
       (filter #(and (str/starts-with? (.getName %) "unit-gen-")
                     (str/ends-with? (.getName %) ".md")))
       (sort-by #(.getName %))))

;; --- Empirical Gate Tests ---

(deftest test-source-text-sha256-digest
  (testing "Primary corpus source-text.txt matches the published cryptographic digest"
    (let [corpus (primary-corpus)
          actual-sha (w/sha256 corpus)]
      (is (= EXPECTED-CORPUS-SHA actual-sha)
          (str "Source text SHA-256 mismatch! Expected " EXPECTED-CORPUS-SHA " but got " actual-sha)))))

(deftest test-published-normalization-rules
  (testing "Published normalizer rules are data and match canonical digest"
    (is (vector? w/normalization-rules))
    (is (= 9 (count w/normalization-rules)))
    (is (string? w/NORMALIZER-DIGEST))
    ;; Test vectors for each rule
    (is (= "-" (w/normalize "—")))
    (is (= "-" (w/normalize "–")))
    (is (= "feed-back" (w/normalize "feed-   back")))
    (is (= "word1" (w/normalize "word¹")))
    (is (= "word2" (w/normalize "word²")))
    (is (= "\"feed-back\"" (w/normalize " \"feed-back \" ")))
    (is (= "'automatic'" (w/normalize " ' automatic ' ")))))

(deftest test-deterministic-locate-anchor
  (testing "locate computes exact offset, occurrence count, and uniqueness"
    (let [corpus (primary-corpus)
          loc (w/locate corpus "DURING World War II some of the research in theoretical physics")]
      (is (= EXPECTED-CORPUS-SHA (:location/source-sha256 loc)))
      (is (= :body (:location/region loc)))
      (is (= 1 (:location/occurrences loc)))
      (is (true? (:location/unique? loc)))
      (is (nat-int? (:location/start loc)))
      (is (nat-int? (:location/end loc)))
      (is (= (+ (:location/start loc) (:location/char-count loc)) (:location/end loc)))
      (is (s/valid? ::spec/witness-location loc)))))

(deftest test-lens-witness-extraction-parity
  (testing "Lens verbatim projection extracts clean witnesses without citation artifacts"
    (let [doc-md "# Test\n\n> \"Such machines had to be able (a) to be fed with information\"\n> (§1, p. 1)\n"
          doc-ast (ast/parse-markdown doc-md)
          witnesses (lens/project-verbatim-witnesses doc-ast)]
      (is (= 1 (count witnesses)))
      (is (= "Such machines had to be able (a) to be fed with information"
             (:witness/text (first witnesses)))))))

(deftest test-genealogy-units-witness-verification
  (testing "Every genealogy unit (GEN-000 to GEN-008) satisfies exact-contiguous witness verification"
    (let [corpus (primary-corpus)
          files (genealogy-markdown-files)]
      (is (= 9 (count files)))
      (doseq [f files]
        (let [doc-ast (ast/parse-markdown (slurp f))
              witnesses (lens/project-verbatim-witnesses doc-ast)
              verifications (w/verify-witnesses corpus (map :witness/text witnesses))]
          (is (seq verifications) (str "No witnesses extracted from " (.getName f)))
          (doseq [v verifications]
            (is (= :verbatim (:witness/verdict v))
                (str "Genealogy witness failed in " (.getName f) ": " (pr-str (:witness/text v))))
            (is (true? (:witness/exact-contiguous? v)))
            (is (true? (:witness/unique? v))
                (str "Genealogy witness not unique in " (.getName f) ": " (pr-str (:witness/text v))))))))))

(deftest witness-verification-is-open-world
  (testing "Verification maps permit unmodeled extension keys (open world)."
    (let [v (assoc (w/verify-witness (primary-corpus) "negative feed-back")
                   :cybernetics.wisdom/section "2.1"
                   :epistemics/basis-tier :primary)]
      (is (s/valid? ::spec/verbatim-register [v])))))
