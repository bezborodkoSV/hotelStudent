<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">

    <title>Log in with your account</title>
<%--    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">--%>
</head>

<body>
<div>
    <table>
        <thead>
        <th>UserName</th>
        <th>Прізвище</th>
        <th>Ім'я</th>
        <th>По батькові</th>
        <th>Факультет</th>
        <th>Група</th>
        <th>Номер телефону</th>
        <th>Місце регістрації</th>
        </thead>
        <c:forEach items="${allResidents}" var="residents">
            <tr>
                <td>${residents.users.username}</td>
                <td>${residents.surname}</td>
                <td>${residents.name}</td>
                <td>${residents.lastname}</td>
                <td>${residents.faculty}</td>
                <td>${residents.groupIn}</td>
                <td>${residents.phoneNumber}</td>
                <td>${residents.registration}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/admin" method="post">
                        <input type="hidden" name="residentUserId" value="${residents.users.id}"/>
                        <input type="hidden" name="action" value="delete"/>
                        <button type="submit">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="/">Главная</a>
    <a href="/floors">Этажи</a>
    <a href="/room">Комнаты</a>
    <a href="/applications">Заявки</a>
</div>
</body>
</html>

