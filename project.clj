(defproject im.chit/purnam.test "0.4.0"
  :description "Common utility functions for purnam"  
  :url "http://www.github.com/purnam/purnam.test"
  :license {:name "The MIT License"
            :url "http://opensource.org/licencses/MIT"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [im.chit/purnam.common "0.4.0"]]
  :test-paths ["test/clj"]
  :profiles {:dev {:dependencies [[org.clojure/clojurescript "0.0-2138"]
                                  [midje "1.6.0"]]
                   :plugins [[lein-cljsbuild "1.0.0"]
                             [lein-midje "3.1.3"]]}}
  :cljsbuild {:builds [{:source-paths ["src", "test/cljs"]
                       :compiler {:output-to "test/purnam.test.js"
                                  :optimizations :whitespace
                                  :pretty-print true}}]})
