package informationsystemshotel

class SearchController {
    static final int postOnPage = 10;

    def index() {
        //ищем страну, которая указана в поиске
        Country currentCountry = Country.all.find { it.name == params.selectCountry }

        int countHotels;
        List<Hotel> hots;
        def searchParam = params.hotelName == null ? "":params.hotelName

        log.info(params.toString())

        //если указана страна, ищем по стране и тексту поиска
        if (currentCountry != null) {
            countHotels = Hotel.findAllByCountryAndNameIlike(currentCountry, "%${searchParam}%").size()
            hots = Hotel.findAllByCountryAndNameIlike(
                    currentCountry,
                    "%${searchParam}%",
                    [
                            sort  : [stars: "desc", name: "asc"],//параметры сортировки
                            max   : postOnPage,//количество для получения
                            offset: params.offset//место с которого получать отели
                    ]
            )
        }
        //если страна не указана, ищем только по тексту поиска
        else {
            countHotels = Hotel.findAllByNameIlike("%${searchParam}%").size()
            hots = Hotel.findAllByNameIlike(
                    "%${searchParam}%",
                    [
                            sort  : [stars: "desc", name: "asc"],//параметры сортировки
                            max   : postOnPage,//количество для получения
                            offset: params.offset//место с которого получать отели
                    ]
            )
        }

        log.info("Выбраны отели: "+String.join(", ", hots))

        [
                hotels   : hots,//пердаём на представление найденные отели
                countries: Country.all,//пердаём на представление список стран из БД
                currentCountry: currentCountry,//пердаём на представление текущую страну
                currentHot: params.hotelName,//пердаём на представление текущий текст запроса отеля
                hotelCount: countHotels,//пердаём на представление количество всего найденных записей
                postOnPage: postOnPage//количество записей на странице
        ]
    }
}
