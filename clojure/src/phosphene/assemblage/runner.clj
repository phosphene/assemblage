(ns phosphene.assemblage.runner
  "Hermetic test and build runner for phosphene.assemblage.
   Ensures Java sources are compiled before test suite execution."
  (:import [javax.tools ToolProvider JavaCompiler DiagnosticCollector StandardJavaFileManager JavaFileObject]
           [java.io File]
           [java.net URL URLClassLoader]
           [java.util Arrays]))

(defn compile-java-sources!
  "Compiles all Java source files in `clojure/src/java` to `target/classes`."
  []
  (let [target-dir (File. "target/classes")
        src-dir (File. "clojure/src/java")]
    (.mkdirs target-dir)
    (let [compiler (ToolProvider/getSystemJavaCompiler)
          diagnostics (DiagnosticCollector.)
          file-manager (.getStandardFileManager compiler diagnostics nil nil)
          java-files (->> (file-seq src-dir)
                          (filter #(.isFile %))
                          (filter #(.endsWith (.getName %) ".java"))
                          (into-array File))
          compilation-units (.getJavaFileObjectsFromFiles file-manager (Arrays/asList java-files))
          classpath (System/getProperty "java.class.path")
          options (Arrays/asList (into-array String ["-d" (.getAbsolutePath target-dir)
                                                     "-cp" classpath]))
          task (.getTask compiler nil file-manager diagnostics options nil compilation-units)
          success? (.call task)]
      (.close file-manager)
      (when-not success?
        (doseq [diag (.getDiagnostics diagnostics)]
          (println diag))
        (throw (ex-info "Java compilation failed" {:diagnostics (.getDiagnostics diagnostics)})))
      success?)))

(defn -main
  [& args]
  (compile-java-sources!)
  (let [target-url (.toURL (.toURI (File. "target/classes")))
        cl (.getContextClassLoader (Thread/currentThread))
        new-cl (URLClassLoader. (into-array URL [target-url]) cl)]
    (.setContextClassLoader (Thread/currentThread) new-cl)
    (require 'cognitect.test-runner)
    (let [test-main (resolve 'cognitect.test-runner/-main)]
      (apply test-main args))))
