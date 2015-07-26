(ns om-ttt.util)

(defn compact [coll]
  (filter #(not (nil? %)) coll))

(defn transpose [m]
  (apply mapv vector m))
