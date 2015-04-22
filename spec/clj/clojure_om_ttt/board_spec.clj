(ns clojure-om-ttt.board-spec
  (:require [clojure-om-ttt.board :refer :all]
            [speclj.core :refer :all]
            [clojure-om-ttt.spec-helper :refer :all]))

(describe "board"
  (it "generates an empty board"
      (should= empty-board (generate)))

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
