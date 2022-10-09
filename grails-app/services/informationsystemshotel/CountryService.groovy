package informationsystemshotel

import grails.gorm.transactions.Transactional

class CountryService {

    //поиск страны по id
    def findCountryById(long id){
        Country.createCriteria().get {
            eq("id", id)
        }
    }

    //получение всех стран
    def getAllCountries() {
        Country.all
    }

    //получение стран с оффсетом
    def getCountriesOffset(int maxOnPage, int offset) {
        Country.createCriteria().list(max: maxOnPage, offset: offset){
            order("name", "asc")
        }
    }

    //поиск страны по имени
    def findCountryByName(String name){
        Country.createCriteria().get {
            ilike("name", name)
        }
    }

    //удаление страны по имени
    @Transactional
    def deleteCountryByName(String name){
        findCountryByName(name).delete(flush: true)
    }
}
