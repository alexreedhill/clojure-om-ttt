(ns om-ttt.browser.ui
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs.core.async :refer [put! chan <!]]
            [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [om-ttt.game.board :as b]
            [om-ttt.protocols.ui :refer [UI] :as ui]))

(enable-console-print!)

(defonce app-state (atom {:board (b/generate 3)}))

(defn- generate-cells [board move-ch]
  (map-indexed
    (fn [i value] (dom/li #js {:className "cell" :id (str "cell-" i)
                               :onClick (fn [e] (put! move-ch i))} value)) board))

(defn board-view [move-ch]
  (om/root
    (fn [data owner]
      (om/component
        (apply dom/ul #js {:id "board"}
          (generate-cells (:board data) move-ch))))
    app-state
    {:target (. js/document (getElementById "app"))}))

(deftype BrowserUI [move-ch]
  UI
  (draw-board [this board]
    (swap! app-state assoc :board board))

  (move [this board token]
    (go
      (let [move (<! move-ch)]
        (ui/draw-board this (b/fill-space (vec board) move token))))))

(defn new-browser-ui []
  (let [move-ch (chan)]
    (board-view move-ch)
    (BrowserUI. move-ch)))
