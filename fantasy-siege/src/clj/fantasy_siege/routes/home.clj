(ns fantasy-siege.routes.home
  (:require [fantasy-siege.layout :as layout]
            [compojure.core :refer [defroutes GET POST]]
            [ring.util.http-response :as response]
            [clojure.string :refer [split-lines trim]]
            [csv-map.core :refer [parse-csv]]
            [clojure.java.io :as io]))

(defn home-page []
  (layout/render "home.html"))

(defn parse-upload [csv-data]
  "Parse the uploaded data into a seq of hashmaps"
  (-> csv-data
      (parse-csv :key :keyword)))

(defn save-upload! [{:keys [params]}]
  "Upload the data submitted data by the admins through the upload page"
  (let [table (-> params :table)
        data-seq (parse-upload (-> params :data))]
    (do (println table data-seq)
        (response/ok {:status :ok}))))

(save-upload! {:params {:data "name\ntest" :table "teams"}})


(defroutes home-routes
  "Define the home-routes"
  (GET "/" [] (home-page))
  (POST "/upload" req (save-upload! req)))
