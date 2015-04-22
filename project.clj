(defproject clojure-om-ttt "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]]
  :profiles {:dev {:dependencies [[speclj "3.2.0"]
                                  [org.clojure/clojurescript "0.0-3196"]
                                  [org.omcljs/om "0.8.8"]]}}
  :plugins [[speclj "3.2.0"]]
  :source-paths ["src/clj" "src/cljs"]
  :test-paths ["spec/clj", "spec/cljs"])
