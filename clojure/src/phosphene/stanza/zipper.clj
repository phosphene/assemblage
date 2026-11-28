(ns phosphene.stanza.zipper
  "Tree-Zipper Abstraction for Markdown AST manipulation (Huet Zipper).
   
   Provides a pure functional cursor over hierarchical Markdown ASTs:
   - Wraps standard `clojure.zip` navigation (down, up, left, right).
   - Locates target nodes by coordinate (:statement/id, :close-read/id, :chiasm/id, etc.).
   - Modifies or replaces target subtrees in isolation.
   - Reconstructs the complete root AST immutably (monotonic enhancement).
   - Operates strictly under the Open-World Assumption (OWA): non-matching nodes
     and unmodeled metadata pass through undisturbed."
  (:require [clojure.zip :as z]
            [clojure.string :as str]))

;; --- Tree Shape & Branching Protocol for Markdown AST ---

(defn ast-branch?
  "Returns true if node can have children in the AST.
   Supports both Mode-D (:ast.node/content) and Mode-S (:content) maps, as well as vectors."
  [node]
  (cond
    (map? node) (boolean (or (:content node) (:ast.node/content node)))
    (vector? node) true
    :else false))

(defn ast-children
  "Returns children of a branch node as a sequence."
  [node]
  (cond
    (map? node) (or (:ast.node/content node) (:content node))
    (vector? node) (seq node)
    :else nil))

(defn ast-make-node
  "Constructs a new branch node with modified children."
  [node children]
  (cond
    (map? node)
    (let [child-vec (vec children)]
      (cond-> node
        (contains? node :ast.node/content) (assoc :ast.node/content child-vec)
        (contains? node :content) (assoc :content child-vec)))

    (vector? node)
    (vec children)

    :else node))

(defn ast-zip
  "Creates a hierarchical Clojure tree zipper over a Markdown AST root."
  [root]
  (z/zipper ast-branch? ast-children ast-make-node root))

;; --- Coordinate Extraction & Predicates ---

(defn extract-coordinate
  "Extracts Hickey namespaced coordinate from an AST node if present."
  [node]
  (when (map? node)
    (or (:statement/id node)
        (:close-read/id node)
        (:chiasm/id node)
        (:model/id node)
        (:coordinate node)
        (get-in node [:attrs :id])
        (get-in node [:attrs :coordinate]))))

(defn coordinate=?
  "Returns true if node matches the target coordinate."
  [node target-coord]
  (let [coord (extract-coordinate node)]
    (or (= coord target-coord)
        (= (str coord) (str target-coord))
        (when (string? (:text node))
          (str/includes? (:text node) (str target-coord))))))

;; --- Zipper Traversal & Navigation ---

(defn find-coordinate
  "Depth-first search using zipper: returns a zipper focused on the first
   node matching target-coord, or nil if not found."
  [loc target-coord]
  (loop [curr loc]
    (cond
      (z/end? curr) nil
      (coordinate=? (z/node curr) target-coord) curr
      :else (recur (z/next curr)))))

(defn find-node
  "Depth-first search using zipper: returns a zipper focused on the first
   node satisfying (pred node), or nil if not found."
  [loc pred]
  (loop [curr loc]
    (cond
      (z/end? curr) nil
      (pred (z/node curr)) curr
      :else (recur (z/next curr)))))

;; --- Monotonic Tree Transformations (Zipper Splicing) ---

(defn replace-subtree-at
  "Pure functional transformation: finds target-coord in the AST tree, replaces
   its subtree with new-node using the zipper cursor, and unzips back to the root.
   If target-coord is not found, returns the root unmodified (OWA safe)."
  [root target-coord new-node]
  (let [loc (ast-zip root)]
    (if-let [target-loc (find-coordinate loc target-coord)]
      (z/root (z/replace target-loc new-node))
      root)))

(defn update-subtree-at
  "Pure functional transformation: finds target-coord in the AST tree, applies (f node)
   to the focused subtree, and unzips back to the root.
   If target-coord is not found, returns the root unmodified."
  [root target-coord f & args]
  (let [loc (ast-zip root)]
    (if-let [target-loc (find-coordinate loc target-coord)]
      (z/root (apply z/edit target-loc f args))
      root)))

(defn query-all
  "Walks the zipper depth-first and accumulates all nodes satisfying pred."
  [root pred]
  (loop [loc (ast-zip root)
         acc []]
    (if (z/end? loc)
      acc
      (let [node (z/node loc)
            new-acc (if (pred node) (conj acc node) acc)]
        (recur (z/next loc) new-acc)))))
