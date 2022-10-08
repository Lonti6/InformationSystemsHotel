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
            <br>
            <label for="h1" id="lab1">Наименование</label>
            <input type="text" name="сountryName" alt="Название страны" id="h1" style="margin-bottom: 10px" required>
            <br>
            <label for="h2" id="lab2">Столица</label>
            <input type="text" name="сountryCapital" alt="Столица" id="h2" max="5" min="1" style="margin-bottom: 10px"
                   required>
            <button type="submit">Добавить</button>
        </form>
    </div>
</div>
</body>
</html>