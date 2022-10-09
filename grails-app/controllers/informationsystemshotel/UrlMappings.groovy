package informationsystemshotel

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/search/index", controller: "Search")

        "/hotels" (controller: "Hotel", action: "index")
        post "/delHotel" (controller: "Hotel", action: "delHotel")
        "/addHotel"(controller: "Hotel", action: "creatingHotel")
        post "/createHotel"(controller: "Hotel", action: "createHotel")

         "/countries"(controller: "Country", action: "index")
         post "/createCountry"(controller: "Country", action: "createCountry")
         "/addCountry"(controller: "Country", action: "creatingCountry")
         post "/delCountry"(controller: "Country", action: "delCountry")

        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
