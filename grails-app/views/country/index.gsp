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

        <g:paginate controller="country"
                    action="index"
                    total="${countryCount}"
                    max="${max}"/>

    </div>
</div>
</body>
</html>