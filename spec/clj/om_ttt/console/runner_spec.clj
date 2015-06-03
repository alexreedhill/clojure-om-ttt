(ns om-ttt.console.runner-spec
  (:require [om-ttt.console.menu :as menu]
            [om-ttt.console.runner :refer :all]
            [om-ttt.game-loop :as game-loop]
            [om-ttt.players.human :refer [new-human-player]]
            [om-ttt.spec-helper :refer [mock-ui mock-config empty-board]]
            [speclj.core :refer :all]))

(describe "console runner"
  (it "gets game config"
    (should-invoke menu/get-game-config {:return mock-config} (run mock-ui)))

  (it "starts game loop"
    (should-invoke game-loop/start-game {:return empty-board} (run mock-ui))))
