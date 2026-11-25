(ns phosphene.assemblage.spec
  "Formal specification and property invariants for phosphene.assemblage."
  (:require [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as gen]))

;; --- Sovereign Attribute & Entity Specifications ---

(s/def :node/id
  (s/or :kw keyword? :str string? :sym symbol?))

(s/def :node/traits
  (s/coll-of (s/or :kw keyword? :str string?) :kind set?))

(s/def :node/level
  (s/and int? (complement neg?)))

(s/def :assemblage/node
  (s/keys :req-un [:node/id :node/traits :node/level]))

(s/def :edge/src :node/id)
(s/def :edge/dst :node/id)
(s/def :edge/kind
  #{:genealogical :transversal-cross-link :feedback-cycle :functional :teleological})

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
