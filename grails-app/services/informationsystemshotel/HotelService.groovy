package informationsystemshotel

import grails.gorm.transactions.Transactional

class HotelService {
    //поиск отеля по id
    def findHotelById(long id){
        Hotel.createCriteria().get {
            eq("id", id)
        }
    }

    //поиск отеля по имени и стране
    def findHotelByNameAndCountry(String name, Country country){
        Hotel.createCriteria().get {
            and {
                ilike("name", name)
                eq("country", country)
            }
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

    @Transactional
    def saveHotel(String name, int stars, Country country, String url){
        new Hotel(name: name, stars: stars, country: country, url: url).save(flush: true)
    }

    @Transactional
    def deleteHotelById(Long id){
        findHotelById(id).delete(flush: true)
    }

    @Transactional
    def changeHotelData(Hotel oldHotel, String newName, int newStars, String newUrl, Country newCountry){
        oldHotel.name = newName
        oldHotel.stars = newStars
        oldHotel.url = newUrl
        oldHotel.country = newCountry
    }
}
