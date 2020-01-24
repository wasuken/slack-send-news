(ns slack-send-news.util
  (:require [aero.core :refer [read-config]]
            [clj-http.client :as client]
            [clojure.data.json :as json]))

(def config (read-config (clojure.java.io/resource "config.edn")))

(defn create-post-format [text]
  (json/write-str (merge (:template config) {:text text})))

(defn basic-post [text]
  (println text)
  (println (:hook-url config))
  (client/post (:hook-url config)
               {:body text
                :content-type :json
                :socket-timeout 1000
                :connection-timeout 1000
                :accept :json}))
