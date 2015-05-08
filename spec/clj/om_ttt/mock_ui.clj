(ns om-ttt.mock-ui
  (:require [om-ttt.protocols.ui :refer [UI]]))

(def play-again-counter (atom 0))

(deftype MockUi [configuration]
  UI
  (get-configuration [this] configuration)

  (get-move [this board] (.indexOf board nil))

  (output [this string] string)

  (play-again? [this]
    (if (= @play-again-counter 2)
      false
      (do (swap! play-again-counter inc) true)))

  (same-options? [this] true))

(defn new-mock-ui [configuration]
  (MockUi. configuration))
