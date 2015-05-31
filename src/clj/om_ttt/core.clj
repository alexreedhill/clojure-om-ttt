(ns om-ttt.core
  (:require [om-ttt.console.runner :refer [run]]
            [om-ttt.console.ui :refer [new-console-ui]]))

(defn -main []
  (run (new-console-ui)))
