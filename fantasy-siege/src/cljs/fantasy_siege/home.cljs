(ns fantasy-siege.home
  (:require [reagent.core :as r]
            [reagent.session :as session]))

(defn home-page []
  [:div.container
   (when-let [docs (session/get :docs)]
     [:div.row>div.col-sm-12
      [:div "Sup"]])])
