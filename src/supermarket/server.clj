(ns supermarket.server
  (:require [ring.adapter.jetty :as jetty]
            [reitit.ring :as ring]))

(def app
  (ring/ring-handler))

(defn -main []
  (println "Server running on port 3000...")
  (jetty/run-jetty app {:port 3000 :join? false}))