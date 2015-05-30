(ns om-ttt.console.validations)

(defn validate-boolean [input]
  (condp = input
    "y" true
    "n" false
    :invalid))

(defn validate-board-size [input]
  (condp = input
    "3" 3
    "4" 4
    :invalid))

(defn validate-move [input board]
  (try
    (let [one-based-input (Integer/parseInt input)]
      (if (> one-based-input (count board))
        :invalid
        (- one-based-input 1)))
    (catch NumberFormatException e
      :invalid)))

(defn validate-token [input]
  (if (= (count input) 1)
    input
    :invalid))

(defn validate-first-player [input]
  (condp = input
    "human" "human"
    "ai" "ai"
    :invalid))
