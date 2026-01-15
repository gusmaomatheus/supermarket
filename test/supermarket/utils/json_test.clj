(ns supermarket.utils.json-test
  (:require [clojure.test :refer [deftest is testing]]
            [supermarket.utils.json :refer [edn->json json->edn]]))

(deftest edn->json-test
  (testing "[OK] Simple map with multi data types."
    (is (= "{\"string\":\"João\",\"int\":30,\"double\":1.5,\"boolean\":true,\"map\":{},\"vector\":[],\"list\":[]}"
           (edn->json {:string "João" :int 30 :double 1.5 :boolean true :map {} :vector [] :list '()}))))

  (testing "[OK] Map with nested keys."
    (is (= "{\"usuario\":{\"id\":1,\"roles\":[\"ADMIN\"]}}"
           (edn->json {:usuario {:id 1 :roles ["ADMIN"]}})))))