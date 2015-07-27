(ns om-ttt.browser.ui
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [om-ttt.game.board :as board]
            [om-ttt.protocols.ui :refer [UI]]))

(enable-console-print!)

(defonce app-state (atom {:board (board/generate 3)}))

(defn- generate-cells [board]
  (map-indexed
    (fn [i value] (dom/li #js {:className "cell" :id (str "cell-" i)} value)) board))

(defn draw-board []
  (om/root
    (fn [data owner]
      (om/component
        (apply dom/ul #js {:id "board"}
          (generate-cells (:board data)))))
    app-state
    {:target (. js/document (getElementById "app"))}))

(deftype BrowserUI []
  UI
  (draw-board [this board]
    (swap! app-state assoc :board board)))

(defn new-browser-ui []
  (draw-board)
  (BrowserUI.))
