(ns phosphene.assemblage.spec
  "Formal specification and property invariants for phosphene.assemblage.
   
   Adheres to Phosphene Quality Care Package (#102415) and Registry Specification:
   - Attribute-Centric Granularity: Atomic namespaced attributes defined first.
   - Open-World Maps: Structural schemas use s/keys, permitting unmodeled extension keys.
   - Named Paths: Disjunctions and sequences use named alternatives (s/or, s/cat).
   - Relational Function Specs: s/fdef contracts check input/output algebraic invariants."
  (:require [clojure.spec.alpha :as s]
            [clojure.string :as str]
            [clojure.spec.gen.alpha :as gen]))

;; --- Sovereign Attribute Specifications ---

(s/def :node/id
  (s/or :kw keyword? :str string? :sym symbol?))

(s/def :node/traits
  (s/coll-of (s/or :kw keyword? :str string?) :kind set?))

(s/def :node/level
  (s/and int? (complement neg?)))

;; --- Aggregate Entity Specifications (Open World) ---

(s/def :assemblage/node
  (s/keys :req-un [:node/id :node/traits :node/level]))

(s/def :edge/src :node/id)
(s/def :edge/dst :node/id)
(s/def :edge/kind
  #{:genealogical :transversal-cross-link :feedback-cycle :functional :teleological :historical-lineage})

(s/def :assemblage/edge
  (s/tuple :edge/src :edge/dst :edge/kind))

(s/def :assemblage/nodes
  (s/map-of :node/id :assemblage/node))

(s/def :assemblage/edges
  (s/coll-of :assemblage/edge :kind set?))

(s/def :assemblage/hierarchy
  (s/map-of :node/id (s/coll-of :node/id :kind set?)))

(s/def ::assemblage
  (s/keys :req-un [:assemblage/nodes :assemblage/edges :assemblage/hierarchy]))

;; --- Semiotic Close-Read Triad Specifications ---
;; Conforms to clj-spec v1.2-Peirce-Hume-Omega supporting both qualified and unqualified keys

(s/def :close-read/id (s/or :str string? :kw keyword?))
(s/def :close-read/title (s/nilable string?))
(s/def :close-read/source (s/nilable string?))

(s/def :quality/phenomenon (s/or :kw keyword? :str string? :vec vector?))
(s/def :perceptual/metaphor (s/or :str string? :vec vector?))

(s/def :seme/firstness
  (s/or :qualified (s/keys :req [:quality/phenomenon] :opt [:perceptual/metaphor])
        :unqualified (s/keys :req-un [:quality/phenomenon] :opt-un [:perceptual/metaphor])))

(s/def :pheme/encounter (s/or :kw keyword? :str string?))
(s/def :pheme/historical-anchors (s/coll-of (s/or :kw keyword? :str string?) :kind vector?))

(s/def :pheme/secondness
  (s/or :qualified (s/keys :req [:pheme/encounter] :opt [:pheme/historical-anchors])
        :unqualified (s/keys :req-un [:pheme/encounter] :opt-un [:pheme/historical-anchors])))

(s/def :term/name keyword?)
(s/def :term/def string?)
(s/def :term/definition
  (s/or :qualified (s/keys :req [:term/name :term/def])
        :unqualified (s/keys :req-un [:term/name :term/def])))

(s/def :term/definitions
  (s/coll-of :term/definition :kind vector?))

(s/def ::close-read-triad
  (s/keys :req [:close-read/id :seme/firstness :pheme/secondness]
          :opt [:close-read/title :close-read/source :delome/thirdness :term/definitions]))

;; --- Markdown AST Specifications (T-637 / Initiative I-012) ---

(s/def :ast/type keyword?)
(s/def :ast/text string?)
(s/def :ast/heading-level (s/int-in 1 7))

(s/def :ast.node/text-node
  (s/keys :req-un [:ast/type :ast/text]))

(s/def :ast.node/heading-node
  (s/keys :req-un [:ast/type :ast/heading-level :ast/content]))

(s/def :ast.node/code-node
  (s/keys :req-un [:ast/type :ast/content]
          :opt-un [:ast/info :ast/language]))

(s/def :ast/content
  (s/coll-of (s/or :text :ast.node/text-node
                   :heading :ast.node/heading-node
                   :code :ast.node/code-node
                   :generic (s/keys :req-un [:ast/type]))
             :kind vector?))

(s/def :ast/doc
  (s/keys :req-un [:ast/type :ast/content]
          :opt-un [:ast/title :ast/toc :ast/footnotes]))


;; --- Verbatim Witness Specifications (:convention/epistemic-witness) ---

(s/def :witness/text (s/and string? (complement str/blank?)))
(s/def :witness/char-count (s/and int? pos?))
(s/def :witness/zero-ellipses? boolean?)

(s/def ::verbatim-witness
  (s/keys :req [:witness/text :witness/char-count :witness/zero-ellipses?]))

(s/def ::verbatim-witnesses
  (s/coll-of ::verbatim-witness :kind vector?))

;; --- Verbatim Register (empirical selection gate, Topic 102248 #102484) ---
;; A witness in the verbatim register is an exact contiguous token selection
;; from the primary corpus. Boundaries are arbitrary; invention and truncation
;; inside the sequence are forbidden. Verification is decidable:
;; witness/exact-contiguous? is computed against the primary corpus in
;; phosphene.assemblage.witness (see assemblages/wisdom-1951/primary/).

(s/def :witness/exact-contiguous? boolean?)
(s/def :witness/offset (s/nilable nat-int?))
(s/def :witness/verdict #{:verbatim :truncated :invented})

(s/def ::verified-verbatim-witness
  (s/keys :req [:witness/text :witness/char-count :witness/zero-ellipses?
                :witness/exact-contiguous? :witness/offset :witness/verdict]))

(s/def ::verbatim-register
  (s/coll-of (s/and ::verified-verbatim-witness
                    #(= :verbatim (:witness/verdict %)))
             :kind vector?))
