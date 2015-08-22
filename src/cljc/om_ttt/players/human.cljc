(ns om-ttt.players.human
  (:require [om-ttt.protocols.player :refer [Player]]
            [om-ttt.protocols.ui :as ui]))

(deftype HumanPlayer [token ui]
  Player
  (make-move [this board]
    (ui/move ui board token)))

(defn new-human-player [player-token ui]
  (HumanPlayer. player-token ui))
