(ns om-ttt.util-spec
  (:require [om-ttt.util :refer :all]
            [speclj.core :refer :all]))

(describe "util"
  (describe "compact"
    (it "removes nils from vectors"
      (should= ["X" "O"] (compact [nil "X" nil "O" nil]))))

  (describe "transpose"
    (it "transposes a matrix"
      (should= [["a" "d" "g"]
                ["b" "e" "h"]
                ["c" "f" "i"]] (transpose [["a" "b" "c"]
                                           ["d" "e" "f"]
                                           ["g" "h" "i"]])))))
