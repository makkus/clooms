(ns clooms.handler
  (:use ring.util.response)
  (:import java.net.DatagramSocket)
  (:import java.net.DatagramPacket)
  (:import java.net.InetAddress)
  (:require [compojure.core :refer :all]
            [ring.middleware.json :as middleware]
            [compojure.handler :as handler]
            [clooms.lights :as lights]
            [compojure.route :as route]))

(def socket (DatagramSocket.))

(defn create-packet [command-bytes hostname port] (new DatagramPacket command-bytes (alength command-bytes) (InetAddress/getByName hostname) port))

(defn send-command [bridge group command]
  (let [upd-packets (lights/create-bytes-white-v2 group command)]
    (doseq [packet upd-packets] (.send socket (create-packet packet (:ip bridge) (:port bridge))))))


(defn list-all-lights
  []
  (response "xxx"))

(defroutes app-routes
  (GET "/lights" [] (list-all-lights))
  (route/not-found "Not Found"))

(def app
  (-> (handler/api app-routes)
      (middleware/wrap-json-body)
      (middleware/wrap-json-response))
  )