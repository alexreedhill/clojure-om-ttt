(ns om-ttt.console.menu
  (:require [om-ttt.protocols.ui :as ui]))

  (def config (atom {}))
  (def board-size-prompt "What size board would you like to play? (3 or 4)")
  (def first-player-prompt "Who would you like to go first? (human or ai)")

  (defn get-game-config [ui]
    (ui/display-message ui board-size-prompt)
    (swap! config assoc :board-size (ui/user-input ui))
    (ui/display-message ui first-player-prompt)
    (swap! config assoc :first-player (ui/user-input ui)))
