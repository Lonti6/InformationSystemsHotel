<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Редактирование отеля</title>
</head>

<body>
<div id="content" role="main">
    <div class="container">
        <h1>Редактирование отеля</h1>
        <br>

        <form method="post" action="/refactorHotel">
            <select name="selectCountry" style="margin-right: 50px">
                <option>${currentCountry}</option>
                <% countries.each { country -> %>
                <option>${country.getName()}</option>
                <% } %>
            </select>
            <br>
            <label for="h4" id="lab4">Старое наименование</label>
            <input value="${hotelName}" type="text" name="oldHotelName" alt="Название отеля" id="h4" style="margin-bottom: 10px" readonly>
            <br>
            <label for="h1" id="lab1">Наименование</label>
            <input value="${hotelName}" type="text" name="hotelName" alt="Название отеля" id="h1" style="margin-bottom: 10px">
            <br>
            <label for="h2" id="lab2">Звёздность</label>
            <input value="${stars}" type="number" name="stars" alt="Звёздность" id="h2" max="5" min="1" style="margin-bottom: 10px">
            <br>
            <label for="h3" id="lab3">Ссылка</label>
            <input value="${hotelURL}" type="url" name="urlHotel" alt="Ссылка на сайт" id="h3" style="margin-bottom: 10px">
            <button type="submit">Сохранить</button>
        </form>
    </div>
</div>
</body>
</html>