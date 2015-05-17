(ns om-ttt.players.ai-spec
  (:require [om-ttt.players.ai :refer [new-ai-player]]
            [om-ttt.protocols.player :refer [make-move]]
            [speclj.core :refer :all]))

(def ai-player-x (new-ai-player "X" "O"))
(def ai-player-o (new-ai-player "O" "X"))

(describe "ai"
  (describe "make move"
    (context "3x3"
      (it "blocks row"
        (should= [nil nil nil
                  "O" "O" "X"
                  "X" nil nil] (make-move ai-player-x [nil nil nil
                                                       "O" "O" nil
                                                       "X" nil nil])))

      (it "blocks column"
        (should= [nil "X" nil
                  nil "O" nil
                  "X" "O" nil] (make-move ai-player-x [nil nil nil
                                                       nil "O" nil
                                                       "X" "O" nil])))

      (it "blocks diagonal"
        (should= ["X" nil nil
                  nil "O" nil
                  "X" nil "O"] (make-move ai-player-x [nil nil nil
                                                       nil "O" nil
                                                       "X" nil "O"])))

      (it "wins row"
        (should= ["X" nil nil
                  "X" nil nil
                  "O" "O" "O"] (make-move ai-player-o ["X" nil nil
                                                       "X" nil nil
                                                       "O" "O" nil])))

      (it "wins column"
        (should= ["X" nil nil
                  "X" "O" nil
                  "X" "O" nil] (make-move ai-player-x [nil nil nil
                                                       "X" "O" nil
                                                       "X" "O" nil])))

      (it "wins diagonal"
          (should= ["O" nil nil
                    "X" "O" nil
                    "X" nil "O"] (make-move ai-player-o ["O" nil nil
                                                         "X" "O" nil
                                                         "X" nil nil])))

      (it "blocks fork"
          (should= ["X" nil nil
                    nil "O" nil
                    nil nil nil] (make-move ai-player-o ["X" nil nil
                                                         nil nil nil
                                                         nil nil nil]))))

    (context "4x4"
      (it "blocks row"
        (should= ["O" "X" "O" "X"
                  "O" "O" "O" "X"
                  "X" nil nil nil
                  "X" "O" "X" nil]  (make-move ai-player-x ["O" "X" "O" "X"
                                                            "O" "O" "O" nil
                                                            "X" nil nil nil
                                                            "X" "O" "X" nil])))

      (it "blocks column"
        (should= [nil "X" nil nil
                  "X" "O" "X" "X"
                  "X" "O" "X" "O"
                  "O" "O" "O" "X"] (make-move ai-player-x [nil nil nil nil
                                                           "X" "O" "X" "X"
                                                           "X" "O" "X" "O"
                                                           "O" "O" "O" "X"])))

      (it "blocks diagonal"
        (should= ["O" nil "O" nil
                  "X" "X" "X" "O"
                  "X" "O" "X" "O"
                  "O" "O" "O" "X"] (make-move ai-player-o [nil nil "O" nil
                                                           "X" "X" "X" "O"
                                                           "X" "O" "X" "O"
                                                           "O" "O" "O" "X"])))

      (it "wins row"
        (should= ["X" nil nil nil
                  "X" "X" nil nil
                  "O" "O" "O" "O"
                  nil "X" "X" "X"]  (make-move ai-player-o ["X" nil nil nil
                                                           "X" "X" nil nil
                                                           nil "O" "O" "O"
                                                           nil "X" "X" "X"])))

      (it "wins column"
        (should= ["X" nil nil nil
                  "X" "O" nil "O"
                  "X" "O" "O" nil
                  "X" "O" "X" nil] (make-move ai-player-x [nil nil nil nil
                                                           "X" "O" nil "O"
                                                           "X" "O" "O" nil
                                                           "X" "O" "X" nil])))

      (it "wins diagonal"
          (should= ["O" nil "X" "X"
                    "X" "O" nil nil
                    "X" "X" "O" nil
                    nil "X" "O" "O"] (make-move ai-player-o [nil nil "X" "X"
                                                             "X" "O" nil nil
                                                             "X" "X" "O" nil
                                                             nil "X" "O" "O"])))
      (it "blocks fork"
        (should-contain
          (make-move ai-player-o
            ["X" "O" "O" "O"
             "X" "X" nil "O"
             "X" "X" nil nil
             "O" nil nil nil])

             [["X" "O" "O" "O"
               "X" "X" nil "O"
               "X" "X" "O" nil
               "O" nil nil nil]

              ["X" "O" "O" "O"
               "X" "X" nil "O"
               "X" "X" nil "O"
               "O" nil nil nil]

              ["X" "O" "O" "O"
               "X" "X" nil "O"
               "X" "X" nil nil
               "O" nil nil "O"]])))))
