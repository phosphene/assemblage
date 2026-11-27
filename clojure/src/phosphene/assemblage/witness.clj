(ns phosphene.assemblage.witness
  "The verbatim register: empirical verification of token-sequence selection.

   Rule (Topic 102248, Ed Phil 2026-11-26 #102484):
   - We cannot invent or truncate token sequences.
   - We CAN select token sequences while in verbatim read mode.
   - Boundaries of the selection are arbitrary and that is fine.
   - Other projections may be generated, but never marked verbatim.

   A witness is valid iff, after token-preserving normalization, it is an
   exact contiguous substring of the primary corpus at some integer offset.
   This is a decidable predicate — no heuristic, no judgement call."
  (:require [clojure.string :as str]))

;; --- Cryptographic Hashing ---

(defn sha256
  "Compute the lowercase hex SHA-256 digest of string `s` using UTF-8 encoding."
  [^String s]
  (let [md (java.security.MessageDigest/getInstance "SHA-256")
        bytes (.digest md (.getBytes s "UTF-8"))]
    (apply str (map #(format "%02x" (bit-and % 0xff)) bytes))))

;; --- Published Ordered Normalization Rules ---

(def normalization-rules
  "Ordered sequence of token-preserving transformation rules applied
   identically to the primary corpus and all candidate witness strings.
   Only OCR/printing artifacts of spacing and glyph variants are normalized;
   no semantic token is added, removed, substituted, or reordered."
  [{:rule/id :dash-canonicalization
    :rule/description "Canonicalize dash glyphs: em/en dash -> hyphen"
    :rule/pattern #"[\u2013\u2014]"
    :rule/replacement "-"}
   {:rule/id :superscript-digit-1
    :rule/description "Canonicalize superscript footnote 1 -> plain 1"
    :rule/pattern #"\u00b9"
    :rule/replacement "1"}
   {:rule/id :superscript-digit-2
    :rule/description "Canonicalize superscript footnote 2 -> plain 2"
    :rule/pattern #"\u00b2"
    :rule/replacement "2"}
   {:rule/id :superscript-digit-3
    :rule/description "Canonicalize superscript footnote 3 -> plain 3"
    :rule/pattern #"\u00b3"
    :rule/replacement "3"}
   {:rule/id :superscript-digit-4
    :rule/description "Canonicalize superscript footnote 4 -> plain 4"
    :rule/pattern #"\u2074"
    :rule/replacement "4"}
   {:rule/id :hyphen-whitespace-join
    :rule/description "Join hyphenated line breaks keeping the hyphen: 'feed- back' -> 'feed-back'"
    :rule/pattern #"-\s+"
    :rule/replacement "-"}
   {:rule/id :strip-whitespace-before-quote
    :rule/description "Strip whitespace preceding single or double quote glyphs"
    :rule/pattern #" +(['\"])"
    :rule/replacement "$1"}
   {:rule/id :strip-whitespace-after-quote
    :rule/description "Strip whitespace following single or double quote glyphs"
    :rule/pattern #"(['\"])+ +"
    :rule/replacement "$1"}
   {:rule/id :collapse-whitespace-runs
    :rule/description "Collapse all runs of whitespace (spaces, tabs, newlines) to a single space"
    :rule/pattern #"\s+"
    :rule/replacement " "}])

(def NORMALIZER-DIGEST
  "SHA-256 digest of the canonical normalization rule sequence."
  (sha256 (pr-str normalization-rules)))

;; --- Token-preserving normalization ---

(defn normalize
  "Apply the published ordered normalization rules identically to corpus and witness."
  [s]
  (if (str/blank? s)
    ""
    (-> (reduce (fn [acc {:keys [:rule/pattern :rule/replacement]}]
                  (str/replace acc pattern replacement))
                s
                normalization-rules)
        str/trim)))

;; --- The empirical verbatim predicate ---

(defn find-all-occurrences
  "Returns a vector of all 0-based integer start offsets of `needle` in `haystack`."
  [^String haystack ^String needle]
  (if (or (str/blank? haystack) (str/blank? needle))
    []
    (loop [res []
           start 0]
      (let [idx (.indexOf haystack needle (int start))]
        (if (= idx -1)
          res
          (recur (conj res idx) (unchecked-add (int idx) 1)))))))

(defn locate
  "Deterministic code-located anchor calculation (T-438 / EschaBot Req 4).
   Returns a location map:
     :location/source-sha256  SHA-256 digest of the unnormalized primary corpus
     :location/region         Target region coordinate (defaults to :body)
     :location/start          0-based start offset in normalized corpus (nil if 0 occurrences)
     :location/end            0-based end offset in normalized corpus (nil if 0 occurrences)
     :location/char-count     Length of the normalized witness
     :location/occurrences    Total count of occurrences in normalized corpus
     :location/unique?        true iff occurrences == 1"
  ([corpus witness]
   (locate corpus witness :body))
  ([corpus witness region]
   (let [c-sha (sha256 corpus)
         norm-c (normalize corpus)
         norm-w (normalize witness)
         w-len (count norm-w)
         occs (find-all-occurrences norm-c norm-w)
         cnt (count occs)
         first-idx (first occs)]
     {:location/source-sha256 c-sha
      :location/region region
      :location/start first-idx
      :location/end (when first-idx (+ first-idx w-len))
      :location/char-count w-len
      :location/occurrences cnt
      :location/unique? (= cnt 1)})))

(defn exact-contiguous?
  "true iff normalized witness is an exact contiguous substring of the
   normalized primary corpus. Boundaries are arbitrary by construction —
   any offset at which the witness occurs is acceptable."
  [corpus witness]
  (pos? (:location/occurrences (locate corpus witness))))

;; --- Witness verification ---

(defn verify-witness
  "Classifies a witness against the primary corpus.
   Returns a map with:
     :witness/text              The input witness string
     :witness/char-count        Character count of unnormalized witness
     :witness/zero-ellipses?    literal '...' absent (necessary, insufficient)
     :witness/exact-contiguous? exact substring of corpus (decidable gate)
     :witness/offset            first offset in normalized corpus (nil if absent)
     :witness/occurrences       number of occurrences found in corpus
     :witness/unique?           true iff exactly one occurrence exists
     :witness/location          complete deterministic location record
     :witness/verdict           :verbatim | :truncated | :invented"
  [corpus witness]
  (let [zero-ellipses? (not (str/includes? (or witness "") "..."))
        loc (locate corpus witness)
        exact? (pos? (:location/occurrences loc))]
    {:witness/text witness
     :witness/char-count (count (or witness ""))
     :witness/zero-ellipses? zero-ellipses?
     :witness/exact-contiguous? exact?
     :witness/offset (:location/start loc)
     :witness/occurrences (:location/occurrences loc)
     :witness/unique? (:location/unique? loc)
     :witness/location loc
     :witness/verdict (cond
                        exact? :verbatim
                        zero-ellipses? :invented
                        :else :truncated)}))

(defn verify-witnesses
  "Vector of verification maps for a sequence of witnesses."
  [corpus witnesses]
  (mapv (partial verify-witness corpus) witnesses))

(defn verbatim-register?
  "true iff every witness in the sequence is verdict :verbatim —
   i.e. the whole sequence is a valid selection from the primary corpus."
  [verifications]
  (and (seq verifications)
       (every? #(= :verbatim (:witness/verdict %)) verifications)))

(defn boundary-arbitrary?
  "Demonstrates edge arbitrariness: two witnesses that overlap or nest are
   both valid selections iff each is exact-contiguous. Returns true iff
   both pass the empirical gate — the boundary choice is not constrained."
  [corpus witness-a witness-b]
  (and (exact-contiguous? corpus witness-a)
       (exact-contiguous? corpus witness-b)))
