(ns fantasy-siege.test.routes.home
  (:require [clojure.test :refer :all]
            [fantasy-siege.routes.home :refer :all]
            [fantasy-siege.db.core :as db]))

(deftest test-parse-upload
  (is (= [{:name "test"}
          {:name "this"}
          {:name "func"}]
         (parse-upload "name\ntest\nthis\nfunc")))

  (is (= [{:name "foxa" :kills "10" :deaths "7"}
          {:name "frizzle" :kills "1" :deaths "200"}
          {:name "mahman" :kills "11" :deaths "0"}]
         (parse-upload "name,kills,deaths\nfoxa,10,7\nfrizzle,1,200\nmahman,11,0"))))

(deftest test-save-upload!
  (is (= {:status 200, :headers {}, :body {:status :ok}}
         (save-upload! {:params
                        {:data "team_name\ntest\nthis\nfunc" :table "team"}})))
  (is (= {:status 500, :headers {}, :body {:errors {:server-error ["Failed to upload the data!"]}}}
         (save-upload! {:params
                        {:data "team_name\ntest\nthis\nfunc" :table "team"}})))
  (is (= [1 1 1]
         (map db/delete-team! [{:team_name "test"}
                          {:team_name "this"}
                          {:team_name "func"}]))))

