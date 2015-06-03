(ns om-ttt.core-spec
  (:require [om-ttt.console.runner :as r]
            [om-ttt.console.ui :as c]
            [om-ttt.core :refer :all]
            [speclj.core :refer :all]))

(describe "main"
  (it "invokes the console runner"
    (should-invoke r/run {} (-main)))

  (it "invokes new-console-ui"
    (should-invoke c/new-console-ui {} (with-redefs [r/run (fn [_])] (-main)))))
