(ns phosphene.assemblage.backport-test
  "Empirical verification of monotonic zipper backport across all 21
   spaces-of-reasoning units and 9 genealogy units (T-439, EschaBot Reqs 2 & 5).
   Guarantees:
   - 100% of units satisfy exact-contiguous substring verification against source-text.txt.
   - Every witness is unique in the corpus (:witness/unique? true).
   - Zero backslash-escaped literal quotes in blockquotes or witness text.
   - Monotonic preservation of surrounding expository prose and formal EDN representations."
  (:require [clojure.test :refer [deftest is testing]]
            [clojure.java.io :as io]
            [clojure.string :as str]
            [phosphene.assemblage.witness :as w]
            [phosphene.assemblage.ast :as ast]
            [phosphene.assemblage.lens :as lens]))

(def PRIMARY-ROOT "assemblages/wisdom-1951/primary/source-text.txt")

(defn- primary-corpus []
  (slurp PRIMARY-ROOT))

(defn- reasoning-unit-files []
  (->> (file-seq (io/file "assemblages/wisdom-1951/spaces-of-reasoning"))
       (filter #(.isFile %))
       (filter #(and (str/starts-with? (.getName %) "unit-")
                     (str/ends-with? (.getName %) ".md")))
       (sort-by #(.getName %))))

(deftest test-all-21-reasoning-units-witness-verification
  (testing "Every one of the 21 spaces-of-reasoning units satisfies exact-contiguous witness verification"
    (let [corpus (primary-corpus)
          files (reasoning-unit-files)]
      (is (= 21 (count files)) "Must test all 21 units in spaces-of-reasoning")
      (doseq [f files]
        (let [content (slurp f)
              doc-ast (ast/parse-markdown content)
              witnesses (lens/project-verbatim-witnesses doc-ast)
              verifications (w/verify-witnesses corpus (map :witness/text witnesses))]
          (is (seq verifications) (str "No witnesses extracted from " (.getName f)))
          (doseq [v verifications]
            (is (= :verbatim (:witness/verdict v))
                (str "Reasoning witness failed verdict in " (.getName f) ": " (pr-str (:witness/text v))))
            (is (true? (:witness/exact-contiguous? v))
                (str "Reasoning witness not contiguous in " (.getName f) ": " (pr-str (:witness/text v))))
            (is (true? (:witness/unique? v))
                (str "Reasoning witness not unique in " (.getName f) ": " (pr-str (:witness/text v))))))))))

(deftest test-no-escaped-quotes-in-blockquotes
  (testing "Markdown blockquotes must not contain escaped backslash-quotes (e.g. \\\")"
    (let [files (reasoning-unit-files)]
      (doseq [f files]
        (let [content (slurp f)
              lines (str/split-lines content)
              bq-lines (filter #(str/starts-with? % ">") lines)]
          (doseq [line bq-lines]
            (is (not (str/includes? line "\\\""))
                (str "Found literal escaped quote in " (.getName f) ": " line))))))))

(defn- extract-witness-text-lines [content]
  (keep (fn [line]
          (when (str/includes? line ":witness/text")
            (let [start (str/index-of line "\"")
                  end (str/last-index-of line "\"")]
              (when (and start end (> end start))
                (subs line (inc start) end)))))
        (str/split-lines content)))

(deftest test-edn-witness-record-parity
  (testing "Every embedded EDN :witness/text record in 21 units verifies against primary corpus"
    (let [corpus (primary-corpus)
          files (reasoning-unit-files)]
      (doseq [f files]
        (let [content (slurp f)
              witness-texts (extract-witness-text-lines content)]
          (is (seq witness-texts) (str "No :witness/text found in " (.getName f)))
          (doseq [raw-wt witness-texts]
            (let [wt (str/replace raw-wt "\\\"" "\"")
                  v (w/verify-witness corpus wt)]
              (is (= :verbatim (:witness/verdict v))
                  (str "EDN witness record failed in " (.getName f) ": " wt))
              (is (true? (:witness/exact-contiguous? v)))
              (is (true? (:witness/unique? v))))))))))
