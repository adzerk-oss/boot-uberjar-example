(ns main.entrypoint
  (:gen-class))

(defn -main [& args]
  (require 'my-namespace)
  (apply (resolve 'my-namespace/-main) args))
