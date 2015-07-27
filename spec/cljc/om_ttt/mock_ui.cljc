(ns om-ttt.mock-ui
  (:require [om-ttt.messages :as m]
            [om-ttt.protocols.ui :refer [UI]]))

(def play-again-counter (atom 0))

(defn restart? []
  (if (> @play-again-counter 1)
    false
    (do (swap! play-again-counter inc) true)))

(deftype MockUI [config]
  UI
  (display-message [this message]
    message)

  (input-prompt [this prompt]
    (condp = prompt
      m/human-token-prompt (config :human-token)
      m/ai-token-prompt  (config :ai-token)
      m/board-size-prompt (config :board-size)
      m/first-player-prompt (config :first-player)
      m/play-again-prompt (restart?)
      m/same-options-prompt false
      nil))

  (draw-board [this board]
    board)

  (move [this board] (.indexOf board nil)))

(defn new-mock-ui [config]
  (MockUI. config))
