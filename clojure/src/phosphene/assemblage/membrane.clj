(ns phosphene.assemblage.membrane
  "Epistemic Membrane Egress Gate & Closed Outbound Specs (T-440).

   Implements the boundary contract between exploratory Space-O (open world,
   permissive accretion, bare (s/keys)) and verifiable Space-D (closed world,
   fail-closed security, seL4 capability boundary).
   
   Adheres to EschaBot Requirements 10, 11, 12, 13, 14:
   - Req 10: Fail-closed egress gate rejecting unmodeled attributes at all depths.
   - Req 11: Elimination of :legacy-form and :open-map shims from export specs.
   - Req 12: Full formal specification of genealogical extensions (:genealogy/roots,
             :genealogy/coined-by, :genealogy/wisdom-synthesis).
   - Req 13: Strict sovereign ID derivation from YAML frontmatter (refusal on missing).
   - Req 14: Typed Cognitect anomaly generation (:cognitect.anomalies/incorrect,
             :cognitect.anomalies/conflict) on validation failures or collisions."
  (:require [clojure.spec.alpha :as s]
            [clojure.string :as str]
            [clojure.set :as set]
            [phosphene.assemblage.spec :as spec]
            [phosphene.assemblage.ast :as ast]
            [phosphene.assemblage.lens :as lens]
            [phosphene.assemblage.witness :as witness]))

;; --- Anomaly Constructors ---

(defn anomaly
  "Constructs a standard Cognitect-compatible anomaly map."
  ([category message]
   (anomaly category message nil))
  ([category message context]
   (cond-> {:cognitect.anomalies/category category
            :anomaly/message message}
     context (assoc :anomaly/context context))))

(defn anomaly?
  "true iff `x` is a Cognitect anomaly map."
  [x]
  (and (map? x) (contains? x :cognitect.anomalies/category)))

;; --- Recursive Key Closure Predicate (closed-keys?) ---

(defn closed-keys?
  "Higher-order predicate generator. Given a set of allowed keys, returns a predicate
   `f: map? -> boolean` that returns true iff all keys in the map belong to `allowed-keys`."
  [allowed-keys]
  (fn [m]
    (and (map? m)
         (empty? (set/difference (set (keys m)) allowed-keys)))))

;; --- Closed Export Specifications for Egress (Space-D) ---

;; 1. Closed Location Spec
(def closed-location-keys
  #{:location/source-sha256
    :location/region
    :location/start
    :location/end
    :location/char-count
    :location/occurrences
    :location/unique?})

(s/def ::closed-location
  (s/and (s/keys :req [:location/source-sha256
                       :location/region
                       :location/char-count
                       :location/occurrences
                       :location/unique?]
                 :opt [:location/start
                       :location/end])
         (closed-keys? closed-location-keys)))

;; 2. Closed Witness Spec
(def closed-witness-selection-keys
  #{:witness/text
    :witness/folio
    :witness/char-count
    :witness/offset
    :witness/printed-folio
    :witness/doc-page
    :witness/zero-ellipses?
    :witness/exact-contiguous?
    :witness/verdict
    :witness/location})

(s/def ::closed-witness-selection
  (s/and (s/keys :req [:witness/text
                       :witness/zero-ellipses?]
                 :opt [:witness/folio
                       :witness/char-count
                       :witness/offset
                       :witness/printed-folio
                       :witness/doc-page
                       :witness/exact-contiguous?
                       :witness/verdict
                       :witness/location])
         (closed-keys? closed-witness-selection-keys)
         (fn [m]
           (if-let [loc (:witness/location m)]
             (s/valid? ::closed-location loc)
             true))))

(s/def :witness/selection ::closed-witness-selection)

;; 3. Closed Semiotic Triad Specs (Purging :open-map and :legacy-form shims)

;; Seme Firstness: Qualified simple, Unqualified simple, or Rheme qualified
(def closed-seme-simple-qual-keys
  #{:quality/phenomenon :perceptual/metaphor :perceptual/invariant})

(def closed-seme-simple-unqual-keys
  #{:phenomenon :metaphor :invariant})

(def closed-rheme-seme-item-keys
  #{:seme/id :seme/pred :seme/metadata})

(s/def ::closed-rheme-seme-item
  (s/and (s/keys :req [:seme/id :seme/pred]
                 :opt [:seme/metadata])
         (closed-keys? closed-rheme-seme-item-keys)))

(def closed-rheme-qual-keys
  #{:rheme/id :rheme/semes})

(s/def ::closed-rheme-firstness
  (s/and (s/keys :req [:rheme/id :rheme/semes])
         (closed-keys? closed-rheme-qual-keys)
         (fn [m] (s/valid? (s/coll-of ::closed-rheme-seme-item :kind vector?) (:rheme/semes m)))))

(s/def ::closed-seme-firstness
  (s/or :simple-qualified (s/and (s/keys :req [:quality/phenomenon]
                                         :opt [:perceptual/metaphor :perceptual/invariant])
                                 (closed-keys? closed-seme-simple-qual-keys))
        :simple-unqualified (s/and (s/keys :req-un [:quality/phenomenon]
                                           :opt-un [:perceptual/metaphor :perceptual/invariant])
                                   (closed-keys? closed-seme-simple-unqual-keys))
        :rheme-qualified ::closed-rheme-firstness))

(s/def :seme/firstness ::closed-seme-firstness)

;; Pheme Secondness: Qualified simple, Unqualified simple, or Vertex qualified
(def closed-pheme-simple-qual-keys
  #{:pheme/encounter :pheme/historical-anchors})

(def closed-pheme-simple-unqual-keys
  #{:encounter :historical-anchors :data})

(def closed-pheme-vertex-qual-keys
  #{:pheme/claimed-vertex
    :pheme/required-coordinates
    :pheme/empirical-encounter
    :pheme/conformance-status
    :pheme/historical-anchors})

(s/def ::closed-pheme-secondness
  (s/or :simple-qualified (s/and (s/keys :req [:pheme/encounter]
                                         :opt [:pheme/historical-anchors])
                                 (closed-keys? closed-pheme-simple-qual-keys))
        :simple-unqualified (s/and (s/keys :req-un [:pheme/encounter]
                                           :opt-un [:pheme/historical-anchors])
                                   (closed-keys? closed-pheme-simple-unqual-keys))
        :vertex-qualified (s/and (s/keys :req [:pheme/claimed-vertex
                                               :pheme/required-coordinates
                                               :pheme/empirical-encounter]
                                         :opt [:pheme/conformance-status
                                               :pheme/historical-anchors])
                                 (closed-keys? closed-pheme-vertex-qual-keys))))

(s/def :pheme/secondness ::closed-pheme-secondness)

;; Delome Thirdness: Purged of :legacy-form any?
(def closed-delome-map-keys
  #{:delome/invariants
    :delome/habits
    :delome/isomorphisms})

(s/def ::closed-delome-thirdness
  (s/or :declarative-vector (s/coll-of :phosphene.assemblage.spec/delome-clause :kind vector?)
        :declarative-map (s/and (s/keys :opt [:delome/invariants
                                              :delome/habits
                                              :delome/isomorphisms])
                                (closed-keys? closed-delome-map-keys))))

(s/def :delome/thirdness ::closed-delome-thirdness)

;; Term Definitions
(def closed-term-def-qual-keys #{:term/name :term/def})
(def closed-term-def-unqual-keys #{:name :def})

(s/def ::closed-term-definition
  (s/or :qualified (s/and (s/keys :req [:term/name :term/def])
                          (closed-keys? closed-term-def-qual-keys))
        :unqualified (s/and (s/keys :req-un [:term/name :term/def])
                            (closed-keys? closed-term-def-unqual-keys))))

(s/def ::closed-term-definitions
  (s/coll-of ::closed-term-definition :kind vector?))

(s/def :term/definitions ::closed-term-definitions)

;; 4. Closed Semiotic Close-Read Unit Spec
(def closed-close-read-unit-keys
  #{:close-read/id
    :close-read/title
    :close-read/source
    :seme/firstness
    :pheme/secondness
    :delome/thirdness
    :term/definitions
    :witness/selection})

(s/def ::closed-close-read-unit
  (s/and (s/keys :req [:close-read/id
                       :close-read/title
                       :close-read/source
                       :seme/firstness
                       :pheme/secondness
                       :delome/thirdness
                       :term/definitions
                       :witness/selection])
         (closed-keys? closed-close-read-unit-keys)))

;; 5. Closed Genealogical Extension Specs (Req 12)
(s/def :root/author string?)
(s/def :root/year pos-int?)
(s/def :root/source string?)
(s/def :root/journal string?)
(s/def :root/volume (s/or :int pos-int? :str string?))
(s/def :root/pages string?)
(s/def :root/discipline (s/or :kw keyword? :str string?))
(s/def :root/vector (s/or :kw keyword? :str string?))

(def closed-genealogy-root-keys
  #{:author :year :source :journal :volume :pages :discipline :vector})

(s/def ::closed-genealogy-root
  (s/and (s/keys :req-un [:root/author :root/year :root/source]
                 :opt-un [:root/journal :root/volume :root/pages :root/discipline :root/vector])
         (closed-keys? closed-genealogy-root-keys)))

(s/def :genealogy/roots
  (s/coll-of ::closed-genealogy-root :kind vector?))

(s/def :coined/author string?)
(s/def :coined/year pos-int?)
(s/def :coined/term string?)

(def closed-coined-by-keys
  #{:author :year :term})

(s/def ::closed-coined-by
  (s/and (s/keys :req-un [:coined/author :coined/year :coined/term])
         (closed-keys? closed-coined-by-keys)))

(s/def :genealogy/coined-by ::closed-coined-by)

(s/def :screened/author string?)
(s/def :screened/year pos-int?)
(s/def :screened/reason string?)
(s/def :screened/wisdom-query string?)

(def closed-screened-out-keys
  #{:author :year :reason :wisdom-query})

(s/def ::closed-screened-out
  (s/and (s/keys :req-un [:screened/author :screened/year :screened/reason]
                 :opt-un [:screened/wisdom-query])
         (closed-keys? closed-screened-out-keys)))

(s/def :genealogy/screened-out
  (s/coll-of ::closed-screened-out :kind vector?))

(s/def :ref/author string?)
(s/def :ref/year pos-int?)
(s/def :ref/source string?)
(s/def :ref/journal string?)
(s/def :ref/volume (s/or :int pos-int? :str string?))
(s/def :ref/pages string?)
(s/def :ref/role (s/or :kw keyword? :str string?))
(s/def :ref/discipline (s/or :kw keyword? :str string?))
(s/def :ref/citation string?)

(def closed-genealogy-ref-keys
  #{:author :year :source :journal :volume :pages :role :discipline :citation})

(s/def ::closed-genealogy-ref
  (s/and (s/keys :req-un [:ref/author :ref/year :ref/source]
                 :opt-un [:ref/journal :ref/volume :ref/pages :ref/role :ref/discipline :ref/citation])
         (closed-keys? closed-genealogy-ref-keys)))

(s/def ::closed-genealogy-references
  (s/coll-of ::closed-genealogy-ref :kind vector?))

(s/def :exemplar/subject string?)
(s/def :exemplar/sensor string?)
(s/def :exemplar/mechanism string?)
(s/def :exemplar/status (s/or :kw keyword? :str string?))

(def closed-exemplar-keys
  #{:subject :sensor :mechanism :status})

(s/def ::closed-exemplar
  (s/and (s/keys :req-un [:exemplar/subject :exemplar/sensor :exemplar/mechanism :exemplar/status])
         (closed-keys? closed-exemplar-keys)))

(s/def :genealogy/exemplar ::closed-exemplar)

(s/def :genealogy/epistemic-status
  #{:dual-genesis
    :terminological-rectification
    :boundary-demarcation
    :clinical-embodiment
    :open-system-transfer
    :model-ceiling})

(s/def :genealogy/wisdom-synthesis
  #{:dual-origin-convergence
    :elder-discipline-corrects-younger
    :mechanism-conditioned-predicates
    :pathological-bifurcation-confirmation
    :ultrastable-adaptation-by-breakdown
    :histological-loop-grounding
    :mechanistic-epistemology-grounding
    :open-system-electrophysiological-grounding
    :epistemic-ceiling-on-analogy})

(s/def :genealogy/epistemic-delta
  (s/or :kw keyword? :str string?))

(def closed-genealogy-unit-keys
  #{:genealogy/id
    :genealogy/target-claim
    :genealogy/epoch
    :genealogy/epistemic-status
    :genealogy/precursors
    :genealogy/successors
    :genealogy/references
    :genealogy/roots
    :genealogy/coined-by
    :genealogy/wisdom-synthesis
    :genealogy/screened-out
    :genealogy/exemplar
    :genealogy/epistemic-delta})

(s/def ::closed-genealogy-unit
  (s/and (s/keys :req [:genealogy/id
                       :genealogy/target-claim
                       :genealogy/epoch
                       :genealogy/epistemic-status
                       :genealogy/precursors
                       :genealogy/successors]
                 :opt [:genealogy/references
                       :genealogy/roots
                       :genealogy/coined-by
                       :genealogy/wisdom-synthesis
                       :genealogy/screened-out
                       :genealogy/exemplar
                       :genealogy/epistemic-delta])
         (closed-keys? closed-genealogy-unit-keys)))

;; --- Sovereign Frontmatter Identification (Req 13) ---

(defn extract-sovereign-id
  "Derives the canonical sovereign ID strictly from YAML frontmatter metadata.
   Checks `:id` and `:registry.core/id`. Returns nil if neither is present."
  [ast-doc]
  (let [meta (ast/doc-metadata ast-doc)]
    (or (:id meta)
        (:registry.core/id meta))))

;; --- Egress Gate Exporters (Req 10, 11, 13, 14) ---

(defn export-reasoning-unit
  "Exports a parsed reasoning unit AST across the epistemic membrane into Space-D.
   Validates against ::closed-close-read-unit.
   Fails closed, returning typed Cognitect anomalies on:
   - Nil/empty document (:cognitect.anomalies/incorrect)
   - Missing sovereign ID in YAML frontmatter (:cognitect.anomalies/incorrect)
   - Merge collision between frontmatter and code ID (:cognitect.anomalies/conflict)
   - Spec non-conformance or unmodeled keys (:cognitect.anomalies/incorrect)"
  [ast-doc]
  (if (or (nil? ast-doc) (empty? ast-doc))
    (anomaly :cognitect.anomalies/incorrect "Input AST document is nil or empty")
    (let [frontmatter-id (extract-sovereign-id ast-doc)]
      (if (str/blank? (str frontmatter-id))
        (anomaly :cognitect.anomalies/incorrect
                 "Sovereign identifier missing from YAML frontmatter (violation of seL4 capability root)"
                 {:doc-metadata (ast/doc-metadata ast-doc)})
        (let [raw-edn (lens/extract-code-edn ast-doc)]
          (cond
            (nil? raw-edn)
            (anomaly :cognitect.anomalies/incorrect "No valid EDN blocks found in reasoning unit" {:id frontmatter-id})

            (:anomaly/type raw-edn)
            (anomaly :cognitect.anomalies/incorrect "Malformed EDN syntax in reasoning unit" raw-edn)

            ;; Check for ID collision
            (and (:close-read/id raw-edn)
                 (not= (str (:close-read/id raw-edn)) (str frontmatter-id)))
            (anomaly :cognitect.anomalies/conflict
                     "Sovereign ID conflict: frontmatter ID differs from embedded EDN ID"
                     {:frontmatter-id frontmatter-id
                      :embedded-id (:close-read/id raw-edn)})

            :else
            (let [doc-meta (ast/doc-metadata ast-doc)
                  exported (cond-> raw-edn
                             true (assoc :close-read/id frontmatter-id)
                             (:title doc-meta) (assoc :close-read/title (:title doc-meta))
                             (:source doc-meta) (assoc :close-read/source (:source doc-meta)))]
              (if (s/valid? ::closed-close-read-unit exported)
                exported
                (anomaly :cognitect.anomalies/incorrect
                         "Egress validation failed: reasoning unit violates closed Space-D spec"
                         {:explain (s/explain-data ::closed-close-read-unit exported)
                          :unmodeled-keys (set/difference (set (keys exported)) closed-close-read-unit-keys)})))))))))

(defn export-genealogy-unit
  "Exports a parsed genealogy unit AST across the epistemic membrane into Space-D.
   Validates against ::closed-genealogy-unit.
   Fails closed with typed Cognitect anomalies on violations."
  [ast-doc]
  (if (or (nil? ast-doc) (empty? ast-doc))
    (anomaly :cognitect.anomalies/incorrect "Input AST document is nil or empty")
    (let [frontmatter-id (extract-sovereign-id ast-doc)]
      (if (str/blank? (str frontmatter-id))
        (anomaly :cognitect.anomalies/incorrect
                 "Sovereign identifier missing from YAML frontmatter"
                 {:doc-metadata (ast/doc-metadata ast-doc)})
        (let [raw-edn (lens/project-genealogy-metadata ast-doc)]
          (cond
            (nil? raw-edn)
            (anomaly :cognitect.anomalies/incorrect "No valid genealogy metadata found in code blocks" {:id frontmatter-id})

            ;; Collision check
            (and (:genealogy/id raw-edn)
                 (not= (str (:genealogy/id raw-edn)) (str frontmatter-id)))
            (anomaly :cognitect.anomalies/conflict
                     "Sovereign ID conflict: frontmatter ID differs from embedded genealogy ID"
                     {:frontmatter-id frontmatter-id
                      :embedded-id (:genealogy/id raw-edn)})

            :else
            (let [exported (assoc raw-edn :genealogy/id frontmatter-id)]
              (if (s/valid? ::closed-genealogy-unit exported)
                exported
                (anomaly :cognitect.anomalies/incorrect
                         "Egress validation failed: genealogy unit violates closed Space-D spec"
                         {:explain (s/explain-data ::closed-genealogy-unit exported)
                          :unmodeled-keys (set/difference (set (keys exported)) closed-genealogy-unit-keys)})))))))))

(defn export-unit
  "Polymorphic egress gate dispatcher. Inspects the AST document metadata,
   routes to either `export-reasoning-unit` or `export-genealogy-unit`,
   and enforces closed-keys validation at the boundary."
  [ast-doc]
  (let [meta (ast/doc-metadata ast-doc)]
    (cond
      (= "genealogy" (:line meta))
      (export-genealogy-unit ast-doc)

      :else
      (export-reasoning-unit ast-doc))))
