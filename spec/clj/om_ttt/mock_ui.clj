(ns om-ttt.mock-ui
  (:require [om-ttt.protocols.ui :refer [UI]]))

(def play-again-counter (atom 0))

(deftype MockUi []
  UI
  (display-message [this string] string)

  (user-input [this])

  (move [this board] (.indexOf board nil))

  (restart? [this]
    (if (> @play-again-counter 1)
      false
      (do (swap! play-again-counter inc) true)))

  (same-config? [this] true))

(defn new-mock-ui []
  (MockUi.))
