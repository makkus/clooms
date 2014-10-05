(ns lobos.migrations
  (:refer-clojure :exclude [alter drop
                            bigint boolean char double float time])
  (:use (lobos [migration :only [defmigration]] core schema config )))

(defmigration add-bridges
  (up [] (create
          (table :bridges
                 (integer :id :primary-key)
                 (varchar :name 100)
                 (varchar :hostname 100)
                 (integer :port)))))

(defmigration add-lights
  (up [] (create
          (table :lights
                 (integer :id :primary-key)
                 (varchar :name 100 )
                 (integer :group)
                 (integer :bridge-id))))
  (down [] (drop (table :lights))))
