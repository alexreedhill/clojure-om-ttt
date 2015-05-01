(ns clojure-om-ttt.ai
  (:require [clojure-om-ttt.board :as b]
            [clojure-om-ttt.rules :as r]
            [clojure-om-ttt.util :as util]))

(declare negamax)
(def DEPTH 6)

(defn score [board tokens depth]
  (let [winner (r/winner board)]
    (cond (= winner (first tokens)) (* 1 depth)
          (= winner (last tokens)) (* -1 depth)
          :else 0)))

(defn potential-moves [board token]
  (->> board
       (map-indexed (fn [i space] (if (nil? space) (b/fill-space board i token))))
       (util/compact)))

(defn score-moves [board tokens depth]
  (->> (potential-moves board (first tokens))
       (map #(- (negamax % (reverse tokens) (- depth 1))))))

(defn negamax [board tokens depth]
  (if (r/game-over? board)
    (score board tokens depth)
    (apply max (score-moves board tokens depth))))

(defn make-move [board tokens]
  (let [scores (score-moves board tokens DEPTH)
        best-score (negamax board tokens DEPTH)]
    (nth (potential-moves board (first tokens)) (.indexOf scores best-score))))
