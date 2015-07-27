(ns om-ttt.game.rules-spec
  (:require [om-ttt.game.rules :as rules]
            [om-ttt.spec-helper :refer [empty-board empty-4x4-board]]
   #?(:clj  [speclj.core :refer [describe context it should=]]
      :cljs [speclj.core :refer-macros [describe context it should=]])))

(describe "rules"
  (describe "game-over?"
    (context "3x3"
      (it "knows game is not over if there is no winner and the board is not full"
          (should= false (rules/game-over? empty-board)))

      (it "knows game is over if the board is full"
          (should= true (rules/game-over? ["X" "O" "X"
                                     "O" "X" "X"
                                     "O" "X" "O"])))

      (it "knows the game is over if there is a winner"
          (should= true (rules/game-over? ["X" "X" "X"
                                     "O" "O" nil
                                     "O" "X" nil]))))
    (context "4x4"
      (it "knows game is not over if there is no winner and the board is not full"
          (should= false (rules/game-over? empty-4x4-board)))

      (it "knows game is over if the board is full"
          (should= true (rules/game-over? ["X" "O" "X" "O"
                                     "O" "X" "X" "X"
                                     "O" "X" "O" "O"
                                     "O" "X" "X" "O"])))

      (it "knows the game is over if there is a winner"
          (should= true (rules/game-over? ["X" "X" "X" "X"
                                     "O" "O" "O" "X"
                                     "O" nil nil nil
                                     nil nil nil nil])))))
  (describe "winner"
    (it "returns nil if there is no winner"
        (should= nil (rules/winner empty-board)))

    (it "returns winner's token"
        (should= "X" (rules/winner ["X" "O" "X"
                              nil "X" nil
                              nil "O" "X"])))))
