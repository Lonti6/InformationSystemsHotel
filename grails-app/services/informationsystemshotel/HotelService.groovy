package informationsystemshotel

import grails.gorm.transactions.Transactional

class HotelService {

    def getAllHotels() {
        Hotel.all
    }
}
