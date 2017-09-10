(ns fantasy-siege.upload
  (:require [reagent.core :as r]
            [reagent.session :as session]
            [ajax.core :refer [POST]]))

(defn send-upload-data! [data table]
  "POST the data to be uploaded"
  (POST "/upload"
        {:format :json
         :headers
         {"Accept" "application/transit+json"
          "x-csrf-token"
          (.-value (.getElementById js/document "token"))}
         :params {:data @data :table @table}
         :handler #(.log js/console (str "response:" %))
         :error-handler #(.log js/console (str "error:" %))}))


(defn upload-title []
  "Contains the title of the upload page"
  [:div.upload-title "Upload New Player Data Here"])

(defn upload-box []
  "This is the text-box that handles upload data"
  (let  [input (r/atom  "")
         table-list (r/atom ["" "players" "matches" "teams"])
         target-table (r/atom (nth @table-list 0))]
    (fn  []
      [:div.upload-box
       [:textarea {:type  "text"
                 :placeholder  "Paste as CSV format"
                 :value @input
                 :on-change #(reset! input  (-> % .-target .-value))}]
       [:div
        [:select
         {:on-change #(reset! target-table (-> % .-target .-value))}
         (for [item @table-list]
         ^{:key item}
         [:option {:value item
                   }
          item])]
        [:button.btn.btn-primary
         {:type :submit
          :on-click #(do (send-upload-data! input target-table)
                         (reset! input ""))}
         "Upload"]]])))

(defn upload-form []
  "Structures the upload page components"
  [:div.upload-form
   [upload-title]
   [upload-box]])
