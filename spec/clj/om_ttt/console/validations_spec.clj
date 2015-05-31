(ns om-ttt.console.validations-spec
  (:require [om-ttt.console.validations :refer :all]
            [om-ttt.board :as b]
            [om-ttt.spec-helper :refer [empty-board empty-4x4-board]]
            [speclj.core :refer :all]))

(describe "validations"
  (describe "validate-boolean"
    (it "converts y into boolean true"
      (should= true (validate-boolean "y")))

    (it "converts n into boolean false"
      (should= false (validate-boolean "n")))

    (it "returns invalid for other input"
      (should= :invalid (validate-boolean "anything else"))))

  (describe "validate-board-size"
    (it "converts 3 into integer"
      (should= 3 (validate-board-size "3")))

    (it "converts 4 into integer"
      (should= 4 (validate-board-size "4")))

    (it "returns invalid for other input"
      (should= :invalid (validate-board-size "5"))))

  (describe "validate-move"
    (context "3x3"
      (it "converts input within the board's size into an integer minus 1"
        (should= 0 (validate-move "1" [empty-board])))

      (it "returns invalid for integers larger than the board's size"
        (should= :invalid (validate-move "10" [empty-board])))

      (it "returns invalid for integers smaller than the board's size"
        (should= :invalid (validate-move "0" [empty-board])))

      (it "returns invalid for integers that represent spaces that are already taken"
        (should= :invalid (validate-move "1" [(b/fill-space empty-board 0 "X")])))

      (it "returns invalid for all other input"
        (should= :invalid (validate-move "wat" [empty-board]))))

    (context "4x4"
      (it "returns invalid for integers larger than the board's size"
        (should= :invalid (validate-move "17" [empty-4x4-board])))))

  (describe "validate-token"
    (it "accepts tokens that are one character in length"
      (should= "A" (validate-token "A")))

    (it "accepts tokens that are one character in length"
      (should= :invalid (validate-token "AA"))))

  (describe "validate-first-player"
    (it "validates human string"
      (should= "human" (validate-first-player "human")))

    (it "validates ai string"
      (should= "ai" (validate-first-player "ai")))

    (it "returns invalid for all other input"
      (should= :invalid (validate-first-player "1")))

    ))
