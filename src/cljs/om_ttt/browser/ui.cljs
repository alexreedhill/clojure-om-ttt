(ns om-ttt.browser.ui
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs.core.async :refer [<!]]
            [om-ttt.game.board :as b]
            [om-ttt.protocols.ui :refer [UI] :as ui]))

(enable-console-print!)

(deftype BrowserUI [app-state]
  UI
  (draw-board [this board]
    (swap! app-state assoc :board board))

  (move [this board token]
    (go
      (let [move (<! (:move-ch @app-state))]
        (ui/draw-board this (b/fill-space (vec board) move token))))))

(defn new-browser-ui [app-state]
  (BrowserUI. app-state))
