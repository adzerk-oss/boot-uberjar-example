#!/usr/bin/env boot

(set-env!
 :resource-paths #{"src"}
 :dependencies `[[org.clojure/clojure ~(clojure-version) :scope "provided"]])

(deftask build
  "Builds an uberjar of this project that can be run with java -jar"
  []
  (comp
   (aot :namespace #{'main.entrypoint})
   (uber)
   (jar :file "project.jar" :main 'main.entrypoint)
   (sift :include #{#"project.jar"})
   (target)))

(defn -main [& args]
  (require 'my-namespace)
  (apply (resolve 'my-namespace/-main) args))
