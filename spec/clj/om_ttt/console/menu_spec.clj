(ns om-ttt.console.menu-spec
  (:require [om-ttt.console.menu :refer :all]
            [om-ttt.spec-helper :refer [mock-ui]]
            [speclj.core :refer :all]))

(describe "console menu"
  (it "gets the game configuration"
    (should= {:board-size 3
              :ai-token "X"
              :human-token "O"
              :first-player "human"} (get-game-config mock-ui))))
