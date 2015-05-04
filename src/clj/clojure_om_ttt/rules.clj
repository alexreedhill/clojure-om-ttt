(ns clojure-om-ttt.rules
  (:require [clojure-om-ttt.board :as b]
            [clojure-om-ttt.util :refer [compact]]))

(defn partition-won? [partition]
  (and (< 0 (count (compact partition)))
       (= 1 (count (distinct partition)))))

(defn winner [board]
  (first (first (filter partition-won? (b/partitions board)))))

(defn cats-game? [board]
  (= 9 (count (compact board))))

(defn game-over? [board]
  (or (cats-game? board) (not (nil? (winner board)))))
