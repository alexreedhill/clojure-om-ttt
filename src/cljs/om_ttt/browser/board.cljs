(ns om-ttt.browser.board
  (:require [cljs.core.async :refer [put!]]
            [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]))

(defn- generate-cells [board move-ch]
  (map-indexed
    (fn [i value] (dom/li #js {:className "cell" :id (str "cell-" i)
                               :onClick (fn [e] (put! move-ch i))} value)) board))

(defn board-view [data owner]
  (om/component
    (apply dom/ul #js {:id "board"}
      (generate-cells (:board data) (:move-ch data)))))
