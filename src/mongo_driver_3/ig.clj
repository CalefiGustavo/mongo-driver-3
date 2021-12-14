(ns mongo-driver-3.ig
  (:require [mongo-driver-3.client :as client]
            [integrant.core :as ig]
            [pgo.log :as log]))


(defn- init-db 
  [db-name uri]
  (let [client (client/create uri)]
    {:client client :db (client/get-db client db-name)}))

(defn init-client [{:keys [db-name uri] :as db-config}]
  (into db-config (init-db db-name uri)))

(defn halt-client [{:keys [client]}]
  (log/info {:msg "Stopping mongo client"})
  (let [{:keys [mongo]} client]
    (when mongo
      (client/close mongo))))

(defmethod ig/init-key ::client [_ db-config]
 (init-client db-config))

(defmethod ig/halt-key! ::client [_ db-config]
  (halt-client db-config))
