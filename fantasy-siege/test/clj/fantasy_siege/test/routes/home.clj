(ns fantasy-siege.test.routes.home
  (:require [clojure.test :refer :all]
            [fantasy-siege.routes.home :refer :all]))

(deftest test-parse-upload
  (is (= [{:name "test"}
          {:name "this"}
          {:name "func"}]
         (parse-upload "name\ntest\nthis\nfunc")))

  (is (= [{:name "foxa" :kills "10" :deaths "7"}
          {:name "frizzle" :kills "1" :deaths "200"}
          {:name "mahman" :kills "11" :deaths "0"}]
         (parse-upload "name,kills,deaths\nfoxa,10,7\nfrizzle,1,200\nmahman,11,0"))))
