(ns user
  (:require [mount.core :as mount]
            [fantasy-siege.figwheel :refer [start-fw stop-fw cljs]]
            fantasy-siege.core))

(defn start []
  (mount/start-without #'fantasy-siege.core/repl-server))

(defn stop []
  (mount/stop-except #'fantasy-siege.core/repl-server))

(defn restart []
  (stop)
  (start))


