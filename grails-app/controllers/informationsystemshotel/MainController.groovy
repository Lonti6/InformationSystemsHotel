package informationsystemshotel

import grails.gorm.transactions.Transactional
import org.grails.datastore.mapping.query.Query
import org.grails.datastore.mapping.query.api.Criteria
import org.hibernate.HibernateException
import org.hibernate.criterion.CriteriaQuery
import org.hibernate.criterion.Order as CriterionOrder

class MainController{

    static allowedMethods = [searchHotels: 'POST']

    def index() {
        Country currentCountry = Country.all.find { it.name == params.selectCountry }

        List<Hotel> hots;
        if (params.hotelName != null && currentCountry!=null)
            hots = Hotel.findAllByNameIlike("%${params.hotelName}%", [sort:[stars:"desc", name:"asc"]])
                    .findAll(x -> x.country == currentCountry)
        else if (params.hotelName != null)
            hots = Hotel.findAllByNameIlike("%${params.hotelName}%", [sort:[stars:"desc", name:"asc"]])
        else
            hots = Hotel.findAllByNameIlike("%",[sort:[stars:"desc", name:"asc"]])


        println(hots.toString())

        [
                hotels   : hots,
                countries: Country.all,
                currentHot: params.hotelName,
                hotelsSize: hots.size()
        ]
    }

    @Transactional
    def delHotel(){
        Hotel.findByName(params.hotelDelete).delete(flush: true)

        redirect(url: "/addHotel")
    }

    def addHotel(){
        [
                countries: Country.all,
                hotels: Hotel.all
        ]
    }

    @Transactional
    def createHotel(){
        def hotelName = params.hotelName;
        def stars = params.stars;
        if (hotelName != null && stars != null){
            Country country = Country.findByName(params.selectCountry)
            new Hotel(name: hotelName, country: country, stars: stars, url: params.urlHotel).save()
        }
        redirect(url: "/addHotel")
    }

    def refactorHotel(){
        Hotel hotel = Hotel.findByName(params.hotelName);
        def hotelName = hotel.getName();
        def hotelStars = hotel.getStars();
        def hotelURL = hotel.getUrl();
        def currentCountry = hotel.getCountry().name;
        [
                countries: Country.all,
                hotelName: hotelName,
                stars: hotelStars,
                hotelURL: hotelURL,
                currentCountry: currentCountry
        ]
    }

    @Transactional
    def refactHotel(){
        def hotelName = params.hotelName;
        int stars = ((params.stars.toString()).trim().toInteger());
        if (hotelName != null && stars != null){
            Country country = Country.findByName(params.selectCountry)
            def hotel = Hotel.findByName(params.oldHotelName)
            hotel.name = hotelName;
            hotel.stars = ((int)stars);
            hotel.country = country;
            hotel.url = params.urlHotel
            hotel.save(flush:true, failOnError:true);

        }
        redirect(url: "/addHotel")
    }

    def addCountry(){
        [
                countries: Country.all
        ]
    }

    @Transactional
    def createCountry(){
        def countryName = params.сountryName;
        def countryCapital = params.сountryCapital;
        if (countryName != null && countryCapital != null)
            new Country(name: countryName, capital: countryCapital).save()

        redirect(url: "/addCountry")
    }

    @Transactional
    def delCountry(){
        Country.findByName(params.countryDelete).delete(flush: true)
        redirect(url: "/addCountry")
    }

    def refactorCountry(){
        Country country = Country.findByName(params.countryName);
        [
                countryName: country.name,
                countryCapital: country.capital,
        ]
    }
    @Transactional
    def refactCountry(){
        def countryName = params.сountryName.toString();
        def oldCountryName = params.oldCountryName.toString();
        def countryCapital = params.сountryCapital.toString();

        if (countryName != null && countryCapital != null){
            def country = Country.findByName(oldCountryName);
            country.setName(countryName)
            country.capital = countryCapital
            country.save(flush:true, failOnError:true)
        }
        redirect(url: "/addCountry")
    }

}
