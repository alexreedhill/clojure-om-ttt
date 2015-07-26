(ns om-ttt.players.ai
  (:require [om-ttt.game.board :as b]
            [om-ttt.protocols.player :refer [Player]]
            [om-ttt.game.rules :as r]
            [om-ttt.util :refer [compact]]))

(declare score-moves)
(def start-depth 7)

(defn- score [board [max-token min-token] depth]
  (condp = (r/winner board)
    max-token  depth
    min-token (- depth)
    0))

(defn- negamax [board tokens depth]
  (if (or (r/game-over? board) (= depth 0))
    (score board tokens depth)
    (apply max (score-moves board tokens depth))))

(def negamax (memoize negamax))

(defn- potential-moves [board token]
  (->> board
       (map-indexed (fn [i space] (if (nil? space) (b/fill-space board i token))))
       compact))

(defn- score-moves [board tokens depth]
  (->> (potential-moves board (first tokens))
       (map #(- (negamax % (reverse tokens) (- depth 1))))))

(defn- best-moves [board tokens]
  (let [scores (score-moves board tokens start-depth)
        best-score (apply max scores)]
    (compact (map-indexed (fn [i score] (if (= score best-score) i)) scores))))

(deftype AiPlayer [tokens]
  Player
  (make-move [this board]
    (nth (potential-moves board (first tokens)) (rand-nth (best-moves board tokens)))))

(defn new-ai-player [player-token opponent-token]
  (AiPlayer. [player-token opponent-token]))
