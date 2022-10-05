<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Добавление страны</title>
    <asset:stylesheet src="styleOfTable.css"/>
</head>
<body>
<div id="content" role="main">
    <div class="container">
        <h1>Добавление страны</h1>
        <br>

        <form method="post" action="/addCountry">
            <br>
            <label for="h1" id="lab1">Наименование</label>
            <input type="text" name="сountryName" alt="Название страны" id="h1" style="margin-bottom: 10px">
            <br>
            <label for="h2" id="lab2">Столица</label>
            <input type="text" name="сountryCapital" alt="Столица" id="h2" max="5" min="1" style="margin-bottom: 10px">
            <button type="submit">Добавить</button>
        </form>

        <table class="html-4">
            <thead>
            <tr>
                <td>Наименование</td>
                <td>Столица</td>
                <td>Действия</td>
            </tr>
            </thead>
            <tbody>
            <% countries.each { country -> %>
            <tr>
                <td>
                    ${country.name}
                </td>
                <td>
                    ${country.capital}
                </td>
                <td>
                    <form method="post" action="/delCountry" style="margin-bottom: 10px">
                        <button name="countryDelete" value="${country.name}" type="submit">Удалить?</button>
                    </form>

                    <form action="/refactorCountry">
                        <button name="countryName" value="${country.name}" type="submit">Редактировать</button>
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