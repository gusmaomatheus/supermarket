(ns supermarket.utils.json
  (:require [clojure.string :refer [replace]]
            [jsonista.core :as json]))

(def ^:private custom-object-mapper
  (json/object-mapper
   {:decode-key-fn
    (fn [k]
      (-> k
          (replace "_" "-")
          keyword))}))

(defn edn->json
  "Convert Clojure structures to JSON strings."
  [data]
  (json/write-value-as-string data custom-object-mapper))

(defn json->edn
  "Convert JSON strings to Clojure structures."
  [string]
  (json/read-value string custom-object-mapper))