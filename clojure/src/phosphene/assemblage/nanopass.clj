(ns phosphene.assemblage.nanopass
  "Monotonic Tree-Rewriting Engine & Nanopass Pipeline.
   Uses phosphene.stanza.zipper to perform surgical subtree transformations
   over CommonMark ASTs while preserving surrounding expository prose, layout,
   and unmodeled metadata under the Open-World Assumption (OWA)."
  (:require [clojure.zip :as z]
            [phosphene.stanza.zipper :as azip]
            [phosphene.assemblage.ast :as ast]
            [phosphene.assemblage.witness :as witness]))

(defn ast-zip
  "Convenience wrapper around stanza's ast-zip."
  [ast-root]
  (azip/ast-zip ast-root))

(defn find-node
  "Finds the first node matching predicate `pred` in depth-first zipper order."
  [loc pred]
  (loop [curr loc]
    (cond
      (z/end? curr) nil
      (pred (z/node curr)) curr
      :else (recur (z/next curr)))))

(defn update-node
  "Surgically updates the first node matching `pred` using `(f node & args)`
   and unzips back to the root AST. Preserves 100% of the surrounding tree."
  [ast-root pred f & args]
  (let [loc (azip/ast-zip ast-root)]
    (if-let [target-loc (find-node loc pred)]
      (z/root (apply z/edit target-loc f args))
      ast-root)))

(defn replace-node
  "Surgically replaces the first node matching `pred` with `new-node`
   and unzips back to the root AST."
  [ast-root pred new-node]
  (let [loc (azip/ast-zip ast-root)]
    (if-let [target-loc (find-node loc pred)]
      (z/root (z/replace target-loc new-node))
      ast-root)))
