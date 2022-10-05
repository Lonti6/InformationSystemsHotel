package informationsystemshotel

import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class Country {

    String name;
    String capital;

    static constraints = {
        name (unique: true, nullable: false, maxSize: 255)
        capital (nullable: false, maxSize: 128)
    }
    static hasMany = [fiction: Hotel, nonFiction: Hotel]
}
