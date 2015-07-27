(ns om-ttt.console.menu
  (:require [om-ttt.messages :as m]
            [om-ttt.protocols.ui :as ui]))

  (defn get-game-config [ui]
    {:human-token (ui/input-prompt ui m/human-token-prompt)
     :ai-token (ui/input-prompt ui m/ai-token-prompt)
     :board-size (ui/input-prompt ui m/board-size-prompt)
     :first-player (ui/input-prompt ui m/first-player-prompt)})
