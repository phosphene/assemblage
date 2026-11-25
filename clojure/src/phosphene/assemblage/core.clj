(ns phosphene.assemblage.core
  "Core data structures and pure functions for representing assemblages,
   genealogical branching, cross-links, and transversal trait inheritance."
  (:require [clojure.set :as set]
            [clojure.spec.alpha :as s]
            [phosphene.assemblage.spec :as aspec]))

;; --- Pure Data Constructors & Mutations ---

(defn make-assemblage
  "Create an empty assemblage data structure conforming to ::aspec/assemblage."
  []
  {:nodes {}
   :edges #{}
   :hierarchy {}})

(s/fdef make-assemblage
  :args (s/cat)
  :ret ::aspec/assemblage)

(defn add-node
  "Add a node with an optional set of traits and hierarchical level."
  ([asm id]
   (add-node asm id #{} 0))
  ([asm id traits]
   (add-node asm id traits 0))
  ([asm id traits level]
   (assoc-in asm [:nodes id] {:id id
                              :traits (set traits)
                              :level (or level 0)})))

(s/fdef add-node
  :args (s/cat :asm ::aspec/assemblage
               :id :node/id
               :traits (s/? :node/traits)
               :level (s/? :node/level))
  :ret ::aspec/assemblage)

(defn add-relation
  "Add a directed relation between src and dst with an explicit relation kind
   (e.g., :genealogical, :transversal-cross-link, :feedback-cycle)."
  [asm src dst kind]
  {:pre [(contains? (:nodes asm) src)
         (contains? (:nodes asm) dst)]}
  (update asm :edges conj [src dst kind]))

(s/fdef add-relation
  :args (s/cat :asm ::aspec/assemblage
               :src :node/id
               :dst :node/id
               :kind :edge/kind)
  :ret ::aspec/assemblage)

(defn transversal-cross-links
  "Return all relations of kind :transversal-cross-link."
  [asm]
  (filter (fn [[_ _ kind]] (= kind :transversal-cross-link)) (:edges asm)))

(s/fdef transversal-cross-links
  :args (s/cat :asm ::aspec/assemblage)
  :ret (s/coll-of :assemblage/edge))

(defn shared-traits
  "Find the intersection of traits between two nodes in the assemblage."
  [asm id-a id-b]
  (let [traits-a (get-in asm [:nodes id-a :traits] #{})
        traits-b (get-in asm [:nodes id-b :traits] #{})]
    (set/intersection traits-a traits-b)))

(s/fdef shared-traits
  :args (s/cat :asm ::aspec/assemblage :id-a :node/id :id-b :node/id)
  :ret set?)

(defn cycles
  "Detect simple 2-node cycles in the relation set."
  [asm]
  (let [edges (:edges asm)]
    (set (for [[u v k1] edges
               [v2 u2 k2] edges
               :when (and (= u u2) (= v v2) (not= u v))]
           #{[u v k1] [v u k2]}))))

(s/fdef cycles
  :args (s/cat :asm ::aspec/assemblage)
  :ret set?)
