#!/usr/bin/env boot
(set-env!
 :source-paths #{"src"}
 :resource-paths #{"resources"}
 :dependencies '[[org.clojure/clojure "1.8.0"]
                 [org.clojure/data.json "0.2.6"]
                 [http-kit "2.1.19"]
                 [ring-cors "0.1.8"]
                 [compojure "1.5.0"]
                 [midje "1.8.3"]])

;; (deftask api []
;;   (comp (serve :port 8000 :handler 'sim.core/handler :reload true)))

(task-options!
 pom {:project 'sim
       :version "0.1.0"}
 aot {:namespace '#{sim.core}}
 jar {:main 'sim.core
       :manifest {"Description" "Sim"}})

(deftask build
  "Build my project."
  []
  (comp
   (aot)
   (pom)
   (uber)
   (jar)
   (target)))
