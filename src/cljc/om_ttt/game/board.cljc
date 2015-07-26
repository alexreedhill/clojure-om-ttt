(ns om-ttt.game.board
  (:require [om-ttt.util :refer [transpose]]))

(defn generate [height]
  (vec (repeat (* height height) nil)))

(defn fill-space [board index value]
  (assoc board index value))

(defn height [board]
  (let [size (count board)]
    #?(:clj (if (> size 0) (int (Math/sqrt size)) 0)
       :cljs (.sqrt js/Math size))))

(defn rows [board]
  (partition (height board) board))

(defn columns [board]
  (transpose (rows board)))

(defn- diagonal [board]
  (map-indexed (fn [i row] (get (vec row) i)) (rows board)))

(defn- reverse-rows [board]
  (->> board (rows) (map reverse) (flatten) (vec)))

(defn diagonals [board]
  (vector (diagonal board) (diagonal (reverse-rows board))))

(defn partitions [board]
  (concat (rows board) (columns board) (diagonals board)))
