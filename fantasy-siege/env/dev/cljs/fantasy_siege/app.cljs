(ns ^:figwheel-no-load fantasy-siege.app
  (:require [fantasy-siege.core :as core]
            [devtools.core :as devtools]))

(enable-console-print!)

(devtools/install!)

(core/init!)
