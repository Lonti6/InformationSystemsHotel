package informationsystemshotel

import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class Hotel {

    String name
    Country country
    Integer stars
    String url

    static constraints = {
        name nullable: false, maxSize: 255, blank: false, validator: { val, obj ->
            def hotel = Hotel.findByCountryAndName(((Hotel)obj).country, val)
            if (hotel != null && hotel.id != ((Hotel)obj).id)
                ['alreadyExists']
        }
        country nullable: false
        stars nullable: false, max: 5
        url nullable: true, blank: true, validator: {
            it.toString().indexOf("http://") == 0 || it.toString().indexOf("https://") == 0 || it == null ?: ["urlStarts"]
        }
    }

    static belongsTo = [country: Country]
}
