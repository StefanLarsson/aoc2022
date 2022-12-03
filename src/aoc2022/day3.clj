(ns aoc2022.day3
  (:gen-class))

(require '[clojure.string :as str])

(def test_data 
  ["vJrwpWtwJgWrhcsFMMfFFhFp"
  "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL"
  "PmmdzqPrVvPwwTWBwg"
  "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn"
  "ttgJtRGJQctTZtZT"
  "CrZsJsPPZsGzwwsLwLmpwMDw"])


(defn priority [c]
  (let [ic (int c)]
    (if (> ic (int \Z))
      (inc (- ic (int \a)))
      (+ 27 (- ic (int \A))))))

(defn find-common [s]
  (let
    [halfl (/ (count s) 2)
     first-set (set (subs s 0 halfl))]
    (loop [i 0]
      (if (> i halfl) nil
        (if (contains? first-set (nth s (+ i halfl)))
          (nth s (+ i halfl))
          (recur (inc i)))))))
        
