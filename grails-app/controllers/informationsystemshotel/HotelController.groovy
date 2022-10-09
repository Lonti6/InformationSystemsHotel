package informationsystemshotel

import grails.gorm.transactions.Transactional

class HotelController {
    static final int postOnPage = 10

    CountryService countryService
    HotelService hotelService


    //список отелей
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

    //созранение/перезапись отеля
    @Transactional
    def save(Hotel hotel){
        hotel.country = countryService.findCountryByName(params.countryName)

        if (!hotel.save(flush: true)){
            flash.message = "Ошибка при создании отеля ${hotel.getName()} (такое имя занято)"
            return render(view: "edit", model: [hotel: hotel, countries: countryService.allCountries])
        }
        flash.message = "Успешное сохранение"
        redirect(controller: "hotel", action: "index")
    }

    //удаление отеля
    @Transactional
    def delHotel() {
        hotelService.deleteHotelById(Long.parseLong(params.hotelDelete))
        log.info("Удалён отель с id ${params.hotelDelete}")
        redirect(action: "index")//перенаправляем
    }

    //при создании с нуля
    def createView(){
        render(view: "edit", model: [hotel: new Hotel(), countries: countryService.allCountries])
    }

    //при редактировании
    def updateView(Long id){
        render(view: "edit", model: [hotel: hotelService.findHotelById(id), countries: countryService.allCountries])
    }
}