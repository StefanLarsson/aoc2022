(ns aoc2022.day6)

(defn valid-marker? [[a b c d]]
  (not (or
         (= a b)
         (= a c)
         (= a d)
         (= b c)
         (= b d)
         (= c d))))

;return the first valid marker position if a valid marker exists
; nil otherwise
(defn first-valid-marker-position [s]
  (if (< (count s) 4)
    nil
    (loop [i 4
           [a b c d] (take 4 s)
           s (drop 4 s)]
      (if (valid-marker? [a b c d])
        i
        (recur (inc i) [b c d (first s)] (rest s))))))

(defn day6-1 []
  (first-valid-marker-position (slurp "data/day6.txt")))
