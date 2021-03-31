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
    <h3>${pageContext.request.userPrincipal.name}</h3>
    <sec:authorize access="!isAuthenticated()">
        <h4><a href="/login">Войти</a></h4>
        <h4><a href="/registration">Зарегистрироваться</a></h4>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <h4><a href="/logout">Выйти</a></h4>
    </sec:authorize>
<a href="/personalArea">Власний кабінет</a>

    <h4><a href="/news">Новости (только пользователь)</a></h4>
    <h4><a href="/admin">Пользователи (только админ)</a></h4>
    <h4><a href="/residentAdd">resident</a></h4>
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
