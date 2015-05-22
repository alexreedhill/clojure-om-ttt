(ns om-ttt.console.menu-spec
  (:require [om-ttt.console.menu :refer :all]
            [om-ttt.spec-helper :refer [mock-ui mock-config]]
            [speclj.core :refer :all]))

(describe "console menu"
  (it "gets the game configuration"
    (should= mock-config (get-game-config mock-ui))))
