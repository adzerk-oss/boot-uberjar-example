#!/usr/bin/env boot

(set-env!
 :resource-paths #{"src"}
 :dependencies '[[org.clojure/clojure "1.6.0"     :scope "provided"]
                 [boot/core           "2.0.0-rc2" :scope "provided"]])

(deftask build
  "Builds an uberjar of this project that can be run with java -jar"
  []
  (comp
   (aot :namespace '#{my-namespace})
   (pom :project 'myproject
        :version "1.0.0")
   (uber)
   (jar :main 'my_namespace)))

(defn -main [& args]
  (require 'my-namespace)
  (apply (resolve 'my-namespace/-main) args))
