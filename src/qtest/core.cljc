(ns qtest.core
  #?(:clj
     (:import
      [java.util.concurrent.atomic AtomicInteger])))

#?(:clj
   (defn fresh-gen-wrapper [thunk]
     (let [id-field (.getDeclaredField clojure.lang.RT "id")
           _ (.setAccessible id-field true)
           id (.get id-field AtomicInteger)
           saved (.getAndSet id 1)
           result (thunk)]
       (.getAndSet id saved)
       result))

   :cljs
   (defn fresh-gen-wrapper [thunk]
     ;; sets up the counter if gensym has not been used before
     (when (nil? gensym_counter) (set! gensym_counter (atom 0)))
     (let [saved (loop [old-val @gensym_counter]
                   (if (compare-and-set! gensym_counter old-val 0)
                     old-val
                     (recur @gensym_counter)))
           result (thunk)]
       (reset! gensym_counter saved)
       result)))

(defmacro with-fresh-gen [& body]
  `(fresh-gen-wrapper (fn [] ~@body)))

(defn unord=
  "Compares the contents of 2 sequences, with ordering not considered."
  [a b]
  (= (set a) (set b)))

