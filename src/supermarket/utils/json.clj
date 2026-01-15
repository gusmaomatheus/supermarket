(ns supermarket.utils.json
  (:require [jsonista.core :as json]))

(def ^:private object-mapper
  (json/object-mapper {:decode-fn-key true}))

(defn edn->json
  "Convert Clojure structures to JSON strings."
  [data]
  (json/write-value-as-string data object-mapper))

(defn json->edn
  "Convert JSON strings to Clojure structures."
  [string]
  (json/read-value string object-mapper))