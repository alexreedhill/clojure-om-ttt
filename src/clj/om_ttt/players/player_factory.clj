(ns om-ttt.players.player-factory
  (:require [om-ttt.players.ai :refer [new-ai-player]]
            [om-ttt.players.human :refer [new-human-player]]))

(defn create-players [ai-token human-token first-player ui]
  (let [players [(new-ai-player ai-token human-token) (new-human-player human-token ui)]]
    (if (= "ai" first-player)
      players
      (reverse players))))
