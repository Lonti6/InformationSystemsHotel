package informationsystemshotel

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/search/index", controller: "Search")

        post "/delHotel" (controller: "Hotel", action: "delHotel")
        "/showHotel"(view:"/hotel/index", controller: "Hotel", action: "index")
        post "/addHotel"(controller: "Hotel", action: "createHotel")
        "/refactorHotel"(view:"/hotel/refactorHotel", controller: "Hotel", action: "refactorHotel")
        post "/refactorHotel"(controller: "Hotel", action: "refactHotel")

         "/countries"(controller: "Country", action: "index")
         post "/createCountry"(controller: "Country", action: "createCountry")
         "/addCountry"(controller: "Country", action: "creatingCountry")
         post "/delCountry"(controller: "Country", action: "delCountry")
         "/refactorCountry"(controller: "Country", action: "refactorCountry")
         post "/refactorCountry"(controller: "Country", action: "refactCountry")

        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
