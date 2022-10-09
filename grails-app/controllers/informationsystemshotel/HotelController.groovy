package informationsystemshotel

import grails.gorm.transactions.Transactional

class HotelController {
    static final int postOnPage = 10

    CountryService countryService
    HotelService hotelService


    def index() {
        int offset = params.offset == null? 0: Integer.parseInt(params.offset)
        def hots = hotelService.findAllHotelByNameAndCountryWithSortAndOffset("", null, postOnPage, offset)
        [
                hotels: hots,
                max: postOnPage,
                offset: offset,
                hotelsCount: hots.getTotalCount()
        ]
    }

    @Transactional
    def createHotel() {
        if (params.oldHotelName != null) {
            redirect(action: "refactHotel", params: params)
            return
        }

        def hotelName = params.hotelName.trim()

        Country country = countryService.findCountryByName(params.selectCountry)
        def h = hotelService.findHotelByNameAndCountry(hotelName, country)

        println(country.name + ", "+hotelName)

        //если такой отеля в такой стране не найдено, то добавляем
        if (h == null) {
            hotelService.saveHotel(hotelName.toString(), Integer.parseInt(params.stars), country, params.urlHotel.trim())
        }
        else {
            redirect(action: "creatingHotel", params: ["errorText": "Такой отель, в этой стране, уже существует!"])
            return
        }

        log.info("Создан отель с именем: ${params.hotelName}")
        redirect(url: "/addHotel")
    }

    @Transactional
    def refactHotel() {
        def hotelName = params.hotelName.trim();
        int stars = Integer.parseInt(params.stars)
        def url = params.urlHotel.trim()
        long id = Long.parseLong(params.hotelId)
        def countryName = params.selectCountry

        Country country = countryService.findCountryByName(countryName)
        Hotel h = hotelService.findHotelByNameAndCountry(hotelName, country)
        if (h == null) {
            def hotel = hotelService.findHotelById(id)
            hotelService.changeHotelData(hotel, hotelName, stars, url, country)
            redirect(action: "index")
        }
        else if ((h.name!=hotelName || h.stars != stars || h.url != url)){
            hotelService.changeHotelData(h, hotelName, stars, url, country)
            params.put("errorText", "Такой отель, в этой стране, уже существует!")
            redirect(action: "creatingHotel", params: params)
        }
    }

    @Transactional
    def delHotel() {
        hotelService.deleteHotelById(Long.parseLong(params.hotelDelete))
        log.info("Удалён отель с id ${params.hotelDelete}")
        redirect(action: "index")//перенаправляем
    }

    def creatingHotel() {
        Hotel hotel
        if (params.hotelId != null)
            hotel = hotelService.findHotelById(Long.parseLong(params.hotelId))
        [
                countries: countryService.allCountries,
                hotelName: hotel == null?null:hotel.name,
                hotelStars: hotel == null?null:hotel.stars,
                hotelUrl: hotel == null?null:hotel.url,
                hotelCountry: hotel == null?null:hotel.country.name,
                hotelId: hotel == null?null:hotel.id,
                errorText: params.errorText
        ]
    }
}
