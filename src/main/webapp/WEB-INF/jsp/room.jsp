<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Регистрация</title>
</head>

<body>
<div>

<%--    <form:form method="post" modelAttribute="floorAdd">--%>
<%--        <h2>Регистрация Пользователя</h2>--%>
<%--        <div>--%>
<%--            <form:input type="number" min="1" max="20" path="numberFloor" placeholder="Номер этажа"></form:input>--%>
<%--            <form:errors path="numberFloor"></form:errors>--%>
<%--                ${floorError}--%>
<%--        </div>--%>
<%--        <div>--%>
<%--            <form:input type="number" min="1" path="numberOfRoomsPerFloor" placeholder="Количество комнат"></form:input>--%>
<%--        </div>--%>
<%--        <div>--%>
<%--            <form:input type="number" min="1" path="numberOfFreeRoomsOnTheFloor" placeholder="Кол. свободных комнат"></form:input>--%>
<%--        </div>--%>
<%--        <button type="submit" value="save">Сохранить</button>--%>
<%--    </form:form>--%>
    <div>
        <table>
            <thead>
            <th>ID</th>
            <th>Номер комнаты</th>
            <th>Количество мест</th>
            <th>Кол. свободных мест</th>
            <th>Этаж</th>

            </thead>
            <c:forEach items="${allRoom}" var="rooms">
                <tr>
                    <td>${rooms.id}</td>
                    <td>${rooms.numberRoom}</td>
                    <td>${rooms.numberOfSeatsInTheRoom}</td>
                    <td>${rooms.numberOfFreePlacesInTheRoom}</td>
                    <td>${rooms.description}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <a href="/">Главная</a>
</div>
</body>
</html>

