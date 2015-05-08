(ns om-ttt.protocols.ui)

(defprotocol UI
  (output [this string])
  (get-configuration [this])
  (get-move [this board])
  (play-again? [this])
  (same-options? [this]))
