(ns om-ttt.game-loop
  (:require [om-ttt.protocols.ui :as ui]
            [om-ttt.protocols.player :as player]
            [om-ttt.rules :as r]))

(defn start-game [board players ui]
  (if (r/game-over? board)
    board
    (-> (player/make-move (first players) board)
        (recur (reverse players) ui))))
