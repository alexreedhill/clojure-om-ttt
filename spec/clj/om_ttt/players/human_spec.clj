(ns om-ttt.players.human-spec
  (:require [om-ttt.players.human :refer [new-human-player]]
            [om-ttt.protocols.player :refer [make-move]]
            [om-ttt.mock-ui :refer [new-mock-ui]]
            [om-ttt.spec-helper :refer :all]
            [speclj.core :refer :all]))

(def human-player-x (new-human-player "X" (new-mock-ui {})))

(describe "human"
  (it "makes a move based on input from the ui"
    (should= ["X" nil nil
              nil nil nil
              nil nil nil] (make-move human-player-x empty-board))))
