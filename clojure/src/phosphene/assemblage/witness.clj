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

;; --- Token-preserving normalization ---

(defn normalize
  "Token-preserving normalization applied identically to corpus and witness.
   Only OCR/printing artifacts of SPACING and GLYPH VARIANT are collapsed;
   no token is added, removed, substituted, or reordered:
   1. Canonicalize dash glyphs: em/en dash -> hyphen (the JSTOR scan
      renders the em dash as a hyphen; this is a glyph variant).
   2. Join hyphenated line breaks keeping the hyphen: \"feed- back\" ->
      \"feed-back\" (the scan breaks compounds at line ends).
   3. Strip whitespace adjacent to quote glyphs (the scan pads quotes:
      \" ' positive ' \" is the token stream 'positive').
   4. Canonicalize superscript footnote digits to plain digits
      (superscript one/two/three -> 1/2/3), matching the corpus's
      own footnote convention.
   5. Collapse all whitespace runs to a single space."
  [s]
  (-> s
      (str/replace #"[\u2013\u2014]" "-")
      (str/replace #"\u00b9" "1")
      (str/replace #"\u00b2" "2")
      (str/replace #"\u00b3" "3")
      (str/replace #"\u2074" "4")
      (str/replace #"-\s+" "-")
      (str/replace #" +(['\"])" "$1")
      (str/replace #"(['\"])+ +" "$1")
      (str/replace #"\s+" " ")
      str/trim))

;; --- The empirical verbatim predicate ---

(defn exact-contiguous?
  "true iff normalized witness is an exact contiguous substring of the
   normalized primary corpus. Boundaries are arbitrary by construction —
   any offset at which the witness occurs is acceptable."
  [corpus witness]
  (str/includes? (normalize corpus) (normalize witness)))

(defn locate
  "Returns the integer offset of the witness in the normalized corpus,
   or nil if absent. Multiple occurrences possible; returns the first."
  [corpus witness]
  (str/index-of (normalize corpus) (normalize witness)))

;; --- Witness verification ---

(defn verify-witness
  "Classifies a witness against the primary corpus.
   Returns a map with:
     :witness/zero-ellipses?    literal '...' absent (necessary, insufficient)
     :witness/exact-contiguous? exact substring of corpus (decidable gate)
     :witness/offset            first offset in normalized corpus (nil if absent)
     :witness/verdict           :verbatim | :truncated | :invented"
  [corpus witness]
  (let [zero-ellipses? (not (str/includes? witness "..."))
        offset (locate corpus witness)]
    {:witness/text witness
     :witness/char-count (count witness)
     :witness/zero-ellipses? zero-ellipses?
     :witness/exact-contiguous? (some? offset)
     :witness/offset offset
     :witness/verdict (cond
                        (some? offset) :verbatim
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
