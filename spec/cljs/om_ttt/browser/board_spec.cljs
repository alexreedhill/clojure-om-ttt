(ns om-ttt.cljs.browser.board-spec
  (:require-macros [cemerick.cljs.test :refer (deftest is use-fixtures done)]
                   [cljs.core.async.macros :refer [go]]
                   [dommy.core :refer (sel sel1)])
  (:require [cemerick.cljs.test]
            [cljs.core.async :as async :refer [chan <!]]
            [cljs-react-test.utils :refer [new-container!]]
            [cljs-react-test.simulate :refer [click]]
            [om.core :as om]
            [om-ttt.browser.board :refer [board-view]]
            [om-ttt.game.board :as b]))

(defn app-fixture [f]
  (def app-state (atom {:board (b/generate 3) :move-ch (chan)}))
  (om/root board-view app-state {:target (new-container!)})
  (f))

(use-fixtures :once app-fixture)

(deftest test-empty
  (swap! app-state assoc :board (repeat 9 nil))

  (is (sel1 :#board))
  (is (= (count (sel :.cell)) (count (:board @app-state)))))

(deftest test-cell-values
  (swap! app-state assoc :board ["X" "O"])

  (is (= (.-innerHTML (first (sel "li")))) "X")
  (is (= (.-innerHTML (second (sel "li")))) "O"))

(deftest test-cell-ids
  (is (sel1 "li#cell-0"))
  (is (sel1 "li#cell-8")))

(deftest ^:async test-clicking-cell-puts-index-to-move-channel
  (def app-state (atom {:board (b/generate 3) :move-ch (chan)}))
  (om/root board-view app-state {:target (new-container!)})
  (go
    (is (= 2 (<! (:move-ch @app-state))))
    (done))
  (click (sel1 "#cell-2") nil))
