<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Страны</title>
    <asset:stylesheet src="styleOfTable.css"/>
</head>

<body>
<div id="content" role="main">
    <div class="container">
        <h1>Добавление страны</h1>
        <br>

        <g:if test="${errorText != null}">
            <h1>Ошибка</h1>

            <h3>${errorText}</h3>
        </g:if>

        <form method="post" action="/createCountry">
            <g:if test="${countryName != null}">
                <br>
                <label style="width: 100px"  for="h4" id="lab4">Старое наименование</label>
                <input value="${countryName}" type="text" name="oldCountryName" alt="Название отеля" id="h4"
                       style="margin-bottom: 10px" readonly>
            </g:if>
            <br>
            <label style="width: 100px" for="h1" id="lab1">Наименование</label>
            <input value="${countryName == null?'':countryName}" type="text" name="сountryName" alt="Название страны" id="h1" style="margin-bottom: 10px" required>
            <br>
            <label style="width: 100px" for="h2" id="lab2">Столица</label>
            <input value="${countryCapital == null?'':countryCapital}" type="text" name="сountryCapital" alt="Столица" id="h2" max="5" min="1" style="margin-bottom: 10px"
                   required>
            <button style="margin-left: 20px" type="submit">${actionType}</button>
        </form>
    </div>
</div>
</body>
</html>