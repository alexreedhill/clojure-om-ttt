(ns clojure-om-ttt.ai-spec
  (:require [clojure-om-ttt.ai :refer :all]
            [speclj.core :refer :all]
            [clojure-om-ttt.spec-helper :refer :all]))

(describe "ai"
  (describe "make move"
    (context "3x3"
      (it "blocks row"
        (should= [nil nil nil
                  "O" "O" "X"
                  "X" nil nil] (make-move [nil nil nil
                                           "O" "O" nil
                                           "X" nil nil] ["X" "O"])))

      (it "blocks column"
        (should= [nil "X" nil
                  nil "O" nil
                  "X" "O" nil] (make-move [nil nil nil
                                           nil "O" nil
                                           "X" "O" nil] ["X" "O"])))

      (it "blocks diagonal"
        (should= ["X" nil nil
                  nil "O" nil
                  "X" nil "O"] (make-move [nil nil nil
                                           nil "O" nil
                                           "X" nil "O"] ["X" "O"])))

      (it "wins row"
        (should= ["X" nil nil
                  "X" nil nil
                  "O" "O" "O"] (make-move ["X" nil nil
                                           "X" nil nil
                                           "O" "O" nil] ["O" "X"])))

      (it "wins column"
        (should= ["X" nil nil
                  "X" "O" nil
                  "X" "O" nil] (make-move [nil nil nil
                                           "X" "O" nil
                                           "X" "O" nil] ["X" "O"])))

      (it "wins diagonal"
          (should= ["O" nil nil
                    "X" "O" nil
                    "X" nil "O"] (make-move ["O" nil nil
                                             "X" "O" nil
                                             "X" nil nil] ["O" "X"]))))
    (context "4x4"
      (it "blocks deep fork"
        (should= ["X" "O" "O" "O"
                  "X" "X" nil "O"
                  "X" "X" "O" nil
                  "O" nil nil nil] (make-move ["X" "O" "O" "O"
                                               "X" "X" nil "O"
                                               "X" "X" nil nil
                                               "O" nil nil nil] ["O" "X"]))))))
