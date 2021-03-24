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
    ${forAdmin}
<form:form method="post" modelAttribute="floorAdd">
    <h2>Додавання поверхів</h2>
    <div>
    <form:input type="number" min="1" path="numberFloor" placeholder="Номер этажа"></form:input>
    <form:errors path="numberFloor"></form:errors>
            ${floorError}
    </div>
    <div>
    <form:input type="number" min="1" path="numberOfRoomsPerFloor" placeholder="Количество комнат"></form:input>
    </div>
    <div>
    <form:input type="number" min="1" path="numberOfFreeRoomsOnTheFloor" placeholder="Кол. свободных комнат"></form:input>
    </div>
    <button type="submit" value="save">Сохранить</button>
    </form:form>
<%--    <form:form method="post" modelAttribute="allFloorsForm">--%>
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
    <a href="/admin">Back</a>
</div>
</body>
</html>
