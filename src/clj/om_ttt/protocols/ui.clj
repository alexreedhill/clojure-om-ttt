(ns om-ttt.protocols.ui)

(defprotocol UI
  (display-message [this string])
  (user-input [this])
  (draw-board [this board])
  (move [this board])
  (restart? [this])
  (same-config? [this]))
