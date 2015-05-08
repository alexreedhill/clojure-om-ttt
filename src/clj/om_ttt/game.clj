(ns om-ttt.game
  (:require [om-ttt.protocols.ui :as ui]
            [om-ttt.protocols.player :as player]
            [om-ttt.board :as b]
            [om-ttt.rules :as r]))

(declare start-game)

(defn- new-game []
  )

(defn- game-over [players board ui]
  (do
    (ui/output ui (str "Game over! " (r/winner board) " won. Would you like to play again?"))
    (if (ui/play-again? ui)
      (if (ui/same-options? ui)
        (start-game (first players) (second players) ui)
        (new-game ui))
      board)))

(defn- game-loop [players board ui]
  (if (r/game-over? board)
    (game-over players board ui)
    (recur (reverse players) (player/make-move (first players) board) ui)))

(defn- order-players [config ai-player human-player]
  (if (= "ai" (config :first-player))
    [ai-player human-player]
    [human-player ai-player]))

(defn start-game [ai-player human-player ui]
  (let [config (ui/get-configuration ui)
        players (order-players config ai-player human-player)]
    (game-loop players (b/generate (config :board-size)) ui)))
