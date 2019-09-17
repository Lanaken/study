package main

import (
	"fmt"
	"net/http" // пакет для поддержки HTTP протокола

	"log" // пакет для логирования
)

func HomeRouterHandler(w http.ResponseWriter, r *http.Request) {
	//	r.ParseForm() //анализ аргументов,
	http.ServeFile(w, r, "main.html")

	/*fmt.Println(r.Form) // ввод информации о форме на стороне сервера

	fmt.Println("path", r.URL.Path)

	fmt.Println("scheme", r.URL.Scheme)

	fmt.Println(r.Form["url_long"])

	for k, v := range r.Form {

		fmt.Println("key:", k)

		fmt.Println("val:", strings.Join(v, ""))

	}

	fmt.Fprintf(w, "Test!")*/ // отправляем данные на клиентскую сторону

}

func handler(w http.ResponseWriter, r *http.Request) {
	name := r.FormValue("username")
	age := r.FormValue("userage")
	fmt.Fprintf(w, "Имя: %s Возраст: %s", name, age)
}

func handler2(w http.ResponseWriter, r *http.Request) {
	name := r.URL.Query().Get("name")
	age := r.URL.Query().Get("age")
	fmt.Fprintf(w, "Имя: %s Возраст: %s", name, age)
}

func mainHadler(w http.ResponseWriter, r *http.Request) {
	http.ServeFile(w, r, "index.html")
}

func videoHandler(w http.ResponseWriter, r *http.Request) {
	http.ServeFile(w, r, "lol.html")
}
func main() {

	http.HandleFunc("/", HomeRouterHandler)
	http.HandleFunc("/post", handler)
	http.HandleFunc("/get", handler2)
	http.HandleFunc("/form", mainHadler)
	http.HandleFunc("/video", videoHandler)

	err := http.ListenAndServe(":8082", nil) // задаем слушать порт

	if err != nil {

		log.Fatal("ListenAndServe: ", err)

	}

}
