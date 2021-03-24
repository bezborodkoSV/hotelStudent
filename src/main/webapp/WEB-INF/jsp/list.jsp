<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE HTML>
<html>
<head>
    <title>Главная</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
<div>
    <table>
        <thead>
        <th>Номер этажа</th>
        <th>Номер камнаты</th>
        <%--        <th>Количество комнат</th>--%>
        <%--        <th>Кол. свободных комнат</th>--%>
        <%--        <th>Количество мест</th>--%>
        <th>Свободных мест</th>
        <th>Описание</th>
        </thead>
        <c:forEach items="${freeRoomForAll}" var="rooms">
            <tr>
                <td>${rooms.floors.numberFloor}</td>
                <td>${rooms.numberRoom}</td>
                    <%--                <td>${rooms.floors.numberOfRoomsPerFloor}</td>--%>
                    <%--                <td>${rooms.floors.numberOfFreeRoomsOnTheFloor}</td>--%>
                    <%--                <td>${rooms.numberOfSeatsInTheRoom}</td>--%>
                <td>${rooms.numberOfFreePlacesInTheRoom}</td>
                <td>${rooms.description}</td>
            </tr>
        </c:forEach>
    </table>

</div>
</body>
</html>