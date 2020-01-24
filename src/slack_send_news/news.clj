(ns slack-send-news.news
  (:require [slack-send-news.util :refer :all]
            [clj-http.client :as client]
            [clojure.data.json :as json]))

(def hackernews-url-map
  {:base "https://hacker-news.firebaseio.com/v0/"
   :get-list-children ["newstories.json" "topstories.json" "beststories.json"]
   :get-item-detail "item/%s.json"})

(defn list-urls []
  (map #(str (:base hackernews-url-map) %)
       (:get-list-children hackernews-url-map)))

(defn item-format []
  (str (:base hackernews-url-map)
       (:get-item-detail hackernews-url-map)))

(defn news-id-list->format-text [news-id-list]
  (let [news-msgs (atom [])]
    (doseq [id news-id-list]
      (Thread/sleep 1000)
      (let [news-json (json/read-str (:body (client/get (format (item-format) id))))]
        (reset! news-msgs (conj @news-msgs
                                (format "<%s|%s>"
                                        (get news-json "url")
                                        (get  news-json "title"))))))
    (clojure.string/join "\n" @news-msgs)))
