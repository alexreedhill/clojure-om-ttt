(ns clojure-om-ttt.ai
  (:require [clojure-om-ttt.board :as b]
            [clojure-om-ttt.rules :as r]
            [clojure-om-ttt.util :refer [compact]]
            [clojure.core.match :refer [match]]))

(declare negamax)
(def DEPTH 5)

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

(defn make-move [board tokens]
  (let [scores (score-moves board tokens DEPTH)
        best-score (negamax board tokens DEPTH)]
    (nth (potential-moves board (first tokens)) (.indexOf scores best-score))))
