(ns clojure-om-ttt.util-spec
  (:require [clojure-om-ttt.util :refer :all]
            [speclj.core :refer :all]
            [clojure-om-ttt.spec-helper :refer :all]))

  (describe "util"
    (describe "compact"
      (it "removes nils from vectors"
        (should= ["X" "O"] (compact [nil "X" nil "O" nil])))))
