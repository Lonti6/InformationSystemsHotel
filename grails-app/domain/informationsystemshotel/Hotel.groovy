package informationsystemshotel

import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class Hotel {

    String name
    Country country
    Integer stars
    String url

    static constraints = {
        name unique: true, nullable: false, maxSize: 255
        country nullable: false
        stars nullable: false, max: 5
        url nullable: true
    }

    static belongsTo = [country: Country]
}
