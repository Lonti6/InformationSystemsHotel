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
                countryCount: countries.getTotalCount(),
                offset: params.offset
        ]
    }

    @Transactional
    def createCountry() {
        def countryName = params.сountryName.trim();
        def countryCapital = params.сountryCapital.trim();

        if (params.oldCountryName != null){
            println("fdsfsfds")
            redirect(action: "refactCountry", params: params)
            return
        }

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

    @Transactional
    def refactCountry() {
        def сountryName = params.сountryName.trim()
        def oldCountryName = params.oldCountryName.trim()
        def countryCapital = params.сountryCapital.trim()

        Country currentCountry = countryService.findCountryByName(сountryName)
        Country oldCountry = countryService.findCountryByName(oldCountryName)

        if (currentCountry == null){
            countryService.changeCountryData(
                    oldCountry,
                    сountryName,
                    countryCapital
            )
            redirect(action: "index")
        }
        else {
            redirect(action: "creatingCountry", params: ["errorText": "Страна с таким именем уже существует!", "countryName":oldCountryName])
        }


    }

    def creatingCountry(){
        Country c;
        String type = "Создать"
        if (params.countryName != null) {
            c = countryService.findCountryByName(params.countryName)
            type = "Изменить"
        }
        [
                errorText: params.errorText,
                countryName: c == null? null: c.name,
                countryCapital: c== null? null: c.capital,
                actionType: type,
                errorText: params.errorText
        ]
    }
}
