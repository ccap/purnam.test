(ns purnam.test
  (:require [purnam.common :refer :all]
            [purnam.test.init :refer [init-fn]]
            [purnam.test.midje :refer [fact-fn]]))

(defmacro init [] (init-fn))

(defmacro fact [opts? & body]
  (fact-fn opts? body))

(defmacro facts [opts? & body]
  (fact-fn opts? body))
