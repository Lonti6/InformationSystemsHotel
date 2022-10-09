<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Создание отеля</title>
    <asset:stylesheet src="styleOfTable.css"/>
</head>


<body>
<div id="content" role="main">
    <div class="container">
        <h1>Добавление отеля</h1>
        <br>

        <g:if test="${errorText != null}">
            <h1>Ошибка</h1>

            <h3>${errorText}</h3>
        </g:if>

        <form method="post" action="/createHotel">
            <select name="selectCountry" style="margin-right: 50px">
                <g:if test="${hotelCountry != null}">
                    <option>${hotelCountry}</option>
                </g:if>
                <% countries.each { country -> %>
                <option>${country.getName()}</option>
                <% } %>
            </select>
            <br>
            <g:if test="${hotelName != null}">
                <br>
                <label style="width: 100px" for="h4" id="lab4">Старое наименование</label>
                <input value="${hotelName}" type="text" name="oldHotelName" alt="Название отеля" id="h4"
                       style="margin-bottom: 10px" readonly>
            </g:if>
            <br>
            <label style="width: 100px" for="h1" id="lab1">Наименование</label>
            <input value="${hotelName}" type="text" name="hotelName" alt="Название отеля" id="h1" style="margin-bottom: 10px" required>
            <br>
            <label  style="width: 100px" for="h2" id="lab2">Звёздность</label>
            <input value="${hotelStars}" type="number" name="stars" alt="Звёздность" id="h2" max="5" min="1" style="margin-bottom: 10px"
                   required>
            <br>
            <label  style="width: 100px" for="h3" id="lab3">Ссылка</label>
            <input  value="${hotelUrl}" type="url" name="urlHotel" alt="Ссылка на сайт" id="h3" style="margin-bottom: 10px">
            <button  value="${hotelId}" name="hotelId" type="submit">Добавить</button>
        </form>
    </div>
</div>
</body>
</html>