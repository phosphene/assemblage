(ns phosphene.assemblage.core
  "Core data structures and pure functions for representing assemblages,
   genealogical branching, cross-links, and transversal trait inheritance."
  (:require [clojure.set :as set]))

;; --- Pure Data Constructors ---

(defn make-assemblage
  "Create an empty assemblage data structure."
  []
  {:nodes {}       ;; id -> {:traits #{}, :level 0}
   :edges #{}      ;; #{[src dst kind]}
   :hierarchy {}}) ;; parent-id -> #{child-ids}

(defn add-node
  "Add a node with an optional set of traits and hierarchical level."
  ([asm id]
   (add-node asm id #{} 0))
  ([asm id traits level]
   (assoc-in asm [:nodes id] {:id id
                              :traits (set traits)
                              :level (or level 0)})))

(defn add-relation
  "Add a directed relation between src and dst with an explicit relation kind
   (e.g., :genealogical, :transversal-cross-link, :feedback-cycle)."
  [asm src dst kind]
  {:pre [(contains? (:nodes asm) src)
         (contains? (:nodes asm) dst)]}
  (update asm :edges conj [src dst kind]))

(defn transversal-cross-links
  "Return all relations of kind :transversal-cross-link."
  [asm]
  (filter (fn [[_ _ kind]] (= kind :transversal-cross-link)) (:edges asm)))

(defn shared-traits
  "Find the intersection of traits between two nodes in the assemblage."
  [asm id-a id-b]
  (let [traits-a (get-in asm [:nodes id-a :traits] #{})
        traits-b (get-in asm [:nodes id-b :traits] #{})]
    (set/intersection traits-a traits-b)))

(defn cycles
  "Detect simple 2-node cycles in the relation set."
  [asm]
  (let [edges (:edges asm)]
    (for [[u v k1] edges
          [v2 u2 k2] edges
          :when (and (= u u2) (= v v2) (not= u v))]
      #{[u v k1] [v u k2]})))
