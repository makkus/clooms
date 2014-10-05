(ns clooms.db
  (:use korma.db
        korma.core)
  (:require [ clojure.string :as str ]
            [ clj-yaml.core :as yaml])
  (:gen-class))

;; Setup database, see http://sqlkorma.com/docs#start
(def db-spec (h2 {:db "~/src/clojure/clooms/clooms"}))

(defdb db db-spec)

(defentity lights)
  
(defentity bridges)

;; populate db:
;; (insert bridges (values (:bridges (yaml/parse-string (slurp "/home/markus/src/clojure/clooms/resources/fixtures.yml")))))
;; (insert lights (values (:lights (yaml/parse-string (slurp "/home/markus/src/clojure/clooms/resources/fixtures.yml")))))


