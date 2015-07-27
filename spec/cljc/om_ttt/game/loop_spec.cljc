(ns om-ttt.game.loop-spec
  (:require [om-ttt.game.loop :as loop]
            [om-ttt.players.ai :refer [new-ai-player]]
            [om-ttt.players.human :refer [new-human-player]]
            [om-ttt.game.rules :as r]
            [om-ttt.spec-helper :refer [empty-board mock-ui]]
   #?(:clj  [speclj.core :refer [describe it should=]]
      :cljs [speclj.core :refer-macros [describe it should=]])))

(def ai-token "X")
(def human-token "O")
(def ai-player (new-ai-player ai-token human-token))
(def mock-human-player (new-human-player human-token mock-ui))

(describe "game"
  (it "plays several games all the way through"
    (should= ai-token (r/winner (loop/start-game empty-board [ai-player mock-human-player] mock-ui)))))
