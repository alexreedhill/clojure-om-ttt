(ns om-ttt.console.ui
  (:require [om-ttt.board :as b]
            [om-ttt.console.messages :as m]
            [om-ttt.console.validations :as v]
            [om-ttt.protocols.ui :refer [UI] :as ui]
            [om-ttt.util :refer [transpose]]))

(defn- validator-for [prompt]
  (condp = prompt
    m/human-token-prompt v/validate-token
    m/ai-token-prompt v/validate-token
    m/board-size-prompt v/validate-board-size
    m/first-player-prompt v/validate-first-player
    m/play-again-prompt v/validate-boolean
    m/same-options-prompt v/validate-boolean
    (fn [input & _] input)))

(defn- validation-loop [validator & validator-args]
  (let [input (read-line)
        validation-response (validator input validator-args)]
    (if (= :invalid validation-response)
      (do
        (println "That input was not valid." "\n")
        (recur validator validator-args))
      validation-response)))

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
    (println string "\n"))

  (input-prompt [this prompt]
    (ui/display-message this prompt)
    (validation-loop (validator-for prompt)))

  (draw-board [this board]
    (println (board->string board)))

  (move [this board]
    (ui/display-message this m/player-move-prompt)
    (validation-loop v/validate-move board)))

(defn new-console-ui []
  (ConsoleUI.))
