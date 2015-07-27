(ns om-ttt.util-spec
  (:require [om-ttt.util :as util]
   #?(:clj  [speclj.core :refer [describe it should=]]
      :cljs [speclj.core :refer-macros [describe it should=]])))

(describe "util"
  (describe "compact"
    (it "removes nils from vectors"
      (should= ["X" "O"] (util/compact [nil "X" nil "O" nil]))))

  (describe "transpose"
    (it "transposes a matrix"
      (should= [["a" "d" "g"]
                ["b" "e" "h"]
                ["c" "f" "i"]] (util/transpose [["a" "b" "c"]
                                                ["d" "e" "f"]
                                                ["g" "h" "i"]])))))
