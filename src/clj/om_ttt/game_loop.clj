(ns om-ttt.game-loop
  (:require [om-ttt.protocols.ui :as ui]
            [om-ttt.protocols.player :as player]
            [om-ttt.board :as b]
            [om-ttt.rules :as r]))

(declare start-game)

(defn- new-game []
  )

(defn- game-over [config players board ui]
  (do
    (ui/display-message ui (str "Game over! " (r/winner board) " won. Would you like to play again?"))
    (if (ui/restart? ui)
      (if (ui/same-config? ui)
        (start-game config (first players) (second players) ui)
        (new-game ui))
      board)))

(defn- game-loop [config players board ui]
  (if (r/game-over? board)
    (game-over config players board ui)
    (recur config (reverse players) (player/make-move (first players) board) ui)))

(defn- order-players [config ai-player human-player]
  (if (= "ai" (config :first-player))
    [ai-player human-player]
    [human-player ai-player]))

(defn start-game [config ai-player human-player ui]
  (let [players (order-players config ai-player human-player)]
    (game-loop config players (b/generate (config :board-size)) ui)))
