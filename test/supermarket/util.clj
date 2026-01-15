(ns supermarket.util
  (:import [java.io ByteArrayInputStream]))

(defn str->stream
  "Creates an InputStream from a String to simulate the Ring's body."
  [string]
  (ByteArrayInputStream. (.getBytes string "UTF-8")))