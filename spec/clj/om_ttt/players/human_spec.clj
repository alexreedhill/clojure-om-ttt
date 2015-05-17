(ns om-ttt.players.human-spec
  (:require [om-ttt.players.human :refer [new-human-player]]
            [om-ttt.protocols.player :refer [make-move]]
            [om-ttt.spec-helper :refer [empty-board mock-ui]]
            [speclj.core :refer :all]))

(def human-player-x (new-human-player "X" mock-ui))

(describe "human"
  (it "makes a move based on input from the ui"
    (should= ["X" nil nil
              nil nil nil
              nil nil nil] (make-move human-player-x empty-board))))
