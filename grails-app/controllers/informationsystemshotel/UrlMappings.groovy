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
        "/addHotel"(controller: "Hotel", action: "createView")
        post "/createHotel"(controller: "Hotel", action: "createHotel")

         "/countries"(controller: "Country", action: "index")
         post "/delCountry"(controller: "Country", action: "delCountry")
         "/addCountry"(controller: "Country", action: "createView")
        post "/createCountry"(controller: "Country", action: "createCountry")

        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
