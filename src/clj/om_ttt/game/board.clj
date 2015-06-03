(ns om-ttt.game.board
  (:require [clojure.math.numeric-tower :as math]
            [om-ttt.util :refer [transpose]]))

(defn generate [height]
  (vec (repeat (* height height) nil)))

(defn fill-space [board index value]
  (assoc board index value))

(defn height [board]
  (math/sqrt (count board)))

(defn rows [board]
  (partition (height board) board))

(defn columns [board]
  (transpose (rows board)))

(defn diagonal [board]
  (map-indexed (fn [i row] (get (vec row) i)) (rows board)))

(defn reverse-rows [board]
  (->> board (rows) (map reverse) (flatten) (vec)))

(defn diagonals [board]
  (vector (diagonal board) (diagonal (reverse-rows board))))

(defn partitions [board]
  (concat (rows board) (columns board) (diagonals board)))
