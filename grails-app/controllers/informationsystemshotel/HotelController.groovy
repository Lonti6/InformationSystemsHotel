package informationsystemshotel

import grails.gorm.transactions.Transactional

class HotelController {
    CountryService countryService
    HotelService hotelService


    def index() {
        [
                countries: countryService.allCountries,
                hotels: hotelService.allHotels
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

    @Transactional
    def delHotel(){

        Hotel.findByName(params.hotelDelete).delete(flush: true)//удаляем запись из БД
        log.info("Удалён отель с именем ${params.hotelDelete}")
        redirect(url: "/addHotel")//перенаправляем
    }
}
