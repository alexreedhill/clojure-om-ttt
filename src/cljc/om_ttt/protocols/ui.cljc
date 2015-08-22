(ns om-ttt.protocols.ui)

(defprotocol UI
  (display-message [this string])
  (input-prompt [this string])
  (draw-board [this board])
  (move [this board token]))
