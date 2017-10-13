(ns fantasy-siege.registration
  (:require [fantasy-siege.components.common :as c]
            [reagent.core :as r]
            [reagent.session :as session]))

(declare login-form)
(declare registration-form)

(defn login [fields]
  "log-in a user"
  (doseq [[k v] @fields] (js/console.log k v)))

(defn register! [fields]
  "Register a user"
  (doseq [[k v] @fields] (js/console.log k v)))

(defn registration-form []
  "registration form for users"
  (let [fields (r/atom {})]
    (fn []
      [c/modal
       [:div "Fantasy Siege Registration"]
       [:div
        [:div.well.well-sm
         [:strong "* required field"]]
        [c/text-input "email" :id "enter your email" fields]
        [c/password-input "password" :pass "enter a password" fields]
        [c/password-input "confirm password" :pass-confirm "re-enter your password" fields]]
       [:div
        [:button.btn.btn-primary.pull-left
         {:on-click #(register! fields)} "Register"]
        [:button.btn.btn-danger.pull-left
          {:on-click #(session/remove! :modal)} "Cancel"]
        [:button.btn.btn-primary.pull-right
         {:on-click #(session/put! :modal login-form)}
         "Already have an Account?"]]])))


(defn login-form []
  "registration form for users"
  (let [fields (r/atom {})]
    (fn []
      [c/modal
       [:div "Fantasy Siege Login"]
       [:div
        [:div.well.well-sm
         [:strong "* required field"]]
        [c/text-input "email" :id "enter your email" fields]
        [c/password-input "password" :pass "enter a password" fields]]
       [:div
        [:button.btn.btn-primary.pull-left
         {:on-click #(register! fields)} "Login"]
        [:button.btn.btn-danger.pull-left
         {:on-click #(session/remove! :modal)} "Cancel"]
        [:button.btn.btn-primary.pull-right
         {:on-click #(session/put! :modal registration-form)}
         "Need to Register?"]]])))

(defn login-button []
  "button that calls login modal"
  [:a.btn
   {:on-click #(session/put! :modal login-form)}
   "Log-In/Register"])
