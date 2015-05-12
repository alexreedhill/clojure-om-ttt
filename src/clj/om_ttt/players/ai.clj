(ns om-ttt.players.ai
  (:require [om-ttt.board :as b]
            [om-ttt.rules :as r]
            [om-ttt.protocols.player :refer [Player]]
            [om-ttt.util :refer [compact]]
            [clojure.core.match :refer [match]]))

(declare negamax)
(def DEPTH 7)

(defn- score [board [max-token min-token] depth]
  (match [(r/winner board)]
    [max-token] depth
    [min-token] (- depth)
    :else 0))

(defn- potential-moves [board token]
  (->> board
       (map-indexed (fn [i space] (if (nil? space) (b/fill-space board i token))))
       (compact)))

(defn- score-moves [board tokens depth]
  (->> (potential-moves board (first tokens))
       (map #(- (negamax % (reverse tokens) (- depth 1))))))

(defn- negamax [board tokens depth]
  (if (or (r/game-over? board) (= depth 0))
    (score board tokens depth)
    (apply max (score-moves board tokens depth))))

(def negamax (memoize negamax))

(deftype AiPlayer [tokens]
  Player
  (make-move [this board]
    (let [scores (score-moves board tokens DEPTH)
          best-score (apply max scores)
          best-moves (compact (map-indexed (fn [i score] (if (= score best-score) i)) scores))]
      (nth (potential-moves board (first tokens)) (rand-nth best-moves)))))

(defn new-ai-player [player-token opponent-token]
  (AiPlayer. [player-token opponent-token]))
