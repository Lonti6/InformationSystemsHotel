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
        <section class="row colset-2-its">
            <h1>Список стран</h1>
            <g:if test="${countryCount > 0}">
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
                                <button style="width: 120px" name="countryDelete" value="${country.name}" type="submit">Удалить</button>
                            </form>

                            <form action="/addCountry">
                                <button style="width: 120px" name="countryName" value="${country.name}" type="submit">Редактировать</button>
                            </a>
                            </form>
                        </td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>

                <g:if test="${countryCount > max}">
                    <div class="pagination">
                        <g:paginate controller="country"
                                    action="index"
                                    total="${countryCount}"
                                    max="${max}"/>
                    </div>
                </g:if>
            </g:if>
            <g:else>
                <h1>Мы ничего не нашли(</h1>
                <asset:image src="goose.webp" class="grails-logo"
                             style="horiz-align: center; width: 350px; margin-left: auto; margin-right: auto;"/>

            </g:else>
        </section>
    </div>
</div>
</body>
</html>