package informationsystemshotel

import grails.gorm.transactions.Transactional

class CountryController {
    static final int postOnPage = 10;

    CountryService countryService

    def index() {
        def countries = countryService.getCountriesOffset(postOnPage, ((params.offset == null?0:params.offset).toInteger()))
        [
                countries: countries,
                max: postOnPage,
                countryCount: countryService.getCountAll(),
                offset: params.offset
        ]
    }

    @Transactional
    def createCountry() {
        def countryName = params.сountryName.trim();
        def countryCapital = params.сountryCapital.trim();

        //если имя и столица не наловые, то добавляем
        if (countryService.findCountryByName(countryName) == null) {
            countryService.saveCountry(countryName, countryCapital)
            log.info("Создана страна с именем: ${params.сountryName}")
            redirect(action: "index")
        } else {
            println("Fdfsfadsfs")
            redirect(action: "creatingCountry", params: ["errorText": "Страна с таким именем уже существует!"])
        }
    }

    @Transactional
    def delCountry() {
        countryService.deleteCountryByName(params.countryDelete);
        log.info("Удалена страна с именем: ${params.countryDelete}")
        redirect(action: "index")
    }

    def refactorCountry() {
        Country country = countryService.findCountryByName(params.countryName);
        [
                countryName   : country.name,
                countryCapital: country.capital,
                errorText     : params.errorText
        ]
    }

    @Transactional
    def refactCountry() {
        def сountryName = params.сountryName.trim()
        def oldCountryName = params.oldCountryName.trim()
        def countryCapital = params.сountryCapital.trim()

        Country currentCountry = countryService.findCountryByName(сountryName)
        Country oldCountry = countryService.findCountryByName(oldCountryName)

        if (currentCountry == null || (currentCountry != null && currentCountry.name == oldCountryName)){
            countryService.changeCountryData(
                    oldCountry,
                    сountryName,
                    countryCapital
            )
            redirect(action: "index")
        }
        else {
            redirect(action: "refactorCountry", params: ["errorText": "Страна с таким именем уже существует!", "countryName":oldCountryName])
        }


    }

    def creatingCountry(){
        [errorText: params.errorText]
    }
}
