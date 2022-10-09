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

        <h1 style="color: red">${flash.message}</h1>

        <g:form method="post" url="[controller: 'hotel', action: 'save']">
            <input name="id" value="${hotel?.id ?: ''}" type="hidden">
            <select name="countryName" style="margin-right: 50px">
                <g:if test="${hotel != null}">
                    <option>${hotel?.getCountry()?.getName() ?: ''}</option>
                </g:if>
                <% countries.each { country -> %>
                <option>${country.getName()}</option>
                <% } %>
            </select>
            <br>
            <br>
            <label style="width: 100px" for="h1" id="lab1">Наименование</label>
            <input value="${hotel?.name ?: ''}" type="text" name="name" alt="Название отеля" id="h1"
                   style="margin-bottom: 10px" required>
            <br>
            <label style="width: 100px" for="h2" id="lab2">Звёздность</label>
            <input value="${hotel?.stars ?: ''}" type="number" name="stars" alt="Звёздность" id="h2" max="5" min="1"
                   style="margin-bottom: 10px"
                   required>
            <br>
            <label style="width: 100px" for="h3" id="lab3">Ссылка</label>
            <input value="${hotel?.url ?: ''}" type="url" name="url" alt="Ссылка на сайт" id="h3"
                   style="margin-bottom: 10px">
            <button type="submit">Добавить</button>
        </g:form>

    </div>
</div>
</body>
</html>