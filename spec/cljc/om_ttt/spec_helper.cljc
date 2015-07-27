(ns om-ttt.spec-helper
  (:require [om-ttt.mock-ui :refer [new-mock-ui]]))

(def empty-board
  [nil nil nil
   nil nil nil
   nil nil nil])

(def empty-4x4-board
  [nil nil nil nil
   nil nil nil nil
   nil nil nil nil
   nil nil nil nil])

(def mock-config
  {:human-token "O"
   :ai-token "X"
   :board-size 3
   :first-player "human"})

(def mock-ui (new-mock-ui mock-config))
