package informationsystemshotel

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/main/index", controller: "Main")
        post "/delHotel" (controller: "Main", action: "delHotel")
        "/addHotel"(view:"/main/addHotel", controller: "Main", action: "addHotel")
        post "/addHotel"(controller: "Main", action: "createHotel")
        "/refactorHotel"(view:"/main/refactorHotel", controller: "Main", action: "refactorHotel")
        post "/refactorHotel"(controller: "Main", action: "refactHotel")
         "/addCountry"(controller: "Main", action: "addCountry")
         post "/addCountry"(controller: "Main", action: "createCountry")
         post "/delCountry"(controller: "Main", action: "delCountry")
         "/refactorCountry"(controller: "Main", action: "refactorCountry")
         post "/refactorCountry"(controller: "Main", action: "refactCountry")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
