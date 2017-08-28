(ns fantasy-siege.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[fantasy-siege started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[fantasy-siege has shut down successfully]=-"))
   :middleware identity})
