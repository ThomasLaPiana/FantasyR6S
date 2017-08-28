(ns fantasy-siege.doo-runner
  (:require [doo.runner :refer-macros [doo-tests]]
            [fantasy-siege.core-test]))

(doo-tests 'fantasy-siege.core-test)

