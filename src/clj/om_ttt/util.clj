(ns om-ttt.util)

  (defn compact [coll]
    (filter #(not (nil? %)) coll))
