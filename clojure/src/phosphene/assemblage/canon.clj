(ns phosphene.assemblage.canon
  "Cross-Runtime Canonical JSON Serialization (RFC 8785 / JCS) (T-441).
   
   Provides deterministic, bit-level canonical JSON serialization and SHA-256
   digests across JVM Clojure, Python, and the seL4 replication harness.
   
   Adheres to RFC 8785:
   - UTF-8 byte stream output.
   - Deterministic dictionary property sorting based on UTF-16 code units.
   - IEEE 754 number formatting matching ECMAScript V8 / Ryu specifications.
   - Native string escape handling (\\b, \\t, \\n, \\f, \\r, \\\", \\\\, and \\uhhhh for control chars).
   - Rejection of NaN and Infinity.
   
   Supports:
   1. In-memory Clojure/EDN structures (maps, vectors, lists, sets, keywords, numbers, booleans, strings).
   2. Raw JSON string or byte-array inputs for wire verification."
  (:import [java.nio.charset StandardCharsets]))

(defn canonical-data->json
  "Serializes an in-memory Clojure data structure (map, vector, keyword, etc.)
   into a canonical JSON string according to RFC 8785 (JCS)."
  ^String [data]
  (phosphene.assemblage.jcs.JCS/canonicalize data))

(defn canonical-json->json
  "Parses and canonicalizes a raw JSON string according to RFC 8785 (JCS)."
  ^String [^String raw-json]
  (phosphene.assemblage.jcs.JCS/canonicalizeJson raw-json))

(defn canonical-json
  "Polymorphic canonicalizer. If `input` is a string that parses as valid JSON,
   canonicalizes the JSON string. If `input` is any Clojure data structure (map,
   vector, etc.) or a non-JSON string, serializes it canonically."
  ^String [input]
  (if (string? input)
    (try
      (phosphene.assemblage.jcs.JCS/canonicalizeJson input)
      (catch Exception _
        (phosphene.assemblage.jcs.JCS/canonicalize input)))
    (phosphene.assemblage.jcs.JCS/canonicalize input)))

(defn canonical-json-bytes
  "Returns the RFC 8785 canonical JSON representation of `input` as a UTF-8 byte array."
  ^bytes [input]
  (if (bytes? input)
    (phosphene.assemblage.jcs.JCS/canonicalizeJsonUtf8 input)
    (.getBytes (canonical-json input) StandardCharsets/UTF_8)))

(defn sha256-hex
  "Computes the lower-case hexadecimal SHA-256 digest of the RFC 8785 canonical JSON
   representation of `input`."
  ^String [input]
  (if (string? input)
    (try
      (phosphene.assemblage.jcs.JCS/sha256HexJson input)
      (catch Exception _
        (phosphene.assemblage.jcs.JCS/sha256Hex input)))
    (phosphene.assemblage.jcs.JCS/sha256Hex input)))

(defn sha256-hex-data
  "Computes the SHA-256 digest of an in-memory Clojure data structure directly."
  ^String [data]
  (phosphene.assemblage.jcs.JCS/sha256Hex data))

(defn sha256-hex-json
  "Computes the SHA-256 digest of a raw JSON string according to RFC 8785."
  ^String [^String raw-json]
  (phosphene.assemblage.jcs.JCS/sha256HexJson raw-json))
