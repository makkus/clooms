(ns clooms.db
  (:use korma.db
        korma.core)
  (:require [ clojure.string :as str ]
            [ clj-yaml.core :as yaml])
  (:gen-class))

;; Setup database, see http://sqlkorma.com/docs#start
(def db-spec (h2 {:db "~/src/clojure/clooms/clooms"}))

(defdb db db-spec)

(defrecord Bridge [id name hostname port])
(defrecord Light [id name group bridge-id])

(defentity bridges
  (entity-fields :id :name :hostname :port)
  (transform #(Bridge. (:id %) (:name %) (:hostname %) (:port %)))
  )
 
(defentity lights
  (entity-fields :id :name :group :bridge-id)
  (belongs-to bridges {:fk :bridge-id})
  (transform #(Light. (:id %) (:name %) (keyword  (str "group_" (:group %))) (:bridge-id %)))
;  (transform #(Light. (:id %) (:name %) (:group (keyword (str "group_" (:group %)))) (:bridge-id %)))
  )
  

;; populate db:
;; (insert bridges (values (:bridges (yaml/parse-string (slurp "/home/markus/src/clojure/clooms/resources/fixtures.yml")))))
;; (insert lights (values (:lights (yaml/parse-string (slurp "/home/markus/src/clojure/clooms/resources/fixtures.yml")))))




