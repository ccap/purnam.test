(ns purnam.test.midje
  (:require [purnam.test.jasmine :refer [describe-fn it-fn is-fn]]))

(defn find-arrow-positions
  ([forms] (find-arrow-positions forms [] 0))
  ([[f & more] idxs count]
     (if f
       (recur more (if (= f '=>) (conj idxs count) idxs) (inc count))
       idxs)))

(defn fact-groups [forms]
  (let [forms (vec forms)
        idxs  (set (find-arrow-positions forms))
        len   (count forms)]
    (->> (for [i (range len)]
          (cond (or (idxs (dec i)) (idxs (inc i)))
                ::nil

                (and (idxs i) (>= (dec i) 0))
                [::is (nth forms (dec i)) (nth forms (inc i))]

                :else
                [::norm (nth forms i)]))
         (filter #(not= ::nil %))
         (vec))))

(defn fact-render [[type f1 f2]]
  (condp = type
    ::is (is-fn f1 f2)
    ::norm f1))

(defn double-vec-map? [ele]
  (and (vector? ele)
       (vector? (first ele))
       (instance? clojure.lang.APersistentMap (ffirst ele))))

(defn fact-fn [opts? body]
  (let [[opts? body]
        (cond (= '=> (first body))
              [{} (cons opts? body)]

              (string? opts?)
              [{:doc opts?} body]

              (double-vec-map? opts?)
              [(ffirst opts?) body]

              :else [{} (cons opts? body)])
        fgrps (fact-groups body)]
    (describe-fn opts?
                 [(it-fn ""
                         (map fact-render fgrps))])))
