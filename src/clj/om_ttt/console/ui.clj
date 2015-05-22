(ns om-ttt.console.ui
  (:require [om-ttt.protocols.ui :refer [UI] :as ui]
            [om-ttt.board :as b]
            [om-ttt.util :refer [transpose]]))

(defn- last-in-column? [board index]
  (> index (- (count board) (b/height board))))

(defn- column-divider [board index]
  (if (last-in-column? board index)
    " "
    "_"))

(defn- last-in-row? [board index]
  (-> (= 0 (mod index (b/height board))) (and (not (= index 1)))))

(defn- row-divider [board index]
  (if (last-in-row? board index)
    "\n"
    "|"))

(defn- format-cell-lines [token row-divider column-divider]
  [(str "     " row-divider)
   (str "  " (or token " ") "  " row-divider)
   (str (apply str (repeat 5 column-divider)) row-divider)])

(defn- board->lines [board]
  (loop [i 1
         lines []]
    (if (> i (count board))
      lines
      (recur
        (inc i)
        (conj lines (format-cell-lines (get board (- i 1)) (row-divider board i) (column-divider board i)))))))

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
    (read-line))

  (draw-board [this board]
    (board->string board)))

(defn new-console-ui []
  (ConsoleUI.))
