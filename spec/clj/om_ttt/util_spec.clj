(ns om-ttt.util-spec
  (:require [om-ttt.util :refer :all]
            [speclj.core :refer :all]
            [om-ttt.spec-helper :refer :all]))

  (describe "util"
    (describe "compact"
      (it "removes nils from vectors"
        (should= ["X" "O"] (compact [nil "X" nil "O" nil])))))
