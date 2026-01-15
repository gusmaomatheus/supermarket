(ns supermarket.utils.json-test
  (:require [clojure.test :refer [deftest is testing]]
            [supermarket.utils.json :refer [edn->json json->edn]]
            [supermarket.util :refer [str->stream]]))

;; tests

(deftest edn->json-test
  (testing "[OK] Simple map with multi data types."
    (is (= "{\"string\":\"João\",\"int\":30,\"double\":1.5,\"boolean\":true,\"map\":{},\"vector\":[],\"list\":[]}"
           (edn->json {:string "João" :int 30 :double 1.5 :boolean true :map {} :vector [] :list '()}))))

  (testing "[OK] Map with nested keys."
    (is (= "{\"usuario\":{\"id\":1,\"roles\":[\"ADMIN\"]}}"
           (edn->json {:usuario {:id 1 :roles ["ADMIN"]}})))))

(deftest json->edn-test
  (testing "[OK] Must convert JSON keys (strings) to Keywords"
    (let [json-input "{\"document\": \"123\", \"status\": \"OK\"}"]
      (is (= {:document "123" :status "OK"}
             (json->edn (str->stream json-input))))))

  (testing "[OK] Must handle snake_case correctly by converting it to the kebab-case keyword."
    (let [json-input "{\"registration_date\": \"2025-01-01\"}"]
      (is (= {:registration-date "2025-01-01"}
             (json->edn (str->stream json-input))))))

  (testing "[OK] Complex nested structures"
    (let [json-input "{\"data\": {\"items\": [{\"id\": 1}, {\"id\": 2}]}}"]
      (is (= {:data {:items [{:id 1} {:id 2}]}}
             (json->edn (str->stream json-input))))))

  (testing "[OK] JSON arrays become Clojure vectors."
    (let [json-input "[10, 20, 30]"]
      (is (= [10 20 30]
             (json->edn (str->stream json-input))))))

  (testing "[OK] Unicode/accentuation handling"
    (let [json-input "{\"mensagem\": \"Olá Mundo\"}"]
      (is (= {:mensagem "Olá Mundo"}
             (json->edn (str->stream json-input)))))))