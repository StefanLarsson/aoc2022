(ns aoc2022.core
  (:gen-class))

(def filename-to-lines #(clojure.string/split-lines (slurp %)))

(defn count-increases [xs]
  (if (not (seq xs))
     0
     (let 
       [fval (first xs)
        mvals (rest xs)
        ]
       (if (not (seq mvals))
         0
         (let
           [sval (first mvals)
            tjoho (if (> sval fval) 1 0)]
           (+ tjoho (count-increases mvals)))))))

(defn count-running-increases [xs n]
  (let [
        first-n (take n xs)
        n-needed (inc n)
        more-xs (drop n xs)]
    (if (empty? more-xs)
      0
      (let [
            old (apply + first-n)
            new (apply + (cons (first more-xs) (rest first-n)))
            ]
        (if (> new old)
          (+ 1 (count-running-increases (rest xs) n))
          (count-running-increases (rest xs) n))))))


(defn aoc2021-1 []
  (let 
    [numbers (map read-string (filename-to-lines "data/legacy/2021/day1.txt"))]
    (list (count-increases numbers) (count-running-increases numbers 3))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
