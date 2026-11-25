(ns phosphene.assemblage.client-test
  (:require [clojure.test :refer [deftest is testing]]
            [phosphene.assemblage.client :as client]))

(deftest payload-formatting-test
  (testing "Payload formatting generates valid JSON structure without external deps"
    (let [payload (client/format-chat-payload {:model "deepseek/deepseek-v4.1-flash-1017"
                                              :prompt "Test prompt with \"quotes\" and \nnewlines"
                                              :max-tokens 500
                                              :temperature 0.2
                                              :reasoning? false})]
      (is (string? payload))
      (is (re-find #"\"model\": \"deepseek/deepseek-v4\.1-flash-1017\"" payload))
      (is (re-find #"\"reasoning\": \{\"enabled\": false\}" payload))
      (is (re-find #"\\\"quotes\\\"" payload)))))

(deftest strip-code-fences-test
  (testing "Code fence stripping handles clojure and edn wrappers"
    (is (= "{:foo :bar}" (client/strip-code-fences "```clojure\n{:foo :bar}\n```")))
    (is (= "{:foo :bar}" (client/strip-code-fences "```edn\n{:foo :bar}\n```")))
    (is (= "{:foo :bar}" (client/strip-code-fences "{:foo :bar}")))))
