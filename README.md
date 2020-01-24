# slack-send-news

## Usage

post news to slack.

## config example

resources/config.edn

```clojure
{
 :hook-url "<hook-url>",
 :template {:channel "<channel-name>"
            :username "<username>"
            :text ""}
 :post-times ["12:00" "18:00" "19:00" "20:00" "21:00" "22:00"]
 }

```
