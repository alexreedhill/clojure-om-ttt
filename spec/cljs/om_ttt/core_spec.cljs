(ns om-ttt.cljs.core-spec
  (:require-macros [cemerick.cljs.test :refer (deftest is testing)]
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

(defn setup-app-fixture! []
  (->> (new-node "app") (append-node)))

(deftest test-draw-board
  (testing "draws empty board"
    (setup-app-fixture!)
    (swap! app-state assoc :board (repeat 9 nil))
    (draw-board)
    (is (sel1 :#board))
    (is (= (count (sel :.cell)) (count (:board @app-state))))))
