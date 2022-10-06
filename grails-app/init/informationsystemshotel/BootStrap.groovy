package informationsystemshotel

import grails.gorm.transactions.Transactional


class BootStrap {
    Map<String, String> countries = Map.of(
            "Россия", "Москва",
            "Белоруссия", "Минск",
            "Казахстан", "Астана",
            "Германия", "Берлин",
            "Куба", "Гавана",
            "Китай", "Пекин"
    )

    def init = { servletContext ->
        log.info("Началась стартовая генерация данных")
        countries.forEach((k, v) -> new Country(name: k, capital: v).save())

        String[] slogs = ["об", "на", "ва", "га", "те", "ту", "ку", "ке", "му", "ме", "ну", "не"]
        def cs = Country.all;
        def random = new Random();

        for (int i = 0; i < 100; i++) {
            new Hotel(
                    name: "" +
                            "${slogs[random.nextInt(slogs.length)]}" +
                            "${slogs[random.nextInt(slogs.length)]}" +
                            "${slogs[random.nextInt(slogs.length)]}" +
                            "${slogs[random.nextInt(slogs.length)]}",
                    stars: random.nextInt(5)+1,
                    country: cs[random.nextInt(cs.size())],
                    url: random.nextBoolean() ? "https://github.com/": null
            ).save()
        }
        log.info("Закончилась стартовая генерация данных")
        //hotels.forEach(x -> )
    }
    def destroy = {
    }
}
