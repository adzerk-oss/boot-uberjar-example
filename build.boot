#!/usr/bin/env boot

(set-env!
 :resource-paths #{"src"}
 :dependencies '[[org.clojure/clojure "1.6.0"     :scope "provided"]])

(deftask build
  "Builds an uberjar of this project that can be run with java -jar"
  []
  (comp
   (javac)
   (pom :project 'myproject
        :version "1.0.0")
   (uber)
   (jar :main 'main.Main)
   (sift :include #{#"project.jar"})
   (target)))

(defn -main [& args]
  (require 'my-namespace)
  (apply (resolve 'my-namespace/-main) args))
