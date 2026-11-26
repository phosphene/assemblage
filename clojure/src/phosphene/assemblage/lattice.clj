(ns phosphene.assemblage.lattice
  "Poset Lattice and Predicative Tension Ledger (T-429).
   
   Integrates `phosphene.seme.pheme` vertex subsumption, meet/join poset operations,
   and retraction/provocation proofs with the Assemblage knowledge graph.
   
   Adheres to:
   - Rich Hickey's open-world specification invariants (CLJ-002)
   - C. S. Peirce's triadic Secondness (encounter/conformance witness)
   - J. O. Wisdom's negative method of analogy (provocation & residual tracking)"
  (:require [clojure.set :as set]
            [clojure.spec.alpha :as s]
            [phosphene.seme.pheme :as pheme]))

;; --- Analytical Vertex Projection from Assemblage Units ---

(defn unit->vertex
  "Projects an assemblage unit of reason into an analytical pheme vertex.
   The required coordinates are derived from conformed traits or explicit required specs;
   optional coordinates capture open-world relational properties."
  ([unit]
   (unit->vertex unit (:unit/traits unit #{})))
  ([unit req-traits]
   (let [unit-id (if (keyword? (:unit/id unit))
                   (:unit/id unit)
                   (keyword "unit" (str (:unit/id unit))))
         traits (:unit/traits unit #{})
         req (set req-traits)
         opt (set/difference traits req)
         meta-info {:unit/title (:unit/title unit)
                    :unit/anchors (:unit/anchors unit)
                    :unit/meta (:unit/meta unit)}]
     (pheme/vertex unit-id req opt meta-info))))

;; --- Poset Subsumption & Trait Orderings ---

(defn trait-subsumes?
  "Returns true iff vertex-a structurally subsumes vertex-b in the poset.
   In keyset lattices: A subsumes B iff req(B) ⊆ req(A) (A requires all that
   B requires plus more; any referent satisfying A satisfies B)."
  [vertex-a vertex-b]
  (pheme/subsumes? vertex-a vertex-b))

(defn unit-meet
  "Computes the greatest lower bound (Meet, ∧) of two units in the trait lattice.
   Returns an analytical vertex representing their maximal shared consensus."
  [unit-a unit-b]
  (let [va (if (pheme/vertex? unit-a) unit-a (unit->vertex unit-a))
        vb (if (pheme/vertex? unit-b) unit-b (unit->vertex unit-b))]
    (pheme/vertex-meet va vb)))

(defn unit-join
  "Computes the least upper bound (Join, ∨) of two units in the trait lattice.
   Returns an analytical vertex representing their composite requirement."
  [unit-a unit-b]
  (let [va (if (pheme/vertex? unit-a) unit-a (unit->vertex unit-a))
        vb (if (pheme/vertex? unit-b) unit-b (unit->vertex unit-b))]
    (pheme/vertex-join va vb)))

;; --- Predicative Tension Ledger ---

(defn evaluate-tension
  "Evaluates an empirical referent (e.g. biological organism, close-read model)
   against a theoretical vertex (e.g. Ashby deterministic machine, Cartesian reflex).
   
   If the referent satisfies all required coordinates:
     returns nil (no tension, isomorphic within model scope).
   If one or more coordinates are falsified or unmodeled:
     returns a proof-carrying tension record containing:
       - :tension/type              :structural-provocation
       - :tension/theoretical-model claimed vertex ID
       - :tension/retracted-vertex  maximal sound lower bound vertex record
       - :tension/retracted-model   retracted vertex ID
       - :tension/falsified-axioms  axioms violated by empirical referent
       - :tension/preserved-axioms  axioms confirmed by empirical referent
       - :tension/anomalies         explain-data or missing keys
       - :tension/residual-metric   ratio of falsified to claimed axioms"
  [theoretical-vertex empirical-referent]
  (let [va (if (pheme/vertex? theoretical-vertex)
             theoretical-vertex
             (unit->vertex theoretical-vertex))
        provocation (pheme/lattice-provocation va empirical-referent)]
    (when provocation
      (let [falsified (:provocation/provoked-axioms provocation)
            preserved (:provocation/preserved-axioms provocation)
            total (+ (count falsified) (count preserved))
            residual (if (pos? total) (double (/ (count falsified) total)) 0.0)
            retraction (:provocation/retraction provocation)
            retracted-v (:retraction/retracted-vertex retraction)]
        {:tension/type :structural-provocation
         :tension/theoretical-model (:provocation/claimed-vertex provocation)
         :tension/retracted-model (:provocation/retracted-vertex provocation)
         :tension/retracted-vertex retracted-v
         :tension/falsified-axioms falsified
         :tension/preserved-axioms preserved
         :tension/anomalies (:retraction/anomalies retraction)
         :tension/residual-metric residual}))))

(defn tension-ledger
  "Computes the tension ledger across a collection of theoretical models
   and empirical referents. Returns a vector of all detected tensions."
  [models-and-referents]
  (->> models-and-referents
       (keep (fn [{:keys [model referent]}]
               (evaluate-tension model referent)))
       vec))
