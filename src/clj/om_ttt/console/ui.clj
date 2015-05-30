(ns om-ttt.console.ui
  (:require [om-ttt.protocols.ui :refer [UI] :as ui]
            [om-ttt.board :as b]
            [om-ttt.console.messages :as m]
            [om-ttt.console.validations :as v]
            [om-ttt.util :refer [transpose]]))

(defn validate-prompt [prompt]
  (let [input (read-line)]
    (condp = prompt
      m/human-token-prompt (v/validate-token input)
      m/ai-token-prompt (v/validate-token input)
      m/board-size-prompt (v/validate-board-size input)
      m/first-player-prompt (v/validate-first-player input)
      m/play-again-prompt (v/validate-boolean input)
      m/same-options-prompt (v/validate-boolean input)
      input)))

(defn- row-divider [board index]
  (if (> index (- (count board) (b/height board)))
    " "
    "_"))

(defn- column-divider [board index]
  (if (= 0 (mod index (b/height board)))
    "\n"
    "|"))

(defn- cell->lines [token row-divider column-divider]
  [(str "     " column-divider)
   (str "  " (or token " ") "  " column-divider)
   (str (apply str (repeat 5 row-divider)) column-divider)])

(defn- board->lines [board]
  (map-indexed
    (fn [i cell]
      (cell->lines cell (row-divider board (+ i 1)) (column-divider board (+ i 1))))
    board))

(defn- board->string [board]
  (->> (board->lines board)
       (b/rows)
       (map transpose)
       (flatten)
       (apply str)))

(deftype ConsoleUI []
  UI
  (display-message [this string]
    (println string))

  (input-prompt [this prompt]
    (ui/display-message this prompt)
    (validate-prompt prompt))

  (draw-board [this board]
    (print (board->string board)))

  (move [this board]
    (v/validate-move (ui/input-prompt this m/player-move-prompt) board)))

(defn new-console-ui []
  (ConsoleUI.))
