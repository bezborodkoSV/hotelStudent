<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Регистрация</title>
</head>

<body>
<div>
</form>
<%--    Форма для создания комнаты--%>
    <form method="post" action="addRoom">
        <form:form modelAttribute="roomAdd">
        <h2>Добавление комнат</h2>
        <div>
            <form:input type="number" path="numberRoom" placeholder="Номер комнаты"></form:input>
            <form:errors path="numberRoom"></form:errors>
                ${roomError}
                ${floorError}
        </div>
        <div>
            <form:input type="number" path="numberOfSeatsInTheRoom" placeholder="Количество мест"></form:input>
        </div>
        <div>
            <form:input type="number" path="numberOfFreePlacesInTheRoom" placeholder="Кол. свободных мест"></form:input>
        </div>
            <div>
                <form:input type="text" path="description" placeholder="Описание"></form:input>
            </div>
            <div>
                <input type="number" name="numberFloor" placeholder="номер этажа">
            </div>
            <button type="submit" value="save">Сохранить</button>
        </form:form>
<%--    Форма для создания комнаты--%>

<%--       вывод таблицы комнат     --%>
    <div>
        <table>
            <thead>
            <th>ID</th>
            <th>Номер комнаты</th>
            <th>Количество мест</th>
            <th>Кол. свободных мест</th>
            <td>Описание</td>
            <th>Этаж</th>

            </thead>
            ${forAdminMessage}
            <c:forEach items="${allRoom}" var="rooms">
                <tr>

                    <td>${rooms.id}</td>
                    <td>${rooms.numberRoom}</td>
                    <td>${rooms.numberOfSeatsInTheRoom}</td>
                    <td>${rooms.numberOfFreePlacesInTheRoom}</td>
                    <td>${rooms.description}</td>
                    <td>${rooms.floors.numberFloor}</td>
                    <td>
<%--            вывод таблицы комнат            --%>
<%--                        Форма для удаления Комнаты по текущему Id в строке--%>
                        <form action="${pageContext.request.contextPath}/room" method="post">
                            <input type="hidden" name="roomId" value="${rooms.id}"/>
                            <input type="hidden" name="action" value="delete"/>
                            <button type="submit">Delete</button>
                        </form>
<%--                        Форма для удаления Комнаты по текущему Id в строке --%>
                    </td>
<%--                    <td>--%>
<%--                        <form action="${pageContext.request.contextPath}/roomUpdate" method="post">--%>
<%--                            <input type="number" name="numberRoom" placeholder="Номер кімнати" value="${rooms.numberRoom}">--%>
<%--                            <input type="number" name="numberFloor" placeholder="Номер поверху" value="${rooms.floors.numberFloor}">--%>
<%--                            <input type="number" name="numberOfSeatsInTheRoom" placeholder="Кількість місць" value="${rooms.numberOfSeatsInTheRoom}">--%>
<%--                            <input type="number" name="numberOfFreePlacesInTheRoom" placeholder="Вільні місця" value="${rooms.numberOfFreePlacesInTheRoom}">--%>
<%--                            <input type="text" name="description" placeholder="Опис" value="${rooms.description}">--%>
<%--                            <input type="hidden" name="action" value="update"/>--%>
<%--                            <input type="hidden" name="roomId" value="${rooms.id}"/>--%>
<%--                            <button type="reset" >update</button>--%>
<%--                        </form>--%>
<%--                    </td>--%>
                </tr>
            </c:forEach>
        </table>
    </div>
    <a href="/">Главная</a>
    <a href="/admin">Назад</a>
</div>
</body>
</html>

