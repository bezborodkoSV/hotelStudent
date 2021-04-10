<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Resident control</title>
</head>

<body>
<%--<% Long numberRoomForFilter = request.getParameterNames();  %>--%>
<%--action="/filter"--%>
<%--action=""--%>
<%--action="controlResidents?numberRoomForFilter=${numberRoomForFilter}--%>
<%--<form method="get">--%>
<%--<td><form:form>--%>
<%--    <input type="number" name="numberRoomForFilter" placeholder="номер кімнати" >--%>
<%--    <% Object numberRoomForFilter =<input type="number" name="numberRoomForFilter" placeholder="номер кімнати" >;--%>
<%--        pageContext.setAttribute("allStudent", numberRoomForFilter);%>--%>
<%--    <button type="submit">Пошук</button>--%>
<%--</form:form></td>--%>
<%--</form>--%>
<%--<div>--%>
<form th:action="@{controlResidents}" method="get" >
    <!--/*@thymesVar id="numberRoom" type="actions"*/-->
    <th><input type="number" name="numberRoomForFilter" th:value="${numberRoom}" class="form-control" placeholder="Номер кімнати"></th>
<%--    <th><!--/*@thymesVar id="numberFloor" type="actions"*/-->--%>
<%--    <input type="number" name="numberFloorForFilter" th:value="${numberFloor}" class="form-control" placeholder="Номер поверху"></th>--%>
<%--    <th><!--/*@thymesVar id="faculty" type="actions"*/-->--%>
<%--    <input type="text" name="facultyForFilter" th:value="${faculty}" class="form-control" placeholder="Факультет"></th>--%>
<%--    <th>--%>
<%--    <!--/*@thymesVar id="groupIn" type="a"*/-->--%>
<%--    <input type="text" name="groupInForFilter" th:value="${groupIn}" class="form-control" placeholder="Група"></th>--%>
<%--    <th><!--/*@thymesVar id="surname" type="actions"*/-->--%>
<%--    <input type="text" name="surnameForFilter" th:value="${surname}" class="form-control" placeholder="Прізвище"></th>--%>
</form>
<form th:action="@{controlResidents}" method="get" >
        <th><!--/*@thymesVar id="surname" type="actions"*/-->
        <input type="text" name="surnameForFilter" th:value="${surname}" class="form-control" placeholder="Прізвище"></th>
</form>
<form th:action="@{controlResidents}" method="get" >
    <th><!--/*@thymesVar id="faculty" type="actions"*/-->
        <input type="text" name="facultyForFilter" th:value="${faculty}" class="form-control" placeholder="Факультет"></th></form>

<form th:action="@{controlResidents}" method="get" >
    <th><!--/*@thymesVar id="numberFloor" type="actions"*/-->
        <input type="number" name="numberFloorForFilter" th:value="${numberFloor}" class="form-control" placeholder="Номер поверху"></th>
</form>
<form th:action="@{controlResidents}" method="get" >
    <th><!--/*@thymesVar id="groupIn" type="actions"*/-->
        <input type="text" name="groupInForFilter" th:value="${groupIn}" class="form-control" placeholder="Група"></th></form>
<div>
    <table>
        <thead>
        <th>ID</th>
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
        <td>${roomError}</td>
        <c:forEach items="${allStudent}" var="residents">
            <tr>
                <td>${residents.id}</td>
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
