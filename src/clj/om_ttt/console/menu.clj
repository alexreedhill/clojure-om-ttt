(ns om-ttt.console.menu
  (:require [om-ttt.protocols.ui :as ui]
            [om-ttt.console.messages :as m]))

  (def config (atom {}))

  (defn get-game-config [ui]
    (swap! config assoc :ai-token (ui/input-prompt ui m/ai-token-prompt))
    (swap! config assoc :human-token (ui/input-prompt ui m/human-token-prompt))
    (swap! config assoc :board-size (ui/input-prompt ui m/board-size-prompt))
    (swap! config assoc :first-player (ui/input-prompt ui m/first-player-prompt)))
