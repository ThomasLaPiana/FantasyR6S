(ns fantasy-siege.routes.home
  (:require [fantasy-siege.layout :as layout]
            [fantasy-siege.db.core :as db]
            [compojure.core :refer [defroutes GET POST]]
            [ring.util.http-response :as response]
            [clojure.string :refer [split-lines trim]]
            [csv-map.core :refer [parse-csv]]
            [clojure.java.io :as io]))

(defn home-page []
  (layout/render "home.html"))

(defn parse-upload [csv-string]
  "Parse the uploaded data into a seq of hashmaps"
  (-> csv-string
      (parse-csv :key :keyword)))

(defn save-upload! [{:keys [params]}]
  "Upload the data submitted data by the admins through the upload page"
  (let [table (-> params :table)
        data-seq (parse-upload (-> params :data))]
    (case table
      "teams"  (doall (map db/create-team! data-seq))
      (println "upload failed"))
    (response/ok {:status :ok})))

(defroutes home-routes
  "Define the home-routes"
  (GET "/" [] (home-page))
  (POST "/upload" req (save-upload! req)))
