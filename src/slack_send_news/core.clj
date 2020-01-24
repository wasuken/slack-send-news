(ns slack-send-news.core
  (:require [slack-send-news.news :refer :all]
            [slack-send-news.util :refer :all]
            [clojure.data.json :as json]
            [clj-http.client :as client])
  (:gen-class))

(defn -main
  []
  (println (->> (:body (client/get (first (list-urls))))
                (json/read-str)
                (take 10)
                (news-id-list->format-text)
                (create-post-format)
                (basic-post)))
  )
