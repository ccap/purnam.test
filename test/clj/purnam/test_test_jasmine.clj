(ns purnam.test-test-jasmine
  (:use midje.sweet)
  (:require [purnam.test.jasmine :as j]))

(fact "it-fn"
  (j/it-fn "<DESC>" '[<BODY>])
  => '(js/it "<DESC>" (clojure.core/fn [] <BODY>)))
