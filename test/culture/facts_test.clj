(ns culture.facts-test
  (:require [clojure.edn :as edn]
            [clojure.string :as str]
            [clojure.test :refer [deftest is]]
            [culture.facts :as facts]))

(deftest bdi-has-culture-basis
  (let [sb (facts/spec-basis "BDI")]
    (is (= 6 (count sb)))
    (is (= (count sb) (count (set (map :culture/id sb)))))
    (is (every? #(str/starts-with? (:culture/url %) "https://") sb))
    (is (every? #(= "BDI" (:culture/country %)) sb))
    (is (every? #(nil? (:culture/municipality %)) sb))
    (is (every? #(seq (:culture/summary %)) sb))
    (is (every? #(string? (:culture/retrieved-at %)) sb))))

(deftest unknown-jurisdiction-has-no-basis
  (is (nil? (facts/spec-basis "RWA")))
  (is (nil? (facts/spec-basis "zzz"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["BDI" "RWA"])]
    (is (= 2 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["RWA"] (:missing-jurisdictions c)))))

(deftest by-kind-filters
  (is (= 3 (count (facts/by-kind "BDI" :dish))))
  (is (= ["bdi.craft.karyenda"]
         (mapv :culture/id (facts/by-kind "BDI" :craft))))
  (is (empty? (facts/by-kind "BDI" :other)))
  (is (empty? (facts/by-kind "RWA" :dish))))

(deftest tx-file-matches-catalog
  (let [tx (edn/read-string (slurp "data/culture-tx.edn"))
        flat (mapcat val (sort-by key facts/catalog))]
    (is (= (vec flat) (vec tx)))))
