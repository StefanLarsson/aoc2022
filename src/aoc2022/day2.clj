(ns aoc2022.day2
  (:gen-class))

(require '[clojure.string :as str])
(def test_data  [
  "A Y"
  "B X"
  "C Z"])


;A for Rock, B for Paper, and C for Scissors.
;  X for Rock, Y for Paper, and Z for Scissors. 
; The score for a single round is the score for the shape you selected (1 for Rock, 2 for Paper, and 3 for Scissors) plus the score for the outcome of the round (0 if you lost, 3 if the round was a draw, and 6 if you won).
(defn shape-score [match]
  (case (:ply match) 
    "X" 1
    "Y" 2
    "Z" 3))

(defn resolve-match [match]
  (case [(:opp match) (:ply match)]
    (["A" "X"] ["B" "Y"] ["C" "Z"]) :draw
    (["A" "Z"] ["B" "X"] ["C" "Y"]) :loss
    (["A" "Y"] ["B" "Z"] ["C" "X"]) :win))

(defn result-score [result]
  (case result
    :loss 0
    :draw 3
    :win 6))

(defn make-match [s]
  (let [[opp ply] (str/split s #" ")]
    {:opp opp
     :ply ply}))

(defn score-match [match] 
  (+ (shape-score match) (result-score (resolve-match match))))
;"Anyway, the second column says how the round needs to end: X means you need to lose, Y means you need to end the round in a draw, and Z means you need to win. Good luck!"

(defn wanted-result [code]
  (case code
    "X" :loss
    "Y" :draw
    "Z" :win))
(defn needed-win [opp]
  (case opp
    "A" "Y"
    "B" "Z"
    "C" "X"))
(defn needed-draw [opp]
  (case opp
    "A" "X"
    "B" "Y"
    "C" "Z"))
(defn needed-loss [opp]
  (case opp
    "A" "Z"
    "B" "X"
    "C" "Y"))
(defn get-ply-move [code-match]
  (let [res (wanted-result (:ply code-match))
        needed-fcn (case res
                     :win needed-win
                     :draw needed-draw
                     :loss needed-loss)]
        (needed-fcn (:opp code-match))))

(defn make-actual-match-from-code-match [code-match]
  {:opp (:opp code-match) :ply (get-ply-move code-match)})

