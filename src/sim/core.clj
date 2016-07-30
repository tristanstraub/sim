(ns sim.core
  (:require [compojure.route :as route]
            [clojure.data.json :as json]
            [clojure.java.io :as io]
            [compojure.core :refer [defroutes GET]]
            [org.httpkit.server :refer [run-server]]
            [ring.middleware.cors :refer [wrap-cors]])
  (:gen-class))

(defroutes main-handler
  (GET "/" []   {:body    "Hello, world!"
                 :headers {"Content-Type" "application/json"}})
  (route/resources "/"))

(def handler
  (-> main-handler
      (wrap-cors :access-control-allow-origin [#".*"]
                 :access-control-allow-methods [:get :put :post :delete])))

(defn -main []
  (run-server #'handler {:port 8000}))
