(ns om-ttt.console.ui-spec
  (:require [om-ttt.console.ui :refer :all]
            [om-ttt.protocols.ui :refer :all]
            [om-ttt.board :as b]
            [om-ttt.console.messages :as m]
            [om-ttt.console.validations :as v]
            [om-ttt.spec-helper :refer [empty-board]]
            [speclj.core :refer :all]))

(def console-ui (new-console-ui))

(describe "console ui"
  (around [it] (with-out-str (it)))

  (it "displays message"
    (should= "hi\n" (with-out-str (display-message console-ui "hi"))))

  (describe "input-prompt"
    (it "prompts a user for input"
      (let [prompt "Choose your character!"
            test-input-prompt #(input-prompt console-ui prompt)]
        (should= "X" (with-in-str "X" (test-input-prompt)))
        (should= (str prompt "\n") (with-out-str (with-in-str "X" (test-input-prompt))))))

      (context "human-token-prompt"
        (it "delegates validation to v/validate-token"
          (should-invoke v/validate-token {}
            (with-in-str "X" (input-prompt console-ui m/human-token-prompt)))))

      (context "ai-token-prompt"
        (it "delegates validation to v/validate-token"
          (should-invoke v/validate-token {}
            (with-in-str "X" (input-prompt console-ui m/ai-token-prompt)))))

      (context "board-size-prompt"
        (it "delegates validation to v/validate-board-size"
          (should-invoke v/validate-board-size {}
            (with-in-str "3" (input-prompt console-ui m/board-size-prompt)))))

      (context "first-player-prompt"
        (it "delegates validation to v/validate-first-player"
          (should-invoke v/validate-first-player {}
            (with-in-str "ai" (input-prompt console-ui m/first-player-prompt)))))

      (context "play-again-prompt"
        (it "delegates validation to v/validate-boolean"
          (should-invoke v/validate-boolean {}
            (with-in-str "y" (input-prompt console-ui m/play-again-prompt)))))

      (context "same-options-prompt"
        (it "delegates validation to v/validate-boolean"
          (should-invoke v/validate-boolean {}
            (with-in-str "y" (input-prompt console-ui m/same-options-prompt))))))

  (it "draws the board"
    (should= (str
               "     |     |     \n"
               "  X  |     |     \n"
               "_____|_____|_____\n"
               "     |     |     \n"
               "     |     |     \n"
               "_____|_____|_____\n"
               "     |     |     \n"
               "     |     |     \n"
               "     |     |     \n")
      (with-out-str (draw-board console-ui (b/fill-space empty-board 0 "X")))))

  (describe "move"
    (it "gets a move from a user"
      (should= (str m/player-move-prompt "\n") (with-out-str (with-in-str "0" (move console-ui empty-board))))
      (should= 0 (with-in-str "1" (move console-ui empty-board))))

      (it "delegates validation to v/validate-move"
        (should-invoke v/validate-move {} (with-in-str "y" (move console-ui empty-board))))))
