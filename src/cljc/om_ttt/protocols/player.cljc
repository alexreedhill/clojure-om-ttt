(ns om-ttt.protocols.player)

(defprotocol Player
  (make-move [this board]))
