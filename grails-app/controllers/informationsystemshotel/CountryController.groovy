package informationsystemshotel

import grails.gorm.transactions.Transactional

class CountryController {
    static final int postOnPage = 10;

    CountryService countryService

    //список стран
    def index() {
        def countries = countryService.getCountriesOffset(postOnPage, ((params.offset?:0).toInteger()))
        [
                countries: countries,
                max: postOnPage,
                countryCount: countries.getTotalCount(),
                offset: params.offset
        ]
    }

    //сохранение/изменение страны
    @Transactional
    def save(Country country){
        if (!country.save(flush: true)){
            flash.message = "Ошибка при создании страны ${country.getName()} (такое имя занято)"
            return render(view: "edit", model: [country: country])
        }
        flash.message = "Успешное сохранение ${country.name}"
        redirect(controller: "country", action: "index")
    }

    //удаление страны
    @Transactional
    def delCountry() {
        countryService.deleteCountryByName(params.countryDelete);
        log.info("Удалена страна с именем: ${params.countryDelete}")
        flash.message = "Успешное удаление страны"
        redirect(action: "index")
    }

    //при создании с нуля
    def create(){
        render(view: "edit", model: [country: new Country()])
    }

    //при редактировании
    def update(Long id){
        render(view: "edit", model: [country: countryService.findCountryById(id)])
    }
}
