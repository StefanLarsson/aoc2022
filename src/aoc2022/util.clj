(ns aoc2022.util
  (:gen-class))

(require '[clojure.string :as str])
(def filename-to-lines #(clojure.string/split-lines (slurp %)))
