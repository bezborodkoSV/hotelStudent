<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Новости</title>
</head>
<body>
<div>
    <h2>Новости</h2>
    <table>
        <thead>
        <th>Номер этажа</th>
<%--        <th>Количество комнат</th>--%>
<%--        <th>Кол. свободных комнат</th>--%>
        <th>Номер камнаты</th>
<%--        <th>Количество мест</th>--%>
        <th>Свободных мест</th>
        <th>Описание</th>
        </thead>
        <c:forEach items="${freeRoomForAuthorized}" var="rooms">
            <tr>
                <td>${rooms.floors.numberFloor}</td>
<%--                <td>${rooms.floors.numberOfRoomsPerFloor}</td>--%>
<%--                <td>${rooms.floors.numberOfFreeRoomsOnTheFloor}</td>--%>
                <td>${rooms.numberRoom}</td>
<%--                <td>${rooms.numberOfSeatsInTheRoom}</td>--%>
                <td>${rooms.numberOfFreePlacesInTheRoom}</td>
                <td>${rooms.description}</td>
            </tr>
        </c:forEach>
    </table>
    <a href="/">Главная</a>
    <a href="/applicationsForAccommodation">Заявка на заселение</a>
</div>
</body>
</html>
