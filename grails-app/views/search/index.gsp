<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Отели</title>
    <asset:stylesheet src="styleOfTable.css"/>
</head>

<body>
<content tag="nav">
    <li class="dropdown" style="margin-right: 20px">
        <p style="color: white" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
           aria-expanded="false">Страны <span class="caret"></span></p>
        <ul class="dropdown-menu">
            <g:form controller="country" action="index">
                <button style="background: transparent; border: transparent; color: white;" type="submit">Список стран</button>
            </g:form>
            <g:form controller="country" action="create">
                <button style="background: transparent; border: transparent; color: white;" type="submit">Добавить страну</button>
            </g:form>
        </ul>
    </li>
    <li class="dropdown" style="margin-right: 100px">
        <p style="color: white" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
           aria-expanded="false">Отели <span class="caret"></span></p>
        <ul class="dropdown-menu">
            <g:form controller="hotel" action="index">
                <button style="background: transparent; border: transparent; color: white;" type="submit">Список отелей</button>
            </g:form>
            <g:form controller="hotel" action="create">
                <button style="background: transparent; border: transparent; color: white;" type="submit">Создать отель</button>
            </g:form>
        </ul>
    </li>
</content>

<div class="svg" role="presentation">
    <div class="grails-logo-container">
        <asset:image src="logo.png" class="grails-logo"/>
    </div>
</div>

<div id="content" role="main">
    <div class="container">
        <section class="row colset-2-its">
            <h1>Добро пожаловать</h1>

            <p>
                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla convallis dictum erat a ornare.
                Mauris lacinia, nunc eu ultricies lobortis, lorem lectus hendrerit leo, vel consequat leo eros in leo.
                Morbi tincidunt mi nisi.
            </p>

            <br>

            <form action="/" style="margin-top: 20px; margin-bottom: 20px">
                <div style="display: flex; justify-content: center;">
                    <h3 style="margin-right: 10px">Страна</h3>
                    <select name="selectCountry" style="margin-right: 50px">
                        <option selected="selected">
                            Все
                        </option>
                        <% countries.each { country -> %>
                        <option>${country.getName()}</option>
                        <% } %>
                    </select>

                    <h3 style="margin-right: 10px">Название отеля</h3>
                    <input type="text" name="hotelName" style="margin-right: 30px" value="${currentHot}">
                    <button type="submit">Найти</button>
                </div>
            </form>

            <br>

            <g:if test="${hotelCount > 0}">
                <table class="html-4">
                    <thead>
                    <tr>
                        <td>Наименование</td>
                        <td>Звёздность</td>
                    </tr>
                    </thead>
                    <tbody>
                    <% hotels.each { hotel -> %>
                    <tr>
                        <td>
                            ${hotel.name}
                            <g:if test="${hotel.url != null}">
                                <br>
                                <a href="${hotel.getUrl()}" target="_blank" rel="noopener"
                                   data-wpel-link="internal">Перейти на сайт</a>
                            </g:if>
                        </td>
                        <td>
                            <g:each in="${(1..hotel.stars).toList()}" var="i">
                                ★
                            </g:each>
                        </td>
                    </tr>
                    <% } %>
                    <tr>
                        <td></td>
                        <td>Всего записей: ${hotelCount}</td>
                    </tr>
                    </tbody>
                </table>

                <div class="pagination">
                    <g:paginate
                            controller="search"
                            action="index"
                            total="${hotelCount}"
                            max="${postOnPage}"
                            params="[selectCountry: (currentCountry != null) ? currentCountry.getName() : 'Все', hotelName: currentHot]"/>
                </div>
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
