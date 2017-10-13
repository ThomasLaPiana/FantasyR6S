(ns fantasy-siege.user
  (:require [reagent.core :as r]
            [reagent.session :as session]))

(defn player-list []
  "list the players that this user recruited"
  [:div.home-title "This is the user dashboard"])

(defn modal []
  (when-let [session-modal (session/get :modal)]
    [session-modal]))

(defn user-dashboard []
  "display information specific to the user"
  [:div
   [modal]
   [player-list]])
