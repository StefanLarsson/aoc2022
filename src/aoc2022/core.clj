(ns aoc2022.core
  (:gen-class))

(require '[clojure.string :as str])

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

(defn apply-command [pos cmd]
  (let [[dir valstring] (str/split cmd #" ")
        val (read-string valstring)]
    (println dir val)
    (case dir
      "forward" {:depth (:depth pos) :horiz (+ val (:horiz pos))}
      "up" {:depth (- (:depth pos) val) :horiz (:horiz pos)}
      "down" {:depth (+ (:depth pos) val) :horiz (:horiz pos)})
    ))

(def initial-pos {:depth 0 :horiz 0})

(defn aoc2021-2 []
  (let
    [commands (filename-to-lines "data/legacy/2021/day2.txt")]
    (reduce apply-command initial-pos commands)))



(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
