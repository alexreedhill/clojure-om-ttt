(ns om-ttt.game.rules-spec
  (:require [om-ttt.game.rules :refer :all]
            [om-ttt.spec-helper :refer [empty-board empty-4x4-board]]
            [speclj.core :refer :all]))

(describe "rules"
  (describe "game-over?"
    (context "3x3"
      (it "knows game is not over if there is no winner and the board is not full"
          (should= false (game-over? empty-board)))

      (it "knows game is over if the board is full"
          (should= true (game-over? ["X" "O" "X"
                                     "O" "X" "X"
                                     "O" "X" "O"])))

      (it "knows the game is over if there is a winner"
          (should= true (game-over? ["X" "X" "X"
                                     "O" "O" nil
                                     "O" "X" nil]))))
    (context "4x4"
      (it "knows game is not over if there is no winner and the board is not full"
          (should= false (game-over? empty-4x4-board)))

      (it "knows game is over if the board is full"
          (should= true (game-over? ["X" "O" "X" "O"
                                     "O" "X" "X" "X"
                                     "O" "X" "O" "O"
                                     "O" "X" "X" "O"])))

      (it "knows the game is over if there is a winner"
          (should= true (game-over? ["X" "X" "X" "X"
                                     "O" "O" "O" "X"
                                     "O" nil nil nil
                                     nil nil nil nil])))))
  (describe "winner"
    (it "returns nil if there is no winner"
        (should= nil (winner empty-board)))

    (it "returns winner's token"
        (should= "X" (winner ["X" "O" "X"
                              nil "X" nil
                              nil "O" "X"])))))
