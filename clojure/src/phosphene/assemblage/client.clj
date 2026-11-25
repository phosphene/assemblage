(ns phosphene.assemblage.client
  "Pure JVM HTTP client (java.net.http) for high-velocity EDN synthesis.
   
   Eliminates Python IPC and JSON escaping bottlenecks by:
   - Direct streaming from OpenRouter / Jev Router endpoints via java.net.http.HttpClient.
   - Requesting native Clojure EDN directly from the model.
   - Parsing responses in-process via clojure.edn with zero JSON serialization penalty."
  (:require [clojure.edn :as edn]
            [clojure.string :as str])
  (:import [java.net URI]
           [java.net.http HttpClient HttpRequest HttpRequest$BodyPublishers HttpResponse HttpResponse$BodyHandlers]
           [java.time Duration]))

;; --- Client Construction ---

(defn make-client
  "Create a standard Java 11+ HttpClient instance."
  ([] (make-client 30))
  ([timeout-seconds]
   (-> (HttpClient/newBuilder)
       (.connectTimeout (Duration/ofSeconds timeout-seconds))
       (.followRedirects java.net.http.HttpClient$Redirect/NORMAL)
       (.build))))

;; --- Payload Serialization (Minimal, Pure Clojure) ---

(defn- escape-json-string [s]
  (-> s
      (str/replace "\\" "\\\\")
      (str/replace "\"" "\\\"")
      (str/replace "\n" "\\n")
      (str/replace "\r" "\\r")
      (str/replace "\t" "\\t")))

(defn format-chat-payload
  "Construct OpenRouter / Jev Router JSON request payload requesting pure EDN output."
  [{:keys [model prompt max-tokens temperature reasoning?]
    :or {model "deepseek/deepseek-v4.1-flash-1017"
         max-tokens 1500
         temperature 0.1
         reasoning? false}}]
  (let [escaped-prompt (escape-json-string prompt)
        reasoning-frag (if reasoning?
                         ", \"reasoning\": {\"effort\": \"low\"}"
                         ", \"reasoning\": {\"enabled\": false}")]
    (str "{\"model\": \"" model "\", "
         "\"messages\": [{\"role\": \"user\", \"content\": \"" escaped-prompt "\"}], "
         "\"max_tokens\": " max-tokens ", "
         "\"temperature\": " temperature
         reasoning-frag "}")))

;; --- Pure In-JVM Request Execution & EDN Ingest ---

(defn strip-code-fences
  "Remove optional markdown code wrappers if model wraps response."
  [s]
  (-> s
      str/trim
      (str/replace #"^```(clojure|edn)?\s*" "")
      (str/replace #"```$" "")
      str/trim))

(defn request-edn-projection
  "Dispatch prompt to OpenRouter endpoint and read response directly as native Clojure EDN.
   Returns {:status :ok :data edn-map :elapsed-ms ...} or {:status :error :message ...}."
  ([api-key prompt]
   (request-edn-projection (make-client) "https://openrouter.ai/api/v1/chat/completions" api-key prompt {}))
  ([client endpoint api-key prompt opts]
   (let [t0 (System/nanoTime)
         payload-json (format-chat-payload (assoc opts :prompt prompt))
         request (-> (HttpRequest/newBuilder)
                     (.uri (URI/create endpoint))
                     (.header "Authorization" (str "Bearer " api-key))
                     (.header "Content-Type" "application/json")
                     (.header "User-Agent" "Phosphene-Assemblage-JVM/0.1.0")
                     (.POST (HttpRequest$BodyPublishers/ofString payload-json))
                     (.build))]
     (try
       (let [response (.send client request (HttpResponse$BodyHandlers/ofString))
             status (.statusCode response)
             body (.body response)]
         (if (= status 200)
           (let [;; Extract content from JSON response using regex to avoid external JSON parser dep
                 content-match (re-find #"\"content\":\s*\"((?:\\\"|[^\"])*)\"" body)
                 raw-content (if content-match
                               (-> (second content-match)
                                   (str/replace "\\n" "\n")
                                   (str/replace "\\\"" "\"")
                                   (str/replace "\\\\" "\\"))
                               body)
                 cleaned (strip-code-fences raw-content)
                 parsed-edn (edn/read-string cleaned)
                 elapsed-ms (quot (- (System/nanoTime) t0) 1000000)]
             {:status :ok
              :data parsed-edn
              :elapsed-ms elapsed-ms})
           {:status :error
            :http-status status
            :body body}))
       (catch Exception e
         {:status :error
          :exception (.getMessage e)})))))
