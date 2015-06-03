(ns om-ttt.game.rules
  (:require [om-ttt.game.board :as b]
            [om-ttt.util :refer [compact]]))

(defn partition-won? [partition]
  (and (< 0 (count (compact partition)))
       (= 1 (count (distinct partition)))))

(defn winner [board]
  (first (first (filter partition-won? (b/partitions board)))))

(defn cats-game? [board]
  (= (count board) (count (compact board))))

(defn game-over? [board]
  (or (cats-game? board) (not (nil? (winner board)))))
