package informationsystemshotel

import grails.gorm.transactions.Transactional

class CountryService {

    def getAllCountries() {
        Country.all
    }

    def getCountriesOffset(int maxOnPage, int offset) {
        Country.createCriteria().list(max: maxOnPage, offset: offset){
            order("name", "asc")
        }
    }

    def findCountryByName(String name){
        Country.createCriteria().get {
            ilike("name", name)
        }
    }

    @Transactional
    def saveCountry(String name, String capital){
        new Country(name: name, capital:capital).save(flush: true)
    }

    @Transactional
    def deleteCountryByName(String name){
        findCountryByName(name).delete(flush: true)
    }

    @Transactional
    def changeCountryData(Country oldCountry, String newName, String newCapital){
        oldCountry.name = newName
        oldCountry.capital = newCapital
        oldCountry.save(flush: true)
    }

    def getCountAll(){
        Country.all.size()
    }
}
