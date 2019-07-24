(ns qtest.core-test
  (:require [clojure.test :refer :all]
            [qtest.core :refer :all]))

(deftest gen-test
  (with-fresh-gen
    (is (= 'x1 (gensym "x")))
    (is (= 'x2 (gensym "x"))))

  (with-fresh-gen
    (is (= 'x1 (gensym "x")))
    (is (= 'x2 (gensym "x")))))

(deftest contents-test
  (is (unord= [1 2 3] [2 3 1])))

