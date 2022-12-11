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

(defn all-different? [coll]
  (loop [checked #{}
         coll coll]
    (if (empty? coll) true
      (let [x (first coll)]
        (if (contains? checked x) false
          (recur (conj checked x) (rest coll)))))))

(defn first-n-different-position [n s]
  (if (< (count s) n)
    nil
    (loop [i n
           first-n-queue (into clojure.lang.PersistentQueue/EMPTY (take n s))

           ])))
