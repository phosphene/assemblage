(ns phosphene.assemblage.canon-test
  "Verification suite for RFC 8785 Canonical JSON Serialization (JCS) (T-441).
   
   Tests compliance with:
   1. RFC 8785 official test vectors (arrays, french, structures, unicode, values, weird).
   2. RFC 8785 Section 3.2.2.3 IEEE 754 number serialization samples.
   3. Clojure in-memory data structures (maps, vectors, keywords, numbers, booleans, sets).
   4. Deterministic Unicode code-point key ordering.
   5. SHA-256 bit-level agreement."
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [phosphene.assemblage.canon :as canon])
  (:import [java.nio.charset StandardCharsets]
           [java.util Arrays]))

(def rfc8785-files
  ["arrays" "french" "structures" "unicode" "values" "weird"])

(deftest rfc8785-official-vectors-test
  (testing "RFC 8785 official test suite canonicalization and idempotency"
    (doseq [name rfc8785-files]
      (let [in-file (io/file (str "test/resources/rfc8785/in_" name ".json"))
            out-file (io/file (str "test/resources/rfc8785/out_" name ".json"))
            raw-in (slurp in-file)
            expected-bytes (.getBytes (slurp out-file) StandardCharsets/UTF_8)
            expected-str (String. expected-bytes StandardCharsets/UTF_8)
            
            ;; 1. From raw JSON string
            actual-str (canon/canonical-json raw-in)
            actual-bytes (canon/canonical-json-bytes raw-in)]
        (is (= expected-str actual-str) (str "String mismatch on vector: " name))
        (is (Arrays/equals expected-bytes actual-bytes) (str "Byte mismatch on vector: " name))
        
        ;; 2. Idempotency test: canonicalizing already-canonical output is identity
        (is (= expected-str (canon/canonical-json actual-str))
            (str "Idempotency failed on vector: " name))))))

(deftest rfc8785-number-samples-test
  (testing "Table 1 ECMAScript-compatible number serialization samples"
    (is (= "0" (canon/canonical-json 0.0)))
    (is (= "0" (canon/canonical-json -0.0)))
    (is (= "5e-324" (canon/canonical-json 5.0e-324)))
    (is (= "-5e-324" (canon/canonical-json -5.0e-324)))
    (is (= "1.7976931348623157e+308" (canon/canonical-json 1.7976931348623157e+308)))
    (is (= "-1.7976931348623157e+308" (canon/canonical-json -1.7976931348623157e+308)))
    (is (= "9007199254740992" (canon/canonical-json 9007199254740992.0)))
    (is (= "-9007199254740992" (canon/canonical-json -9007199254740992.0)))
    (is (= "1e+23" (canon/canonical-json 1.0e+23)))
    (is (= "1e+21" (canon/canonical-json 1.0e+21)))
    (is (= "0.000001" (canon/canonical-json 0.000001)))
    (is (= "333333333.3333333" (canon/canonical-json 333333333.3333333)))
    (is (= "-0.0000033333333333333333" (canon/canonical-json -0.0000033333333333333333)))))

(deftest clojure-data-structures-test
  (testing "In-memory Clojure data structures serialize deterministically"
    ;; Key sorting
    (is (= "{\"a\":1,\"b\":2,\"c\":3}"
           (canon/canonical-data->json {:c 3 :a 1 :b 2})))
    
    ;; Qualified keywords
    (is (= "{\"close-read/id\":\"CR-WISDOM-U001\",\"foo/bar\":42}"
           (canon/canonical-data->json {:foo/bar 42 :close-read/id "CR-WISDOM-U001"})))
    
    ;; Nested maps and vectors
    (is (= "{\"inner\":{\"x\":true,\"z\":[1,2,null]},\"outer\":false}"
           (canon/canonical-data->json {:outer false :inner {:z [1 2 nil] :x true}})))
    
    ;; Sets are canonically sorted
    (is (= "[\"a\",\"b\",\"c\"]"
           (canon/canonical-data->json #{"c" "a" "b"})))))

(deftest sha256-digest-test
  (testing "Deterministic SHA-256 computation"
    (let [d1 {:b 2 :a 1}
          d2 {:a 1 :b 2}]
      (is (= (canon/sha256-hex d1) (canon/sha256-hex d2)))
      (is (= 64 (count (canon/sha256-hex d1)))))))
