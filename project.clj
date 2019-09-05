(defproject org.clojars.quoll/qtest "0.1.1"
  :description "Testing utility functions"
  :url "https://github.com/quoll/qtest"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/clojurescript "1.10.520"]]
  :repl-options {:init-ns qtest.core}
  :plugins [[lein-cljsbuild "1.1.7"]]
  :cljsbuild {
    :builds {
      :dev
      {:source-paths ["src"]
       :compiler {
         :output-to "out/qtest/core.js"
         :optimizations :simple
         :pretty-print true}}
      :test
      {:source-paths ["src" "test"]
       :compiler {
         :output-to "out/qtest/test.js"
         :optimizations :simple
         :pretty-print true}}
      }
    :test-commands {
      "unit" ["node" "out/qtest/test.js"]}
    })
