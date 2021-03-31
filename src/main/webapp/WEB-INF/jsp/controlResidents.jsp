<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Resident control</title>
</head>

<body>
<%--<form method="post">--%>
<%--    <td><form:form>--%>
<%--        <input type="number" name="numberRoom" placeholder="номер кімнати" >--%>
<%--        <button type="submit">Пошук</button>--%>
<%--    </form:form></td>--%>
<%--</form>--%>
<div>
    <table>
        <thead>
        <th>UserName</th>
        <th>ФІО</th>
<%--        <th>Ім'я</th>--%>
<%--        <th>По батькові</th>--%>
        <th>Факультет</th>
        <th>Група</th>
        <th>Номер телефону</th>
        <th>Місце регістрації</th>
        <th>Поверх</th>
        </thead>
        <td>${roomFulError}</td>
        <c:forEach items="${allStudent}" var="residents">
            <tr>
                <td>${residents.users.username}</td>
                <td>${residents.surname} ${residents.name} ${residents.lastname}</td>
<%--                <td></td>--%>
<%--                <td></td>--%>
                <td>${residents.faculty}</td>
                <td>${residents.groupIn}</td>
                <td>${residents.phoneNumber}</td>
                <td>${residents.registration}</td>
                <td>${residents.rooms.floors.numberFloor}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/controlResidents" method="post">
                        <td><form:form>
                        <input type="number" name="numberRoom" value="${residents.rooms.numberRoom}" placeholder="номер кімнати" >
                        <input type="hidden" name="residentId" value="${residents.id}"/>
                        <input type="hidden" name="action" value="moveTo"/>
                        <button type="submit">Заселити</button>
                        </form:form></td>
                        <td><form:form>
                            <input type="hidden" name="residentId" value="${residents.id}"/>
                            <input type="hidden" name="action" value="moveOut"/>
                            <button type="submit">Виселити</button>
                        </form:form></td>
                    </form>

                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="/">Главная</a>
    <a href="/admin">Назад</a>
</div>
</body>
</html>
