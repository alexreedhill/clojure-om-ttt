(ns om-ttt.board-spec
  (:require [om-ttt.board :refer :all]
            [speclj.core :refer :all]
            [om-ttt.spec-helper :refer :all]))

(describe "board"
  (context "3x3"
    (it "generates an empty board"
        (should= empty-board (generate 3)))

    (it "fills a space"
        (should= ["X" nil nil
                  nil nil nil
                  nil nil nil]
          (fill-space empty-board 0 "X")))

    (it "returns the rows"
        (should=  [[nil "X" nil]
                   [nil nil nil]
                   [nil nil nil]]
          (-> (fill-space empty-board 1 "X")
              (rows))))

    (it "returns the columns"
        (should=  [[nil nil nil]
                   ["X" nil nil]
                   [nil nil nil]]
          (-> (fill-space empty-board 1 "X")
              (columns))))

    (it "returns the diagonals"
        (should=  [["X" "X" nil]
                   ["O" "X" nil]]

          (diagonals ["X" nil "O"
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

                 (partitions ["X" nil "O"
                              nil "X" nil
                              nil nil nil]))))
  (context "4x4"
    (it "generates an empty board"
      (should= (generate 4) empty-4x4-board))

    (it "returns the height"
      (should= 4 (height (generate 4))))

    (it "returns the rows"
        (should= [[nil nil nil "X"]
                  [nil nil nil nil]
                  [nil nil nil nil]
                  [nil nil nil nil]]
          (-> (fill-space empty-4x4-board 3 "X")
              (rows))))

    (it "returns the columns"
        (should=  [[nil nil nil nil]
                   ["X" nil nil nil]
                   [nil nil nil nil]
                   [nil nil nil nil]]
          (-> (fill-space empty-4x4-board 1 "X")
              (columns))))

    (it "returns the diagonals"
        (should=  [["X" "O" nil nil]
                   ["O" "X" nil nil]]

          (diagonals ["X" nil nil "O"
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

                 (partitions ["X" nil nil "O"
                              nil "O" "X" nil
                              nil nil nil nil
                              nil nil nil nil]))))
