(ns om-ttt.console.ui-spec
  (:require [om-ttt.console.ui :refer :all]
            [om-ttt.protocols.ui :refer :all]
            [om-ttt.board :as b]
            [om-ttt.spec-helper :refer [empty-board]]
            [speclj.core :refer :all]))

(def console-ui (new-console-ui))

(describe "console ui"
  (it "displays message"
    (should= "hi\n" (with-out-str (display-message console-ui "hi"))))

  (it "prompts a user for input"
    (let [prompt "Choose your character!"
          test-input-prompt #(input-prompt console-ui prompt)]
      (should= "X" (with-in-str "X" (with-redefs [println (fn [_])] (test-input-prompt))))
      (should= (str prompt "\n") (with-out-str (with-in-str "X" (test-input-prompt))))))

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
      (draw-board console-ui (b/fill-space empty-board 0 "X"))))

  (it "gets a move from a user"))
