<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Редактирование страны</title>
    <asset:stylesheet src="styleOfTable.css"/>
</head>

<body>
<div id="content" role="main">
    <div class="container">
        <h1>Редактирование страны</h1>
        <br>

        <g:if test="${errorText != null}">
            <h1>Ошибка</h1>
            <h3>${errorText}</h3>
        </g:if>


        <form method="post" action="/refactorCountry">
            <br>
            <label for="h4" id="lab4">Старое наименование</label>
            <input value="${countryName}" type="text" name="oldCountryName" alt="Название отеля" id="h4" style="margin-bottom: 10px" readonly>
            <br>
            <label for="h1" id="lab1">Наименование</label>
            <input value="${countryName}" type="text" name="сountryName" alt="Название отеля" id="h1" style="margin-bottom: 10px" required>
            <br>
            <label for="h2" id="lab2">Столица</label>
            <input value="${countryCapital}" type="text" name="сountryCapital" alt="Звёздность" id="h2" max="5" min="1" style="margin-bottom: 10px" required>
            <button type="submit">Сохранить</button>
        </form>
    </div>
</div>
</body>
</html>