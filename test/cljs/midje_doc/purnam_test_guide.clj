(ns midje-doc.purnam-native-guide)

[[:chapter {:title "Introduction"}]]

"
`purnam.test` was written to allow clojurescript tests to work with the karma test runner. There are various advantages to using the [karma](http://karma-runner.github.io/) test runner over existing clojurescript solutions:
  - Supports a variety of browsers including Chrome, Firefox, Safari, Phantomjs and [Node Webkit](https://github.com/intelligentgolf/karma-nodewebkit-launcher)
  - Uses `nodejs` and so has a very quick startup time
  - It is supported by [Google](http://google.com) and the [Angularjs](http://angularjs.org) team
  - Very active community with lots of users

Furthermore, the javascript `dot.notational.style` to be used for better native integration. Since version `0.4`, there is much better support for error reporting and the library can now be used on its own, outside of all other purnam libraries. Currently two flavors are supported describing clojurescript tests:
  - **Jasmine style** (`describe`, `it` and `is`). It gives a more fully featured interface and has async support (`runs`, `waits-for`)
  - **Midje style**  (`fact` and *=>*). It can be combined with [MidjeDoc](https://www.github.com/zcaudate/lein-midje-doc) to generate documentation using unit tests (the current article being one example of a auto-generated document).
"

[[:chapter {:title "Installation"}]]

"To use just the test functionality, add to `project.clj` dependencies:

  `[im.chit/purnam.test` \"`{{PROJECT.version}}`\"`]` 

The library is also included when `[im.chit/purnam` \"`{{PROJECT.version}}`\"`]` is used."

[[:chapter {:title "Usage"}]]

"To run tests, the [karma](http://karma-runner.github.io/) test library is required. It can be installed using [npm](https://www.npmjs.org). There are two files that needs to be updated:
   - `/project.clj`
   - `/karma.conf.js`

In `project.clj`, add your clojurescript builds. Usually the test code is built to a seperate file than the application code. In the following case, the tests are compiled to `target/example-test.js`."

[[{:title "project.clj"}]]
(comment
  :cljsbuild {:builds [{:source-paths ["src"]
                        :compiler {:output-to "target/example.js"
                                   :optimizations :whitespace
                                   :pretty-print true}}
                       {:source-paths ["src", "test/cljs"]
                        :compiler {:output-to "target/example-test.js"
                                   :optimizations :whitespace
                                   :pretty-print true}}]})

"A `karma.conf.js` file is required at the root level of your project. It is created by running `karma init` in the root project directory and following a bunch of instructions:

![karma-setup](https://raw.github.com/purnam/purnam.test/master/karma-setup.png)]

Make sure that `autoWatch` is set to `true` and that the the compiled output from cljsbuild is included as one of the files."

[[{:lang "js" :title "karma.conf.js" :tag "c-py-1"}]]
[[:code 
  "....
  
  files: [
    'target/example-test.js'
  ],
  
  autoWatch: true,
  
  ...."]]

"
Run in one window: `lein cljsbuild auto`.

Run in another window: `karma start`.

Note that you will have to restart karma if the *.js file was not found before the test runner starts.

[![karma-testing](https://raw.github.com/purnam/example.purnam.test/master/karma-testing.png)](http://www.youtube.com/watch?v=mhBqjJUYY6w)
"

[[:chapter {:title "Generating Documentation"}]]


[[:file {:src "test/cljs/midje_doc/api_purnam_test_jasmine.cljs"}]]
    
[[:file {:src "test/cljs/midje_doc/api_purnam_test_sweet.cljs"}]]
