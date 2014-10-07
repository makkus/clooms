(ns clooms.handler
  (:use ring.util.response)
  (:import java.net.DatagramSocket)
  (:import java.net.DatagramPacket)
  (:import java.net.InetAddress)
  (:require [compojure.core :refer :all]
            [ring.middleware.json :as middleware]
            [compojure.handler :as handler]
            [clooms.lights :as lights]
            [clooms.state :as state]
            [compojure.route :as route]))

(def socket (DatagramSocket.))

(defn create-packet [command-bytes hostname port] (new DatagramPacket command-bytes (alength command-bytes) (InetAddress/getByName hostname) port))

(defn send-command [bridge group command]
  (let [upd-packets (lights/create-bytes-white-v2 group command)]
    (doseq [packet upd-packets] (.send socket (create-packet packet (:hostname bridge) (:port bridge))))))


(defroutes light-routes
  (context "/light" []
           (GET "/" [] (response (state/get-all-lights-with-current-state)))
           (GET "/:light-name" [light-name] (response (state/get-light light-name)))
           (POST "/:light-name/:action" [light-name action] (response (state/do-action-on-light light-name action)))
           (POST "/:light-name/:action/:param" [light-name action param] (response (state/do-action-on-light light-name action param)))
           ))

(defroutes app-routes
  (route/not-found "Not Found"))

(def app
  (-> (handler/api (routes light-routes app-routes))
      (middleware/wrap-json-body)
      (middleware/wrap-json-response))
  )
