<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="ru" class="no-js">
<head>
    <meta name="layout" content="main"/>
    <title>Отели</title>
    <asset:stylesheet src="styleOfTable.css"/>
</head>

<body>
<div id="content" role="main">
    <div class="container">
        <h1>Добавление отеля</h1>
        <br>

        <form method="post" action="/addHotel">
            <select name="selectCountry" style="margin-right: 50px">
                <% countries.each { country -> %>
                <option>${country.getName()}</option>
                <% } %>
            </select>
            <br>
            <label for="h1" id="lab1">Наименование</label>
            <input type="text" name="hotelName" alt="Название отеля" id="h1" style="margin-bottom: 10px" required>
            <br>
            <label for="h2" id="lab2">Звёздность</label>
            <input type="number" name="stars" alt="Звёздность" id="h2" max="5" min="1" style="margin-bottom: 10px" required>
            <br>
            <label for="h3" id="lab3">Ссылка</label>
            <input type="url" name="urlHotel" alt="Ссылка на сайт" id="h3" style="margin-bottom: 10px">
            <button type="submit">Добавить</button>
        </form>

        <table class="html-4">
            <thead>
            <tr>
                <td>Наименование</td>
                <td>Звёздность</td>
                <td>Страна</td>
                <td>Действия</td>
            </tr>
            </thead>
            <tbody>
            <% hotels.each { hotel -> %>
            <tr>
                <td>
                    ${hotel.name}
                    <g:if test="${hotel.url != null}">
                        <br>
                        <a href="${hotel.getUrl()}">Перейти на сайт</a>
                    </g:if>
                </td>
                <td>
                    <g:each in="${(1..hotel.stars).toList()}" var="i">
                        ★
                    </g:each>
                </td>
                <td>
                    ${hotel.country.name}
                </td>
                <td>
                    <form method="post" action="/delHotel" style="margin-bottom: 10px">
                        <button name="hotelDelete" value="${hotel.getName()}" type="submit">Удалить?</button>
                    </form>

                    <form action="/refactorHotel">
                            <button name="hotelName" value="${hotel.getName()}" type="submit">Редактировать</button>
                        </a>
                    </form>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>