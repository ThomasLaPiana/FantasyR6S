(ns fantasy-siege.navbar
  (:require [reagent.core :as r]
            [reagent.session :as session]
            [fantasy-siege.registration :as reg]))

(defn nav-link [uri title page collapsed?]
  [:li.nav-item
   {:class (when (= page (session/get :page)) "active")}
   [:a.nav-link
    {:href uri
     :on-click #(reset! collapsed? true)} title]])

(defn user-menu []
  "create the contextual login/logout navbar button"
  (if-let [id (session/get :identity)]
    [:ul.nav.navbar-nav.navbar-text.pull-right
     [:li.nav-item "REE"]]
    [:ul.nav.navbar-nav.navbar-text.pull-right
     [:li.nav-item [reg/login-button]]]))

(defn navbar []
  (let [collapsed? (r/atom true)]
    (fn []
      [:nav.navbar.navbar-dark.bg-primary
       [:button.navbar-toggler.hidden-sm-up
        {:on-click #(swap! collapsed? not)} "☰"]
       [:div.collapse.navbar-toggleable-xs
        (when-not @collapsed? {:class "in"})
        [:a.navbar-brand {:href "#/"} "Fantasy R6S"]
        [:ul.nav.navbar-nav
         [nav-link "#/" "Home" :home collapsed?]
         [nav-link "#/upload" "Upload" :upload collapsed?]]
        [user-menu]]])))
