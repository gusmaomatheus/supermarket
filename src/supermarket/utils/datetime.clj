(ns supermarket.utils.datetime
  (:import
   [java.time ZonedDateTime]
   [java.time.format DateTimeFormatter]))

(def ^:private ts-formatter
  (DateTimeFormatter/ofPattern "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"))

(defn ->now []
  (.format ts-formatter (ZonedDateTime/now)))