(defproject clooms "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[cider/cider-nrepl "0.8.0-20140920.152534-18"]
                 [org.clojure/clojure "1.6.0"]
                 [compojure "1.1.9"]
                 [ring/ring-json "0.3.1"]
                 [korma "0.4.0"]
                 [com.h2database/h2 "1.4.178"]
                 [lobos "1.0.0-beta3"]
                 [clj-yaml "0.3.1"]]
  :plugins [[lein-ring "0.8.12"]]
  :ring {:handler clooms.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}})
