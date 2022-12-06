(ns aoc2022.day5)

(def test_data [
"    [D]"   
"[N] [C]"
"[Z] [M] [P]"
" 1   2   3 "
""
"move 1 from 2 to 1"
"move 3 from 1 to 3"
"move 2 from 2 to 1"
"move 1 from 1 to 2"])

(defn type1line [s]
  (loop [acc []
         ss s]
    (if (< (count ss) 3)
      acc
      (let [[a b c] (take 3 ss)
            sss (drop 4 ss)]

    (do
      ;(println b)
      ;(println sss)
      ;(println acc)
      (recur (conj acc b) sss))))))

; state is a vector of lists
; line is a list of chars
; spaces to be considered empty
(defn insert-line [line state]
  (loop [line line
         state state
         i 0]
    (if (empty? line)
      state
      (let [c (first line)
            r (rest line)]
        ))))

; move a crate from from to to
(defn move [[ from to]]
  [(rest from) (cons (first from) to)])

; move n crates from from to to
(defn move-crates [n [from to]]
  (loop [
         n n
         [from to] [from to]]
    (if (= 0 n)
      [from to]
      (recur (dec n) (move [from to])))))

(def initial_test_state ["NZ" "DCM" "P"])

(defn move-crates-state [n from to state]
  (do (println n from to state))
  (let [
        fromidx (dec from)
        toidx (dec to)
        fromcol (get state fromidx)
        tocol (get state toidx)
        [resfromcol restocol] (move-crates n [fromcol tocol])]
    (assoc (assoc state fromidx resfromcol) toidx restocol)))


(defn type3line [s]
  (let [[_ n from to] (re-matches #"move (\d+) from (\d+) to (\d+)" s)]
    [(read-string n) [(read-string from) (read-string  to)]]))

(defn add-letter [l ls]
  (if ls
    (cons l ls)
    (list l) ))

(defn initial-state-apply-line [letters s]
  (loop [letters letters
         i 0
         acc s]
    (if (empty? letters)
      acc
      (let [l (first letters)
            ls (rest letters)]
        (if (= \space l)
          (recur ls (inc i) acc)
          (recur ls (inc i) (assoc acc i (add-letter l (get acc i)))))))))

(defn build-initial-state [lines]
  (loop [lines lines
         acc [nil nil nil nil nil nil nil nil nil]]
    (if (empty? lines)
      (into [] (map reverse acc))
      ;(map identity acc)
      (recur (rest lines) (initial-state-apply-line (first lines) acc)))))

(defn proceed-moves [lines state]
  (loop [lines lines
         acc state]
    (if (empty? lines) 
      acc
      (let [[n [from to]] (type3line (first lines))]
        (recur (rest lines) (move-crates-state n from to acc))))))

(defn day5-1 [all-lines]
  (let [initial-state-lines (take-while #(not (empty? %)) all-lines)
        move-lines (drop 1 (drop-while #(not (empty? %)) all-lines ))
        initial-state-lines-parsed (map type1line initial-state-lines)
        initial-state (build-initial-state (drop-last initial-state-lines-parsed))]
    (do
      ;(println initial-state-lines)
      ;(println initial-state-lines-parsed)
      (println initial-state)
      (println move-lines))
    (proceed-moves move-lines initial-state)))
                         
