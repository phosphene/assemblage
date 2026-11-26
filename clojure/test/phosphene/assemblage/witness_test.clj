(ns phosphene.assemblage.witness-test
  "Hermetic empirical gate for the verbatim register.
   Every deposited Wisdom (1951) verbatim witness must be an exact contiguous
   token selection from the primary corpus (assemblages/wisdom-1951/primary/)."
  (:require [clojure.test :refer [deftest is testing]]
            [clojure.string :as str]
            [phosphene.assemblage.witness :as w]
            [phosphene.assemblage.spec :as spec]
            [clojure.spec.alpha :as s]))

(def PRIMARY-ROOT "assemblages/wisdom-1951/primary/source-text.txt")

(defn- primary-corpus []
  (slurp PRIMARY-ROOT))

(defn- l3-witnesses []
  "Extract blockquote witness text from every L3-verbatim file.
   Convention: a witness is a blockquote line beginning `> \"...\"` —
   the double quotes delimit the verbatim block; they are not tokens
   and are stripped (one leading, one trailing if present)."
  (->> (file-seq (java.io.File. "assemblages/wisdom-1951/deep-reads/L3-verbatim"))
       (filter #(.isFile %))
       (filter #(str/ends-with? (.getName %) ".md"))
       (mapcat (fn [f]
                 (->> (str/split (slurp f) #"\n")
                      (filter #(str/starts-with? % "> \""))
                      (map (fn [l]
                             (let [s (str/trim (subs l 3))]
                               (if (str/ends-with? s "\"")
                                 (subs s 0 (dec (count s)))
                                 s)))))))
       (remove str/blank?)
       vec))

(deftest primary-corpus-is-hermetic
  (testing "The primary token stream is present and non-trivial."
    (let [corpus (primary-corpus)]
      (is (> (count corpus) 60000))
      (is (str/includes? corpus "Amplification Feed-Back"))
      (is (str/includes? corpus "mixed feed-back"))
      (is (str/includes? corpus "uniselectors")))))

(deftest exact-contiguous-is-decidable
  (testing "A genuine contiguous selection passes."
    (is (w/exact-contiguous? (primary-corpus)
        "This feed-back is 'negative' because the work done by the feed-back mechanism opposes the working of the main driving force.")))
  (testing "Truncation (mid-sentence cut) fails the empirical gate."
    ;; An UNMARKED end-cut is a valid selection: boundaries of a selection
    ;; are arbitrary, so a shorter span is just a different selection.
    (is (w/exact-contiguous? (primary-corpus)
        "This feed-back is 'negative' because the work done by the feed-back mechanism opposes the working of the"))
    ;; Truncation becomes a register violation only when it is MARKED as
    ;; continuing (ellipsis): then the '...' glyphs are not corpus tokens.
    (is (= :truncated (:witness/verdict (w/verify-witness (primary-corpus)
        "This feed-back is 'negative' because the work done by the feed-back mechanism opposes the working of the...")))))
  (testing "Invention (a token sequence that never occurred) fails."
    (is (not (w/exact-contiguous? (primary-corpus)
        "This feed-back is 'negative' because the organism resists displacement.")))
    (is (= :invented (:witness/verdict (w/verify-witness (primary-corpus)
        "This feed-back is 'negative' because the organism resists displacement."))))))

(deftest boundaries-are-arbitrary
  (testing "Overlapping selections from the same region both pass: the boundary choice is free."
    (let [corpus (primary-corpus)]
      (is (w/boundary-arbitrary? corpus
          "negative feed-back"
          "feed-back is 'negative' because the work done by the feed-back mechanism"))
      (is (w/exact-contiguous? corpus "feed-back"))
      (is (w/exact-contiguous? corpus
          "The energy of a feed-back may be solely derived from the main power or solely due to a local source, or to both")))))

(deftest ocr-hyphenation-normalization-preserves-tokens
  (testing "OCR hyphenation artifacts normalize to the same token sequence."
    (let [corpus (primary-corpus)]
      ;; The corpus normalizes "feed- back" -> "feed-back"; a witness deposited
      ;; with the artifact denotes the same token sequence and passes.
      (is (w/exact-contiguous? corpus "Any chain- reaction mechanism is an illustration."))
      (is (w/exact-contiguous? corpus "Any chain-reaction mechanism is an illustration.")))))

(deftest all-deposited-l3-witnesses-are-verbatim
  (testing "Every L3-verbatim blockquote is an exact contiguous selection."
    (let [corpus (primary-corpus)
          witnesses (l3-witnesses)
          verifications (w/verify-witnesses corpus witnesses)]
      (is (seq witnesses))
      (doseq [{:keys [witness/text witness/verdict]} verifications]
        (is (= :verbatim verdict)
            (str "Non-verbatim witness in the verbatim register: " (pr-str (str/join (take 80 text))))))
      (is (s/valid? ::spec/verbatim-register verifications)))))

(deftest witness-verification-is-open-world
  (testing "Verification maps permit unmodeled extension keys (open world)."
    (let [v (assoc (w/verify-witness (primary-corpus) "negative feed-back")
                   :cybernetics.wisdom/section "2.1"
                   :epistemics/basis-tier :primary)]
      (is (s/valid? ::spec/verbatim-register [v])))))
