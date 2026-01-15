(ns supermarket.handler.handler
  (:require [supermarket.utils.datetime :refer [->now]]
            [supermarket.utils.json :refer [edn->json]]))

(defn ->rest-response [data errors]
  (edn->json {:timestamp (->now)
              :data data
              :errors errors}))