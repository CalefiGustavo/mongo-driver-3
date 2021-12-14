(ns mongo-driver-3.ig-test
  (:require [clojure.test :refer :all]
            [mongo-driver-3.client :as mg]
            [mongo-driver-3.ig :as mongo-ig]
            [integrant.core :as ig])
  (:import (com.mongodb.client MongoClient MongoDatabase)))


(def mongo-database {:db-name "my-db"
                     :uri     "mongodb://localhost:27017"})

(defprotocol CloseableWithObject
  (close [this])
  (get-client [this keyword]))

(defrecord IMongo [components]
  CloseableWithObject
  (close [_] (ig/halt! components))
  (get-client [_ keyword] (get components keyword)))

(defn prep-config [conf]
  (->IMongo (->> conf ig/prep ig/init)))

(defn start! []
  (prep-config {::mongo-ig/client mongo-database}))

(deftest ^:integration test-connect-to-db
  (with-open [ig-mongo (start!)]
    (let [{:keys [db-name uri client db]} (get-client ig-mongo :mongo-driver-3.ig/client)]
      (is (instance? MongoClient client))
      (is (instance? MongoDatabase db))
      (is (= {:db-name db-name :uri uri} mongo-database)))))
