(ns clojure-om-ttt.rules-spec
  (:require [clojure-om-ttt.rules :refer :all]
            [speclj.core :refer :all]
            [clojure-om-ttt.spec-helper :refer :all]))

(describe "rules"
  (describe "game-over?"
    (it "knows game is not over if there is no winner and the board is not full"
        (should= false (game-over? empty-board)))

    (it "knows game is over if the board is full"
        (should= true (game-over? ["X" "O" "X"
                                   "O" "X" "X"
                                   "O" "X" "O"])))

    (it "knows the game is over if there is a winner"
        (should= true (game-over? ["X" "X" "X"
                                   "O" "O" nil
                                   "O" "X" nil])))

    (it "knows the game is over if there is a winner"
        (should= true (game-over? ["X" "X" "X"
                                   "O" "O" nil
                                   "O" "X" nil]))))
  (describe "winner"
    (it "returns nil if there is no winner"
        (should= nil (winner empty-board)))

    (it "returns winner's token"
        (should= "X" (winner ["X" "O" "X"
                              nil "X" nil
                              nil "O" "X"])))))
