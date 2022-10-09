package informationsystemshotel

class BootStrap {
    Map<String, String> countries = new HashMap<>();

    def init = { servletContext ->

        countries.put("Россия", "Москва")
        countries.put("Белоруссия", "Минск")
        countries.put("Казахстан", "Астана")
        countries.put("Германия", "Берлин")
        countries.put("Куба", "Гавана")
        countries.put("Узбекистан", "Ташкент")
        countries.put("Бразилия", "Бразилиа")
        countries.put("Монголия", "Улан-Батор")
        countries.put("КНДР", "Пхеньян")
        countries.put("Китай", "Пекин")
        countries.put("Великобритания", "Лондон")

        log.info("Началась стартовая генерация данных")
        countries.forEach((k, v) -> new Country(name: k, capital: v).save())

        String[] slogs = ["об", "на", "ва", "га", "те", "ту", "ку", "ке", "му", "ме", "ну", "не"]
        def cs = Country.all;
        def random = new Random();

        for (int i = 0; i < 200; i++) {
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
    }
    def destroy = {
    }
}
