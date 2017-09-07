(ns fantasy-siege.routes.home
  (:require [fantasy-siege.layout :as layout]
            [compojure.core :refer [defroutes GET POST]]
            [ring.util.http-response :as response]
            [clojure.java.io :as io]))

(defn home-page []
  (layout/render "home.html"))

(defn save-upload! [{:keys [params]}]
  (println params)
  (response/ok {:status :ok}))

(defroutes home-routes
  (GET "/" [] (home-page))
  (POST "/upload" req (save-upload! req)))
