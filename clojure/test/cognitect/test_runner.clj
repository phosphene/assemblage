(ns cognitect.test-runner
  (:require [clojure.test :as t]
            [clojure.java.io :as io]
            [clojure.string :as str]))
(defn -main [& _args]
  (doseq [f (->> (file-seq (io/file "clojure/test/phosphene/assemblage"))
                 (filter #(.isFile %))
                 (filter #(str/ends-with? (.getName %) "_test.clj")))]
    (require (symbol (str "phosphene.assemblage."
                          (str/replace (.getName f) #"\.clj$" "")))))
  (let [{:keys [fail error]} (t/run-all-tests)]
    (System/exit (if (zero? (+ fail error)) 0 1))))
