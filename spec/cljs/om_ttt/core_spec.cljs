(ns om-ttt.cljs.core-spec
  (:require-macros [cemerick.cljs.test :refer (deftest is testing use-fixtures)]
                   [dommy.core :refer (sel sel1)])
  (:require [om-ttt.core :as core :refer [app-state draw-board]]
            [cemerick.cljs.test]
            [dommy.core :as dommy]
            [om.core :as om]))

(defn new-node [id]
  (-> (dommy/create-element "div")
      (dommy/set-attr! "id" id)))

(defn append-node [node]
  (dommy/append! (sel1 js/document :body) node))

(defn app-fixture [f]
  (->> (new-node "app") (append-node))
  (f))

(use-fixtures :once app-fixture)

(deftest test-draw-empty-board
  (swap! app-state assoc :board (repeat 9 nil))
  (draw-board)
  (is (sel1 :#board))
  (is (= (count (sel :.cell)) (count (:board @app-state)))))

(deftest test-draw-board-with-cell-values
  (swap! app-state assoc :board ["X" "O"])
  (is (= (dommy/text (first (sel "li")))) "X")
  (is (= (dommy/text (second (sel "li")))) "O"))
