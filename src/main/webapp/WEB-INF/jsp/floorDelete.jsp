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
    <div>
        <table>
            <thead>
            <th>ID</th>
            <th>Номер этажа</th>
            <th>Количество комнат</th>
            <th>Кол. свободных комнат</th>
            </thead>
            <c:forEach items="${allFloors}" var="floors">
                <tr>
                    <td>${floors.id}</td>
                    <td>${floors.numberFloor}</td>
                    <td>${floors.numberOfRoomsPerFloor}</td>
                    <td>${floors.numberOfFreeRoomsOnTheFloor}</td>
        <td>
               <form action="${pageContext.request.contextPath}/floorDelete" method="post">
               <input type="hidden" name="floorId" value="${floors.id}"/>
               <input type="hidden" name="action" value="delete"/>
               <button type="submit">Delete</button>
        </form>
        </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <a href="/">Главная</a>
</div>
</body>
</html>

