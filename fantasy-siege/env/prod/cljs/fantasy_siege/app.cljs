(ns fantasy-siege.app
  (:require [fantasy-siege.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
