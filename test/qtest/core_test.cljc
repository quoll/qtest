(ns qtest.core-test
  (:require [clojure.test :refer [deftest is run-tests] :include-macros true]
            [qtest.core :refer [unord= with-fresh-gen] :include-macros true]))

(deftest gen-test
  (with-fresh-gen
    (is (= 'x1 (gensym "x")))
    (is (= 'x2 (gensym "x"))))

  (with-fresh-gen
    (is (= 'x1 (gensym "x")))
    (is (= 'x2 (gensym "x")))))

(deftest contents-test
  (is (unord= [1 2 3] [2 3 1])))

#?(:cljs (run-tests))
