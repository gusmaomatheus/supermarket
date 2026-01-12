(ns supermarket.model.schema-test
  (:require [clojure.test :refer [deftest is testing]]
            [supermarket.model.schema :as schema]
            [supermarket.model.validator :refer [schema-validate]]))

;; mocks

(def valid-customer
  {:document "49184711516"
   :status "ACTIVATED"
   :type "COMMON"
   :registration-date "2025-01-09"})

(def invalid-customer
  {:document 49184711516
   :status "ON"
   :type "RARE"
   :registration-date 741893198})

;; tests

(deftest customer-schema-test
  (testing "[OK] Entity with all the correct data"
    (let [expected {:status :ok :data valid-customer :errors []}
          output (schema-validate schema/CustomerSchema valid-customer)]
      (is (= expected output))))
  (testing "[FAIL] Entity with all invalid fields"
    (let [expected {:status :error
                    :data invalid-customer
                    :errors ["E002: The 'document' field must be of type 'string'."
                             "E002: The 'document' field must be of type 'string'."
                             "E003: The 'status' field must be ACTIVATED or DISABLED."
                             "E003: The 'status' field must be COMMON or SPECIAL."]}
          output (schema-validate schema/CustomerSchema invalid-customer)]
      (is (= expected output)))))