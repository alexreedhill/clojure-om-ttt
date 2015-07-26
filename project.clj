(defproject om-ttt "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "0.0-2760"]
                 [com.cemerick/clojurescript.test "0.3.3"]
                 [org.omcljs/om "0.9.0"]
                 [org.clojure/math.numeric-tower "0.0.4"]
                 [prismatic/dommy "1.1.0"]]
  :profiles {:dev {:dependencies [[speclj "3.2.0"]]}}

  :plugins [[speclj "3.2.0"]
            [lein-cljsbuild "1.0.5"]
            [com.cemerick/clojurescript.test "0.3.3"]]

  :source-paths ["src/clj" "src/cljs", "src/cljc"]
  :test-paths ["spec/clj", "spec/cljs"]
  :cljsbuild {
    :builds [{:id "dev"
              :source-paths ["src"]
              :compiler {:output-to "resources/public/app.js"
                         :output-dir "out"
                         :optimizations :whitespace
                         :pretty-print true}}

             {:id "tests"
                :source-paths ["src/cljs" "spec/cljs"]
                :compiler {:pretty-print true
                           :output-dir "resources/private/js"
                           :output-to "resources/private/js/unit-test.js"
                           :optimizations :whitespace }}]

    :test-commands {"unit-tests" ["phantomjs" :runner
                            "resources/private/js/phantom/bind-polyfill.js"
                            "resources/private/js/unit-test.js"]}}
  :main om-ttt.core)
