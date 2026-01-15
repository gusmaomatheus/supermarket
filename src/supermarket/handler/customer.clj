(ns supermarket.handler.customer
  (:require [ring.util.response :refer [bad-request created content-type]]
            [supermarket.handler.handler :refer [->rest-response]]
            [supermarket.model.schema :refer [CustomerSchema]]
            [supermarket.model.validator :refer [schema-validate]]
            [supermarket.utils.json :refer [json->edn]]))

(defn create-customer-handler [{:keys [body] :as _request}]
  (let [body' (json->edn body)
        schema-validation-result (schema-validate CustomerSchema body')
        schema-is-not-valid? (= :error (:status schema-validation-result))]
    (if schema-is-not-valid?
      (-> (bad-request (->rest-response [] (:errors schema-validation-result)))
          (content-type "application/json"))
      (-> (created "/api/customer" (->rest-response [(:data schema-validation-result)] []))
          (content-type "application/json")))))