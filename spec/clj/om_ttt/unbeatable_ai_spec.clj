(ns om-ttt.unbeatable-ai-spec
  (:require [om-ttt.game.loop :refer [start-game]]
            [om-ttt.players.ai :refer [new-ai-player]]
            [om-ttt.game.rules :refer [winner]]
            [om-ttt.spec-helper :refer [empty-board mock-ui]]
            [speclj.core :refer :all]))

(def ai-player-x (new-ai-player "X" "O"))
(def ai-player-o (new-ai-player "O" "X"))

(describe "unbeatable ai"
  (context "3x3"
    (it "always results in a tie game when two unbeatable ais play eachother"
      (should (every? nil? (take 1000 (repeatedly
        #(winner (start-game empty-board [ai-player-x ai-player-o] mock-ui)))))))))
