(ns purnam.test.init)

(defn init-fn []
  '(js/beforeEach
     (fn []
       (.addMatchers (js* "this")
         (let [o (js-obj)]
           (aset o "toSatisfy"
             (fn [expected tactual texpected]
               (let [actual (.-actual (js* "this"))
                     actualText (str actual)
                     actualText (if (= actualText "[object Object]")
                                   (let [ks (js/goog.object.getKeys actual)
                                         vs (js/goog.object.getValues actual)]
                                     (into {} (map (fn [x y] [x y])
                                                 ks vs)))
                                   actualText)
                     notText (if (.-isNot (js* "this")) "Not " "")]
                 (aset (js* "this") "message"
                       (fn []
                         (str "Expression: " tactual
                              "\n  Expected: " notText texpected
                              "\n  Actual: " actualText)))
                 (cond (= (js/goog.typeOf expected) "array")
                       (purnam.test.common/js-equals expected actual)

                       (fn? expected)
                       (expected actual)

                       :else
                       (or (= expected actual)
                           (purnam.test.common/js-equals expected actual))))))
            o)))))
