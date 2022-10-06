package informationsystemshotel

import grails.gorm.transactions.Transactional

class MainController{
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

    @Transactional
    def delHotel(){

        Hotel.findByName(params.hotelDelete).delete(flush: true)//удаляем запись из БД
        log.info("Удалён отель с именем ${params.hotelDelete}")
        redirect(url: "/addHotel")//перенаправляем
    }

    def addHotel(){
        [
                countries: Country.all,
                hotels: Hotel.all
        ]
    }

    @Transactional
    def createHotel(){
        def hotelName = params.hotelName;
        def stars = params.stars;
        //если имя и количество звёзд не наловые, то создаём
        if (hotelName != null && stars != null){
            Country country = Country.findByName(params.selectCountry)
            def h =Hotel.findByNameAndCountry(hotelName.trim(), country)
            //если такой отеля в такой стране не найдено, то добавляем
            if (h == null)
                new Hotel(name: hotelName.trim(), country: country, stars: stars, url: params.urlHotel).save(flush:true)
            log.info("Создан отель с именем: ${params.hotelName}")
        }
        redirect(url: "/addHotel")
    }

    def refactorHotel(){
        Hotel hotel = Hotel.findByName(params.hotelName);
        def hotelName = hotel.getName();
        def hotelStars = hotel.getStars();
        def hotelURL = hotel.getUrl();
        def currentCountry = hotel.getCountry().name;
        [
                countries: Country.all,
                hotelName: hotelName,
                stars: hotelStars,
                hotelURL: hotelURL,
                currentCountry: currentCountry
        ]
    }

    @Transactional
    def refactHotel(){
        def hotelName = params.hotelName;
        int stars = Integer.parseInt(params.stars)
        //если имя и количество звёзд не наловые, то изменяем
        if (hotelName != null && stars != null){
            Country country = Country.findByName(params.selectCountry)
            def h =Hotel.findByNameAndCountry(hotelName.trim(), country)
                if (h == null) {
                    def hotel = Hotel.findByName(params.oldHotelName)
                    hotel.name = hotelName.trim();
                    hotel.stars = ((int) stars);
                    hotel.country = country;
                    hotel.url = params.urlHotel
                    hotel.save(flush: true)
                }
            log.info("Изменён отель с именем: ${params.oldHotelName}")
        }
        redirect(url: "/addHotel")
    }

    def addCountry(){
        [
                countries: Country.all
        ]
    }

    @Transactional
    def createCountry(){
        def countryName = params.сountryName;
        def countryCapital = params.сountryCapital;
        //если имя и столица не наловые, то добавляем
        if (countryName != null && countryCapital != null) {
            new Country(name: countryName.trim(), capital: countryCapital.trim()).save(flush: true)
            log.info("Создана страна с именем: ${params.сountryName}")
        }

        redirect(url: "/addCountry")
    }

    @Transactional
    def delCountry(){
        Country.findByName(params.countryDelete).delete(flush: true)
        log.info("Удалена страна с именем: ${params.countryDelete}")
        redirect(url: "/addCountry")
    }

    def refactorCountry(){
        Country country = Country.findByName(params.countryName);
        [
                countryName: country.name,
                countryCapital: country.capital,
        ]
    }
    @Transactional
    def refactCountry(){
        def countryName = params.сountryName
        def oldCountryName = params.oldCountryName.toString().trim()
        def countryCapital = params.сountryCapital

        if (countryName != null && countryCapital != null){
            def country = Country.findByName(oldCountryName);
            country.name = countryName.trim()
            country.capital = countryCapital.trim()
            country.save(flush:true)
            log.info("Изменена страна с именем: ${params.oldCountryName}")
        }
        redirect(url: "/addCountry")
    }

}
