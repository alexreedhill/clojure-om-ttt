(ns om-ttt.console.menu-spec
  (:require [om-ttt.console.menu :refer :all]
            [om-ttt.mock-ui :refer [new-mock-ui]]
            [om-ttt.spec-helper :refer :all]
            [speclj.core :refer :all]))

(def mock-ui (new-mock-ui))

(describe "console menu"
  (it "gets the game configuration"
    (should= {:board-size "foobar"
              :first-player "foobar"} (get-game-config mock-ui))))
