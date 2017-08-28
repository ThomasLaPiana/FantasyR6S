(ns fantasy-siege.env
  (:require [selmer.parser :as parser]
            [clojure.tools.logging :as log]
            [fantasy-siege.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[fantasy-siege started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[fantasy-siege has shut down successfully]=-"))
   :middleware wrap-dev})
