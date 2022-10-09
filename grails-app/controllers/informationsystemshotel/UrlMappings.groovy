package informationsystemshotel

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
            }
        }

        "/"(view:"/search/index", controller: "Search")

        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
