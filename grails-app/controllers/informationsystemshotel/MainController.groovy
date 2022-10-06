package informationsystemshotel

import grails.gorm.transactions.Transactional

class MainController{
    static final int postOnPage = 10;

    def index() {
        //ищем страну, которая указана в поиске
        Country currentCountry = Country.all.find { it.name == params.selectCountry }
        int countHotels;

        //делаем выборку в зависимости от поиска
        List<Hotel> hots;
        //если указана страна и поиск по заголовку отеля
        if (params.hotelName != null && currentCountry!=null) {
            countHotels = Hotel.findAllByNameIlike("%${params.hotelName}%")
                    .findAll(x -> x.country == currentCountry).size()//получаем количество отелей по запросу
            hots = Hotel.findAllByNameIlike(//получаем сами отели
                    "%${params.hotelName}%",//запрос
                    [
                            sort: [stars: "desc", name: "asc"],//параметры сортировки
                            max:postOnPage,//количество для получения
                            offset: params.offset//место с которого получать отели
                    ]
            )
                    .findAll(x -> x.country == currentCountry)
        }
        //если поиск только по заголовку
        else if (params.hotelName != null) {
            countHotels = Hotel.findAllByNameIlike("%${params.hotelName}%").size()//получаем количество отелей по запросу
            hots = Hotel.findAllByNameIlike(
                    "%${params.hotelName}%",//запрос
                    [
                            sort: [stars: "desc", name: "asc"],//параметры сортировки
                            max:postOnPage,//количество для получения
                            offset: params.offset//место с которого получать отели
                    ]
            )
        }
        //параметры поиска не указаны
        else {
            countHotels = Hotel.all.size()//получаем количество отелей по запросу
            hots = Hotel.findAllByNameIlike(
                    "%",//запрос
                    [sort: [stars: "desc", name: "asc"],//параметры сортировки
                     max:postOnPage,//количество для получения
                     offset: params.offset//место с которого получать отели
                    ]
            )
        }

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
        if (countryName != null && countryCapital != null)
            new Country(name: countryName.trim(), capital: countryCapital.trim()).save(flush:true)

        redirect(url: "/addCountry")
    }

    @Transactional
    def delCountry(){
        Country.findByName(params.countryDelete).delete(flush: true)
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
        }
        redirect(url: "/addCountry")
    }

}
