(ns clojure-om-ttt.rules
  (:require [clojure-om-ttt.board :as b]
            [clojure-om-ttt.util :as util]))

(defn partition-won? [partition]
  (and (< 0 (count (util/compact partition)))
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
  (= 9 (count (util/compact board))))

(defn game-over? [board]
  (or
    (cats-game? board)
    (not (nil? (winner board)))))
