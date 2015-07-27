(ns om-ttt.game.board-spec
  (:require [om-ttt.game.board :as board]
            [om-ttt.spec-helper :refer [empty-board empty-4x4-board]]
   #?(:clj  [speclj.core :refer [describe context it should=]]
      :cljs [speclj.core :refer-macros [describe context it should=]])))

(describe "board"
  (context "3x3"
    (it "generates an empty board"
        (should= empty-board (board/generate 3)))

    (it "fills a space"
        (should= ["X" nil nil
                  nil nil nil
                  nil nil nil]
          (board/fill-space empty-board 0 "X")))

    (it "returns the rows"
        (should=  [[nil "X" nil]
                   [nil nil nil]
                   [nil nil nil]]
          (-> (board/fill-space empty-board 1 "X")
              (board/rows))))

    (it "returns the columns"
        (should=  [[nil nil nil]
                   ["X" nil nil]
                   [nil nil nil]]
          (-> (board/fill-space empty-board 1 "X")
              (board/columns))))

    (it "returns the diagonals"
        (should=  [["X" "X" nil]
                   ["O" "X" nil]]

          (board/diagonals ["X" nil "O"
                            nil "X" nil
                            nil nil nil])))

    (it "returns all of the partitions"
        (should= [["X" nil "O"]
                  [nil "X" nil]
                  [nil nil nil]

                  ["X" nil nil]
                  [nil "X" nil]
                  ["O" nil nil]

                  ["X" "X" nil]
                  ["O" "X" nil]]

                 (board/partitions ["X" nil "O"
                                    nil "X" nil
                                    nil nil nil]))))
  (context "4x4"
    (it "generates an empty board"
      (should= (board/generate 4) empty-4x4-board))

    (it "returns the height"
      (should= 4 (board/height (board/generate 4))))

    (it "returns the rows"
        (should= [[nil nil nil "X"]
                  [nil nil nil nil]
                  [nil nil nil nil]
                  [nil nil nil nil]]
          (-> (board/fill-space empty-4x4-board 3 "X")
              (board/rows))))

    (it "returns the columns"
        (should=  [[nil nil nil nil]
                   ["X" nil nil nil]
                   [nil nil nil nil]
                   [nil nil nil nil]]
          (-> (board/fill-space empty-4x4-board 1 "X")
              (board/columns))))

    (it "returns the diagonals"
        (should=  [["X" "O" nil nil]
                   ["O" "X" nil nil]]

          (board/diagonals ["X" nil nil "O"
                            nil "O" "X" nil
                            nil nil nil nil
                            nil nil nil nil]))))

    (it "returns all of the partitions"
        (should= [["X" nil nil "O"]
                  [nil "O" "X" nil]
                  [nil nil nil nil]
                  [nil nil nil nil]

                  ["X" nil nil nil]
                  [nil "O" nil nil]
                  [nil "X" nil nil]
                  ["O" nil nil nil]

                  ["X" "O" nil nil]
                  ["O" "X" nil nil]]

                 (board/partitions ["X" nil nil "O"
                                    nil "O" "X" nil
                                    nil nil nil nil
                                    nil nil nil nil]))))
