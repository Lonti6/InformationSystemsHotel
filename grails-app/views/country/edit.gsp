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

        <h1 style="color: red">${flash.message}</h1>

        <g:form method="post" url="[controller: 'country', action: 'save']">
            <input name="id" value="${country.id ?: ''}" type="hidden">
            <label style="width: 100px" for="h1" id="lab1">Наименование</label>
            <input value="${country.name ?: ''}" type="text" name="name"
                   alt="Название страны" id="h1" style="margin-bottom: 10px" required>
            <br>
            <label style="width: 100px" for="h2" id="lab2">Столица</label>
            <input value="${country.capital ?: ''}" type="text" name="capital"
                   alt="Столица" id="h2" max="5" min="1" style="margin-bottom: 10px"
                   required>
            <button style="margin-left: 20px" type="submit">Добавить</button>
        </g:form>
    </div>
</div>
</body>
</html>