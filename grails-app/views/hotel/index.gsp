<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="ru" class="no-js">
<head>
    <meta name="layout" content="main"/>
    <title>Отели</title>
    <asset:stylesheet src="styleOfTable.css"/>
</head>

<body>
<div id="content" role="main">
    <div class="container">
        <section class="row colset-2-its">

            <table class="html-4">
                <thead>
                <tr>
                    <td>Наименование</td>
                    <td>Звёздность</td>
                    <td>Страна</td>
                    <td>Действия</td>
                </tr>
                </thead>
                <tbody>
                <% hotels.each { hotel -> %>
                <tr>
                    <td>
                        ${hotel.name}
                        <g:if test="${hotel.url != null}">
                            <br>
                            <a href="${hotel.getUrl()}">Перейти на сайт</a>
                        </g:if>
                    </td>
                    <td>
                        <g:each in="${(1..hotel.stars).toList()}" var="i">
                            ★
                        </g:each>
                    </td>
                    <td>
                        ${hotel.country.name}
                    </td>
                    <td>
                        <form method="post" action="/delHotel" style="margin-bottom: 10px">
                            <button style="width: 120px" name="hotelDelete" value="${hotel.getId()}" type="submit">Удалить</button>
                        </form>

                        <form action="/addHotel">
                            <button style="width: 120px" name="hotelId" value="${hotel.getId()}" type="submit">Редактировать</button>
                        </a>
                        </form>
                    </td>
                </tr>
                <% } %>
                </tbody>
            </table>

            <g:if test="${hotelsCount > max}">
                <div class="pagination">
                    <g:paginate controller="hotel"
                                action="index"
                                total="${hotelsCount}"
                                max="${max}"/>
                </div>
            </g:if>
        </section>
    </div>
</div>
</body>
</html>