(ns om-ttt.cljs.browser.ui-spec
  (:require-macros [cemerick.cljs.test :refer (deftest is testing use-fixtures done)]
                   [cljs.core.async.macros :refer [go]]
                   [dommy.core :refer (sel sel1)])

  (:require [cemerick.cljs.test]
            [cljs.core.async :refer [chan close! <!]]
            [dommy.core :as dommy]
            [om.core :as om]
            [om-ttt.browser.ui :refer [app-state new-browser-ui]]
            [om-ttt.protocols.ui :as ui]))

(defn new-node [id]
  (-> (dommy/create-element "div")
      (dommy/set-attr! "id" id)))

(defn append-node [node]
  (dommy/append! (sel1 js/document :body) node))

(defn app-fixture [f]
  (append-node (new-node "app"))
  (def browser-ui (new-browser-ui))
  (f))

(defn simulate-click-event [el]
  (let [document (.-document js/window)]
    (cond
     (.-click el) (.click el)
     (.-createEvent document) (let [e (.createEvent document "MouseEvents")]
                                (.initMouseEvent e "click" true true
                                                 js/window 0 0 0 0 0
                                                 false false false false 0 nil)
                                (.dispatchEvent el e))
     :default (throw "Unable to simulate click event"))))

(defn- do-later
  [timeout fn]
    (js/setTimeout fn timeout))

(use-fixtures :once app-fixture)

(deftest test-draw-empty-board
  (swap! app-state assoc :board (repeat 9 nil))

  (is (sel1 :#board))
  (is (= (count (sel :.cell)) (count (:board @app-state)))))

(deftest test-draw-board-with-cell-values
  (swap! app-state assoc :board ["X" "O"])

  (is (= (dommy/text (first (sel "li")))) "X")
  (is (= (dommy/text (second (sel "li")))) "O"))

(deftest test-draw-board-with-cell-ids
  (is (sel1 "li#cell-0"))
  (is (sel1 "li#cell-8")))

(deftest test-implements-ui-protocol-updates-app-state
  (let [board      (repeat 9 "X")]
    (ui/draw-board browser-ui board)

    (is (= (:board @app-state) board))))

(deftest ^:async move-listens-for-click-event
  (let [board (repeat 9 nil)]
    (ui/move browser-ui board "X")
    (do-later 100
      (fn []
        (simulate-click-event (sel1 "#cell-0"))
        (is (= (first (:board @app-state)) "X"))
        (done)))))
