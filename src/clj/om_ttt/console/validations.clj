(ns om-ttt.console.validations)

(defn validate-boolean [input & _]
  (condp = input
    "y" true
    "n" false
    :invalid))

(defn validate-board-size [input & _]
  (condp = input
    "3" 3
    "4" 4
    :invalid))

(defn validate-move [input [board]]
  (try
    (let [one-based-input (Integer/parseInt input)
          zero-based-input (- one-based-input 1)]
      (if (or (< one-based-input 1) (> one-based-input (count board)) (not (nil? (board zero-based-input))))
        :invalid
        zero-based-input))
    (catch NumberFormatException e
      :invalid)))

(defn validate-token [input & _]
  (if (= (count input) 1)
    input
    :invalid))

(defn validate-first-player [input & _]
  (condp = input
    "human" "human"
    "ai" "ai"
    :invalid))
