(defproject fantasy-siege "0.1.0-SNAPSHOT"

  :description "FIXME: write description"
  :url "http://example.com/FIXME"

  :dependencies [[clj-time "0.14.0"]
                 [cljs-ajax "0.6.0"]
                 [compojure "1.6.0"]
                 [conman "0.6.8"]
                 [cprop "0.1.11"]
                 [csv-map "0.1.2"]
                 [funcool/struct "1.0.0"]
                 [luminus-immutant "0.2.3"]
                 [luminus-migrations "0.4.0"]
                 [luminus-nrepl "0.1.4"]
                 [luminus/ring-ttl-session "0.3.2"]
                 [markdown-clj "1.0.0"]
                 [metosin/muuntaja "0.3.2"]
                 [metosin/ring-http-response "0.9.0"]
                 [metosin/ring-swagger "0.24.2"]
                 [mount "0.1.11"]
                 [org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.908" :scope "provided"]
                 [org.clojure/tools.cli "0.3.5"]
                 [org.clojure/tools.logging "0.4.0"]
                 [org.postgresql/postgresql "42.1.3"]
                 [org.webjars.bower/tether "1.4.0"]
                 [org.webjars/bootstrap "4.0.0-alpha.5"]
                 [org.webjars/font-awesome "4.7.0"]
                 [reagent "0.7.0"]
                 [reagent-utils "0.2.1"]
                 [ring-webjars "0.2.0"]
                 [ring/ring-core "1.6.2"]
                 [ring/ring-defaults "0.3.1"]
                 [secretary "1.2.3"]
                 [selmer "1.11.0"]]

  :min-lein-version "2.0.0"

  :jvm-opts ["-server" "-Dconf=.lein-env"]
  :source-paths ["src/clj" "src/cljc"]
  :test-paths ["test/clj"]
  :resource-paths ["resources" "target/cljsbuild"]
  :target-path "target/%s/"
  :main ^:skip-aot fantasy-siege.core
  :migratus {:store :database :db ~(get (System/getenv) "DATABASE_URL")}

  :plugins [[lein-cprop "1.0.3"]
            [migratus-lein "0.5.1"]
            [lein-cljsbuild "1.1.5"]
            [lein-immutant "2.1.0"]]
  :clean-targets ^{:protect false}
  [:target-path [:cljsbuild :builds :app :compiler :output-dir] [:cljsbuild :builds :app :compiler :output-to]]
  :figwheel
  {:http-server-root "public"
   :nrepl-port 7002
   :css-dirs ["resources/public/css"]
   :nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}
  

  :profiles
  {:uberjar {:omit-source true
             :prep-tasks ["compile" ["cljsbuild" "once" "min"]]
             :cljsbuild
             {:builds
              {:min
               {:source-paths ["src/cljc" "src/cljs" "env/prod/cljs"]
                :compiler
                {:output-to "target/cljsbuild/public/js/app.js"
                 :optimizations :advanced
                 :pretty-print false
                 :closure-warnings
                 {:externs-validation :off :non-standard-jsdoc :off}
                 :externs ["react/externs/react.js"]}}}}
             
             
             :aot :all
             :uberjar-name "fantasy-siege.jar"
             :source-paths ["env/prod/clj"]
             :resource-paths ["env/prod/resources"]}

   :dev           [:project/dev :profiles/dev]
   :test          [:project/dev :project/test :profiles/test]

   :project/dev  {:dependencies [[prone "1.1.4"]
                                 [ring/ring-mock "0.3.1"]
                                 [ring/ring-devel "1.6.2"]
                                 [pjstadig/humane-test-output "0.8.2"]
                                 [binaryage/devtools "0.9.4"]
                                 [com.cemerick/piggieback "0.2.2"]
                                 [doo "0.1.7"]
                                 [figwheel-sidecar "0.5.13"]]
                  :plugins      [[com.jakemccrary/lein-test-refresh "0.19.0"]
                                 [lein-doo "0.1.7"]
                                 [lein-figwheel "0.5.13"]
                                 [org.clojure/clojurescript "1.9.908"]]
                  :cljsbuild
                  {:builds
                   {:app
                    {:source-paths ["src/cljs" "src/cljc" "env/dev/cljs"]
                     :figwheel {:on-jsload "fantasy-siege.core/mount-components"}
                     :compiler
                     {:main "fantasy-siege.app"
                      :asset-path "/js/out"
                      :output-to "target/cljsbuild/public/js/app.js"
                      :output-dir "target/cljsbuild/public/js/out"
                      :source-map true
                      :optimizations :none
                      :pretty-print true}}}}
                  
                  
                  
                  :doo {:build "test"}
                  :source-paths ["env/dev/clj"]
                  :resource-paths ["env/dev/resources"]
                  :repl-options {:init-ns user}
                  :injections [(require 'pjstadig.humane-test-output)
                               (pjstadig.humane-test-output/activate!)]}
   :project/test {:resource-paths ["env/test/resources"]
                  :cljsbuild
                  {:builds
                   {:test
                    {:source-paths ["src/cljc" "src/cljs" "test/cljs"]
                     :compiler
                     {:output-to "target/test.js"
                      :main "fantasy-siege.doo-runner"
                      :optimizations :whitespace
                      :pretty-print true}}}}
                  
                  }
   :profiles/dev {}
   :profiles/test {}})
