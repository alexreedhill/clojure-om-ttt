(ns clojure-om-ttt.board)

(defn generate []
  (vec (repeat 9 nil)))

(defn fill-space [board index value]
  (assoc board index value))

(defn rows [board]
 (partition 3 board))

(defn transpose [m]
  (apply mapv vector m))

(defn columns [board]
  (transpose (rows board)))

(defn diagonal [board]
  [(board 0) (board 4) (board 8)])

(defn reverse-rows [board]
  (->> board (rows) (map reverse) (flatten) (vec)))

(defn diagonals [board]
  (vector (diagonal board)
          (diagonal (reverse-rows board))))
