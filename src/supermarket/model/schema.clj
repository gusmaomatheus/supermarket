(ns supermarket.model.schema
  (:require [supermarket.model.errors :as e]))

;; schemas

(def CustomerSchema
  [:map
   [:document [:string
               {:error/message (e/->invalid-type-error :document :string)}]]
   [:status [:enum
             {:error/message (e/->invalid-value-error :status "ACTIVATED or DISABLED")}
             "ACTIVATED" "DISABLED"]]
   [:type [:enum
           {:error/message (e/->invalid-value-error :status "COMMON or SPECIAL")}
           "COMMON" "SPECIAL"]]
   [:registration-date [:string
                        {:error/message (e/->invalid-type-error :document :string)}]]])