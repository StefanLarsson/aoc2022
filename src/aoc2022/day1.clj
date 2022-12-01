(ns aoc2022.day1
  (:gen-class))

(def test_data ["1000" "2000" "3000" "" "4000" "" "5000" "6000" "" "7000" "8000" "9000" "" "10000"])


(defn list-strings-to-sum [strlist]
  (let [numlist (map read-string strlist)]
    (apply + numlist)))

(defn max-sum-non-blank-groups [lines]
  (let [ xs (partition-by clojure.string/blank? lines)
         ys (filter (complement #(clojure.string/blank? (first %))) xs)
         zs (map list-strings-to-sum ys)]
    
  (apply max zs)))

(defn largest-three [[l1 l2 l3 :as acc] cand]
  (if (> cand l1) [cand l1 l2]
    (if (> cand l2 ) [l1 cand l2]
      (if (> cand l3 ) [l1 l2 cand]
          acc))))
  
