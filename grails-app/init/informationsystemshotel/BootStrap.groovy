package informationsystemshotel

class BootStrap {
    Map<String, String> countries = Map.of(
            "Россия", "Москва",
            "Белоруссия", "Минск",
            "Казахстан", "Астана"
    )

    def init = { servletContext ->
        countries.forEach((k, v) -> new Country(name: k, capital: v).save())

        def cs = Country.all;

        println(cs.size())

        Country rus = Country.findByName("Россия")
        Country bel = Country.findByName("Белоруссия")
        Country kaz = Country.findByName("Казахстан")


        Hotel.saveAll(
                new Hotel(name: "Карагай", country: rus, stars: 2, url: "https://sankaragai.ru/"),
                new Hotel(name: "Минск-Отель", country: bel, stars: 1, url: null),
                new Hotel(name: "Онегин", country: rus, stars: 3, url: "https://hotelonegin.com/"),
                new Hotel(name: "Урал", country: rus, stars: 5, url: null),
                new Hotel(name: "Астана-Хостел", country: kaz, stars: 2, url: null),
        )

        //hotels.forEach(x -> )
    }
    def destroy = {
    }
}
