(defproject mongo-driver-3 "0.6.1-SNAPSHOT"
  :description "A Clojure wrapper for the Java MongoDB driver 3.11/4.0+."
  :url "https://github.com/gnarroway/mongo-driver-3"
  :license {:name         "The MIT License"
            :url          "http://opensource.org/licenses/mit-license.php"
            :distribution :repo}
  :deploy-repositories [["clojars" {:url           "https://clojars.org/repo"
                                    :username      :env/clojars_user
                                    :password      :env/clojars_pass
                                    :sign-releases false}]]
  :dependencies [[br.com.paygo/pgo-log "0.0.21"]
                 [integrant "0.8.0"]
                 [org.clojure/clojure "1.10.1"]
                 [org.mongodb/mongodb-driver-sync "4.4.0"]]
  :plugins [[lein-cljfmt "0.6.4"]
            [s3-wagon-private "1.3.4"]]
  :repositories [["paygo-snapshot" {:url        "s3p://paygo-mvn/snapshot" }]
                 ["paygo-release" {:url        "s3p://paygo-mvn/release"}]]
  #_:profiles #_{:dev {:dependencies []}})
