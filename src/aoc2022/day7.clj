(ns aoc2022.day7)

(defn get-command [s] 
  (let [m (re-matches #"^\$ (.*)" s)]
    (if m
      (m 1))))

(defn parse-cd [s]
  (let [m (re-matches #"cd (.*)" s)]
    (if m
      (m 1))))

(defn parse-ls [s]
  (let [m (re-matches #"ls" s)]
    (if m
      s)))

(def empty-tree '())

(defn mk-node [x]
  {:x x
   :children '()})


(defn make-empty-state [] {:cwd '() :tree {} })
(defn insert-at-path [tree path subtree]
                      (assoc-in tree path subtree))
(defn cd [state arg]
  (cond 
    (= arg "/") {:cwd '() :tree (:tree state)}
    (= arg "..") ({:cwd (rest (:cwd state) :tree (:tree state))})
    :else { :cwd (conj (:cwd state) arg)  :tree (:tree state)}))
(defn insert-file [state file]
  (let [ ks (into [] (:cwd state))]
   {:cwd (:cwd state) :tree (assoc-in (:tree state) ks file)}))
;$ cd /
;$ ls
;dir a
;14848514 b.txt
;8504156 c.dat
;dir d
;$ cd a
;$ ls
;dir e
;29116 f
;2557 g
;62596 h.lst
;$ cd e
;$ ls
;584 i
;$ cd ..
;$ cd ..
;$ cd d
;$ ls
;4060174 j
;8033020 d.log
;5626152 d.ext
;7214296 k
