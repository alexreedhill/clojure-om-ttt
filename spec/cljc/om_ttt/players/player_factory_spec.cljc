(ns om-ttt.players.player-factory-spec
  (:require [om-ttt.players.player-factory :refer [create-players]]
            [om-ttt.spec-helper :refer [mock-ui]]
   #?(:clj  [speclj.core :refer [describe it should=]]
      :cljs [speclj.core :refer-macros [describe it should=]])))

(describe "player factory"
  (it "returns an ai player and a human player in the specified order"
    (let [players (create-players "X" "O" "ai" mock-ui)]
      (should= "class om_ttt.players.ai.AiPlayer" (str (type (first players))))
      (should= "class om_ttt.players.human.HumanPlayer" (str (type (last players)))))))
