(ns phosphene.assemblage.runner
  (:require [phosphene.assemblage.ast :as ast]
            [phosphene.assemblage.lens :as lens]
            [phosphene.assemblage.core :as asm]
            [clojure.java.io :as io]))

(defn -main [& args]
  (let [dir (io/file (or (first args) "../assemblages/wisdom-1951/spaces-of-reasoning"))
        files (filter #(and (.isFile %) (not= "INDEX.md" (.getName %))) (sort (.listFiles dir)))
        all-elements (mapv (fn [f]
                             (let [doc (ast/parse-markdown (slurp f))]
                               (lens/project-assemblage-elements doc)))
                           files)
        total-nodes (reduce merge {} (map :nodes all-elements))
        total-edges (reduce into #{} (map :edges all-elements))
        g0 (reduce (fn [acc [nid node]]
                     (asm/add-node acc (:id node) (:traits node) (:level node)))
                   (asm/make-assemblage)
                   total-nodes)
        g (reduce (fn [acc [src dst kind]]
                    (asm/add-relation acc src dst kind))
                  g0
                  total-edges)]
    (println (str "Graph constructed! Nodes: " (count (:nodes g)) " Edges: " (count (:edges g))))
    (println (str "Detected cycles: " (asm/cycles g)))))
