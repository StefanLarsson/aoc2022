(ns aoc2022.day4)


(def test_data
  ["2-4,6-8"
  "2-3,4-5"
  "5-7,7-9"
  "2-8,3-7"
  "6-6,4-6"
  "2-6,4-8"])

(def split_re #"(\d+)-(\d+),(\d+)-(\d+)" )

(defn parse_line [s] (map read-string (rest (re-matches split_re s))))

(defn one-in-other [vals] 
  (let [[min1 max1 min2 max2] vals]
    (or
      (and (<= min1 min2)
           (>= max1 max2))
      (and (<= min2 min1)
           (>= max2 max1)))))


(defn any-overlap [vals]
  (let [[min1 max1 min2 max2] vals
        maxmin (max min1 min2)
        minmax (min max1 max2)]
    (<=
      maxmin
      minmax)))
