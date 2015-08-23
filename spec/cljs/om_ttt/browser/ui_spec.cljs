(ns om-ttt.browser.ui-spec
  (:require-macros [cemerick.cljs.test :refer (deftest is done)]
                   [cljs.core.async.macros :refer [go]])
  (:require [cemerick.cljs.test]
            [cljs.core.async :refer [chan >!]]
            [om-ttt.browser.ui :refer [new-browser-ui]]
            [om-ttt.game.board :as b]
            [om-ttt.protocols.ui :as ui]))

(deftest test-draw-board-updates-board
  (let [app-state (atom {:board (b/generate 3) :move-ch (chan)})
        browser-ui (new-browser-ui app-state)
        new-board (b/fill-space (:board @app-state) 0 "X")]
    (ui/draw-board browser-ui new-board)

    (is (= (:board @app-state) new-board))))

(deftest ^:async test-move-takes-from-move-channel-and-fills-space
  (let [app-state (atom {:board (b/generate 3) :move-ch (chan)})
        browser-ui (new-browser-ui app-state)]
        (go
          (ui/move browser-ui (:board @app-state) "X")
          (>! (:move-ch @app-state) 0)
          (is (= (first (:board @app-state)) "X"))
          (done))))
