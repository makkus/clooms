(ns clooms.lightcontrol
  (:import java.net.DatagramSocket)
  (:import java.net.DatagramPacket)
  (:import java.net.InetAddress)
  (:require [clooms.lights :as lights])
  )

(def socket (DatagramSocket.))

(defn create-packet [command-bytes hostname port] (new DatagramPacket command-bytes (alength command-bytes) (InetAddress/getByName hostname) port))

(defn send-command [bridge group command]
  (let [upd-packets (lights/create-bytes-white-v2 group command)]
    (doseq
        [packet upd-packets]
      (.send socket (create-packet packet (:hostname bridge) (:port bridge)))
      )))

