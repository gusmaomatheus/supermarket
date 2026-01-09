(ns supermarket.model.validator
  (:require [malli.core :as m]
            [malli.error :as me]))

(defn- ->return-message [status data errors]
  {:status status
   :data data
   :errors errors})

(defn- ->humanized-schema-errors [schema data]
  (me/humanize (m/explain schema data)))

(defn schema-validate [schema data]
  (let [errors (or (->humanized-schema-errors schema data) [])
        status (if (seq errors) :error :ok)]
    (->return-message status data errors)))