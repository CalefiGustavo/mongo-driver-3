(ns mongo-driver-3.ig
  (:require []
            [integrant.core :as ig]
            [pgo.log :as log]))

(defn- init-db [db-name uri]
 (let [{:keys [conn]} (mg/connect-via-uri uri)]
   (mg/get-db conn db-name)))

(defmethod ig/init-key ::client [_ {:keys [db-name db-coll uri] :as db-config}]
 (assoc db-config :db (init-db db-name uri)))

(defmethod ig/halt-key! ::client [_ {:keys [client]}]
  (log/info {:msg "Stopping mongo client"})
  (let [{:keys [mongo]} client]
    (when mongo
      (mg/disconnect mongo))))
