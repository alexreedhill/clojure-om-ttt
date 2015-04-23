(ns clojure-om-ttt.rules
  (:require [clojure-om-ttt.board :as b]))

(defn compact [board]
  (filter #(not (nil? %)) board))

(defn partition-won? [partition]
  (and (< 0 (count (compact partition)))
       (= 1 (count (distinct partition)))))

(defn winner [board]
  (reduce
    (fn [acc partition]
      (if (partition-won? partition)
        (first partition)
        acc))
    nil
    (b/partitions board)))

(defn cats-game? [board]
  (= 9 (count (compact board))))

(defn game-over? [board]
  (or
    (cats-game? board)
    (not (nil? (winner board)))))
