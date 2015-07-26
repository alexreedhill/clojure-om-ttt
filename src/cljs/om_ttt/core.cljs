(ns om-ttt.core
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]))

(enable-console-print!)

(defonce app-state (atom {:board [nil nil nil nil nil nil nil nil nil]}))

(defn draw-board []
  (om/root
    (fn [data owner]
      (om/component
        (apply dom/ul #js {:id "board"}
          (map (fn [cell] (dom/li #js {:className "cell"} cell)) (:board data)))))
    app-state
    {:target (. js/document (getElementById "app"))}))
