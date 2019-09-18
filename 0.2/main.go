package main

import (
	"fmt"
	"github.com/RealJK/rss-parser-go"
	"log"
	"net/http"
)

type item struct {
	item_number int
	title       string
	link        string
	description string
	guid        string
}

type rssChannel struct {
	title         string
	generator     string
	pubDate       rss.RSSDate
	lastBuildDate string
	description   string
	items         []*item
}

func printRSSChannel(channel *rssChannel) {
	fmt.Printf("Title : %s\n", channel.title)

	fmt.Printf("Generator : %s\n", channel.generator)

	fmt.Printf("PubDate : %s\n", channel.pubDate)

	fmt.Printf("LastBuildDate : %s\n", channel.lastBuildDate)

	fmt.Printf("Description : %s\n", channel.description)

	fmt.Printf("Number of Items : %d\n", len(channel.items))

	for v := range channel.items {

		item := channel.items[v]
		fmt.Println()

		fmt.Printf("Item Number : %d\n", v)

		fmt.Printf("Title : %s\n", item.title)

		fmt.Printf("Link : %s\n", item.link)

		fmt.Printf("Description : %s\n", item.description)

		fmt.Printf("Guid : %s\n", item.guid)
	}
}

func parseRSS(str string, rssChannel *rssChannel) {
	rssObject, err := rss.ParseRSS(str)
	if err != nil {
		rssChannel.title = rssObject.Channel.Title
		rssChannel.generator = rssObject.Channel.Generator
		rssChannel.pubDate = rssObject.Channel.PubDate
		rssChannel.lastBuildDate = rssObject.Channel.LastBuildDate
		rssChannel.description = rssObject.Channel.Description
		items := make([]*item, len(rssObject.Channel.Items))
		for i := range items {
			var con item
			items[i] = &con
		}

		for v := range rssObject.Channel.Items {

			item := rssObject.Channel.Items[v]

			items[v].item_number = v
			items[v].title = item.Title
			items[v].link = item.Link
			items[v].description = item.Description
			items[v].guid = item.Guid.Value

		}
		rssChannel.items = items

	}
}

func HomeRouterHandler(w http.ResponseWriter, r *http.Request) {
	http.ServeFile(w, r, "main.html")
}

func main() {
	var structs [6]*rssChannel
	for i := range structs {
		var con rssChannel
		structs[i] = &con
	}
	parseRSS("http://blagnews.ru/rss_vk.xml", structs[0])
	parseRSS("https://lenta.ru/rss", structs[0])
	parseRSS("http://www.rssboard.org/files/sample-rss-2.xml", structs[1])
	parseRSS("https://news.mail.ru/rss/90/", structs[2])
	parseRSS("http://technolog.edu.ru/index.php?option=com_k2&view=itemlist&layout=category&task=category&id=8&lang=ru&format=feed", structs[3])
	parseRSS("http://news.ap-pa.ru/rss.xml", structs[5])

	http.HandleFunc("/", HomeRouterHandler)
	err := http.ListenAndServe(":8082", nil)
	if err != nil {

		log.Fatal("ListenAndServe: ", err)

	}
}
