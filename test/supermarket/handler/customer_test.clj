(ns supermarket.handler.customer-test
  (:require [clojure.test :refer [deftest is testing use-fixtures]]
            [supermarket.handler.customer :as customer-handler]
            [supermarket.utils.datetime :as dt] ;; Necessário para o mock
            [supermarket.util :refer [str->stream]]))

;; redefs & fixtures

(def fixed-timestamp "2026-01-01T12:00:00.000Z")

(defn mock-datetime-fixture [f]
  (with-redefs [dt/->now (constantly fixed-timestamp)]
    (f)))

(use-fixtures :once mock-datetime-fixture)

;; tests

(deftest create-customer-handler-test
  #_(testing "[OK] Successful Creation (201 Created) - "
      (let [payload "{\"document\": \"12345678900\", 
                    \"status\": \"ACTIVATED\", 
                    \"type\": \"COMMON\", 
                    \"registration_date\": \"2025-01-15T10:00:00\"}"
            request {:body (str->stream payload)}
            response (customer-handler/create-customer-handler request)
            expected-json (str "{\"timestamp\":\"" fixed-timestamp "\","
                               "\"data\":[{\"type\":\"COMMON\","
                               "\"document\":\"12345678900\","
                               "\"status\":\"ACTIVATED\","
                               "\"registration-date\":\"2025-01-15T10:00:00\"}],"
                               "\"errors\":[]}")]

        (testing "status Code and Headers"
          (is (= 201 (:status response)))
          (is (= "application/json" (get-in response [:headers "Content-Type"]))))

        (testing "response body matches exact JSON string"
          (is (= expected-json (:body response))))))

  (testing "[FAIL] Validation Error (400 Bad Request) -"
    (let [payload "{\"chave\": \"\"}"
          request {:body (str->stream payload)}
          response (customer-handler/create-customer-handler request)
          expected-json (str "{\"timestamp\":\"2026-01-01T12:00:00.000Z\","
                             "\"data\":[],"
                             "\"errors\":[\"missing required key\","
                             "\"missing required key\","
                             "\"missing required key\","
                             "\"missing required key\"]}")]

      (testing "status Code and Headers"
        (is (= 400 (:status response)))
        (is (= "application/json" (get-in response [:headers "Content-Type"]))))

      (testing "response body matches exact JSON string with errors"
        (is (= expected-json (:body response)))))))