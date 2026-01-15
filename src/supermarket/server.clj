(ns supermarket.server
  (:require [ring.adapter.jetty :as jetty]
            [reitit.ring :as ring]
            [supermarket.handler.customer :refer [create-customer-handler]]))

(def app
  (ring/ring-handler
   (ring/router
    ["api/v1"
     ["customer" {:post create-customer-handler}]])))

(defn -main []
  (println "Server running on port 3000...")
  (jetty/run-jetty app {:port 3000 :join? false}))