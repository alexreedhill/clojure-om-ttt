(ns om-ttt.game-loop
  (:require [om-ttt.protocols.player :as player]
            [om-ttt.protocols.ui :as ui]
            [om-ttt.rules :as r]))

(defn start-game [board players game-ui]
  (ui/draw-board game-ui board)
  (if (r/game-over? board)
    board
    (-> (player/make-move (first players) board)
        (recur (reverse players) game-ui))))
