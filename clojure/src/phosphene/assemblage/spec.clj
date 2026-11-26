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
  (s/or :qualified (s/keys :req [:node/id :node/traits :node/level])
        :unqualified (s/keys :req-un [:node/id :node/traits :node/level])))

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
  (s/or :qualified (s/keys :req [:assemblage/nodes :assemblage/edges :assemblage/hierarchy])
        :unqualified (s/keys :req-un [:assemblage/nodes :assemblage/edges :assemblage/hierarchy])))

;; --- Delome Thirdness Declarative Grammar (CLJ-007 / T-427) ---
;; Purged of executable Clojure code (do, defn, s/def).
;; Thirdness is Peircean Governing Habit, represented as an inert sequential AST:
;;   - (invariant :coord predicate)
;;   - (rule :rule-id expression)
;;   - (isomorphism :name mapping-map)
;;   - (taxonomy :name category-map)

(s/def :delome.invariant/tag #{'invariant})
(s/def :delome.invariant/coord (s/or :kw qualified-keyword? :simple-kw keyword?))
(s/def :delome.invariant/predicate any?)

(s/def ::delome-invariant
  (s/cat :tag :delome.invariant/tag
         :coord :delome.invariant/coord
         :predicate :delome.invariant/predicate))

(s/def :delome.rule/tag #{'rule})
(s/def :delome.rule/id (s/or :kw keyword? :sym symbol?))
(s/def :delome.rule/body any?)

(s/def ::delome-rule
  (s/cat :tag :delome.rule/tag
         :id :delome.rule/id
         :body :delome.rule/body))

(s/def :delome.taxonomy/tag #{'taxonomy})
(s/def :delome.taxonomy/name (s/or :kw keyword? :sym symbol?))
(s/def :delome.taxonomy/body (s/or :map map? :coll vector?))

(s/def ::delome-taxonomy
  (s/cat :tag :delome.taxonomy/tag
         :name :delome.taxonomy/name
         :body :delome.taxonomy/body))

(s/def :delome.isomorphism/tag #{'isomorphism})
(s/def :delome.isomorphism/name (s/or :kw keyword? :sym symbol?))
(s/def :delome.isomorphism/body map?)

(s/def ::delome-isomorphism
  (s/cat :tag :delome.isomorphism/tag
         :name :delome.isomorphism/name
         :body :delome.isomorphism/body))

(s/def ::delome-clause
  (s/or :invariant ::delome-invariant
        :rule ::delome-rule
        :taxonomy ::delome-taxonomy
        :isomorphism ::delome-isomorphism
        :generic-form seq?))

(s/def ::delome-thirdness
  (s/or :declarative-vector (s/coll-of ::delome-clause :kind vector?)
        :declarative-map (s/keys :opt [:delome/invariants :delome/habits :delome/isomorphisms])
        :legacy-form any?))

(s/def :delome/thirdness ::delome-thirdness)

;; --- Semiotic Close-Read Triad Specifications ---
;; Conforms to clj-spec v1.2-Peirce-Hume-Omega supporting both qualified and unqualified keys
;; and multi-paradigm Seme/Pheme representations (Peircean Rheme and Poset Vertices).

(s/def :close-read/id (s/or :str string? :kw keyword?))
(s/def :close-read/title (s/nilable string?))
(s/def :close-read/source (s/nilable (s/or :str string? :map map?)))

(s/def :quality/phenomenon (s/or :kw keyword? :str string? :vec vector?))
(s/def :perceptual/metaphor (s/or :str string? :vec vector?))
(s/def :perceptual/invariant (s/or :kw keyword? :str string? :vec vector?))

(s/def :rheme/id keyword?)
(s/def :rheme/semes (s/coll-of map? :kind vector?))

(s/def :seme/firstness
  (s/or :simple-qualified (s/keys :req [:quality/phenomenon] :opt [:perceptual/metaphor :perceptual/invariant])
        :simple-unqualified (s/keys :req-un [:quality/phenomenon] :opt-un [:perceptual/metaphor :perceptual/invariant])
        :rheme-qualified (s/keys :req [:rheme/id :rheme/semes])
        :open-map (s/and map? #(or (contains? % :quality/phenomenon)
                                   (contains? % :phenomenon)
                                   (contains? % :rheme/id)
                                   (seq %)))))

(s/def :pheme/encounter (s/or :kw keyword? :str string?))
(s/def :pheme/historical-anchors (s/coll-of (s/or :kw keyword? :str string?) :kind coll?))
(s/def :pheme/claimed-vertex keyword?)
(s/def :pheme/required-coordinates (s/coll-of keyword? :kind vector?))
(s/def :pheme/empirical-encounter map?)
(s/def :pheme/conformance-status keyword?)

(s/def :pheme/secondness
  (s/or :simple-qualified (s/keys :req [:pheme/encounter] :opt [:pheme/historical-anchors])
        :simple-unqualified (s/keys :req-un [:pheme/encounter] :opt-un [:pheme/historical-anchors])
        :vertex-qualified (s/keys :req [:pheme/claimed-vertex :pheme/required-coordinates :pheme/empirical-encounter])
        :open-map (s/and map? #(or (contains? % :pheme/encounter)
                                   (contains? % :encounter)
                                   (contains? % :pheme/claimed-vertex)
                                   (seq %)))))

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

;; --- Markdown AST Specifications (T-637 / T-434 Sovereign Senses) ---
;; Adheres to Mode-D quarantine: sovereign qualified attributes defined first,
;; supporting both Mode-D (qualified attributes) and Mode-S boundary adapters.

(s/def :ast.node/type keyword?)
(s/def :ast.node/content (s/coll-of any? :kind vector?))
(s/def :ast.text/content string?)
(s/def :ast.heading/level (s/int-in 1 7))
(s/def :ast.code/info (s/nilable string?))
(s/def :ast.code/language (s/nilable string?))
(s/def :ast.doc/title (s/nilable string?))
(s/def :ast.doc/toc (s/nilable map?))
(s/def :ast.doc/footnotes (s/nilable (s/coll-of any? :kind vector?)))

;; Unqualified boundary keys (for backward compatibility with nextjournal raw AST)
(s/def :ast/type :ast.node/type)
(s/def :ast/text :ast.text/content)
(s/def :ast/heading-level :ast.heading/level)
(s/def :ast/info :ast.code/info)
(s/def :ast/language :ast.code/language)
(s/def :ast/title :ast.doc/title)
(s/def :ast/toc :ast.doc/toc)
(s/def :ast/footnotes :ast.doc/footnotes)

(s/def :ast.node/text-node
  (s/or :qualified (s/keys :req [:ast.node/type :ast.text/content])
        :unqualified (s/keys :req-un [:ast/type :ast/text])))

(s/def :ast.node/heading-node
  (s/or :qualified (s/keys :req [:ast.node/type :ast.heading/level :ast.node/content])
        :unqualified (s/keys :req-un [:ast/type :ast/heading-level :ast/content])))

(s/def :ast.node/code-node
  (s/or :qualified (s/keys :req [:ast.node/type :ast.node/content]
                           :opt [:ast.code/info :ast.code/language])
        :unqualified (s/keys :req-un [:ast/type :ast/content]
                             :opt-un [:ast/info :ast/language])))

(s/def :ast/content
  (s/coll-of (s/or :text :ast.node/text-node
                   :heading :ast.node/heading-node
                   :code :ast.node/code-node
                   :generic (s/or :qualified (s/keys :req [:ast.node/type])
                                  :unqualified (s/keys :req-un [:ast/type])))
             :kind vector?))

(s/def :ast/doc
  (s/or :qualified (s/keys :req [:ast.node/type :ast.node/content]
                           :opt [:ast.doc/title :ast.doc/toc :ast.doc/footnotes])
        :unqualified (s/keys :req-un [:ast/type :ast/content]
                             :opt-un [:ast/title :ast/toc :ast/footnotes])))


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
