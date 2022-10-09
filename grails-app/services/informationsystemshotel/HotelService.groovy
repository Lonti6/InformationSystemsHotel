package informationsystemshotel

import grails.gorm.transactions.Transactional

class HotelService {
    //поиск отеля по id
    def findHotelById(long id){
        Hotel.createCriteria().get {
            eq("id", id)
        }
    }

    //поиск отелей по стране и имени с максимальным количеством и офсетом
    def findAllHotelByNameAndCountryWithSortAndOffset(String name, Country country, int max, int offset){
        Hotel.createCriteria().list(max: max, offset: offset) {
            and {
                ilike("name", "%${name}%")
                if (country != null) {
                    eq("country", country)
                }
                and{
                    order("stars", "desc")
                    order("name", "asc")
                }
            }
        }
    }

    //удаление отеля по id
    @Transactional
    def deleteHotelById(Long id){
        findHotelById(id).delete(flush: true)
    }
}