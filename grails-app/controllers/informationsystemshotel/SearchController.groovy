package informationsystemshotel

class SearchController {
    static final int postOnPage = 10;

    HotelService hotelService
    CountryService countryService

    def index() {
        //ищем страну, которая указана в поиске
        Country currentCountry = countryService.findCountryByName(params.selectCountry == null ? "Все" : params.selectCountry)
        log.info(params.toString())

        List<Hotel> hotels  = hotelService.findAllHotelByNameAndCountryWithSortAndOffset(
                params.hotelName?:"",
                currentCountry,
                postOnPage,
                params.offset == null ? 0 : Integer.parseInt(params.offset)
        )

        [
                hotels        : hotels,//пердаём на представление найденные отели
                countries     : Country.all,//пердаём на представление список стран из БД
                currentCountry: currentCountry,//пердаём на представление текущую страну
                currentHot    : params.hotelName,//пердаём на представление текущий текст запроса отеля
                hotelCount    : hotels.getTotalCount(),//пердаём на представление количество всего найденных записей
                postOnPage    : postOnPage//количество записей на странице
        ]
    }
}