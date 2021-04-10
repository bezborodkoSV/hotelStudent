<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <form th:action="@{controlResidents}" method="get" >
        <th><!--/*@thymesVar id="faculty" type="actions"*/-->
            <input type="text" name="facultyForFilter" th:value="${faculty}" class="form-control" placeholder="Факультет"></th></form>
    <form th:action="@{controlResidents}" method="get" >
        <th><!--/*@thymesVar id="groupIn" type="actions"*/-->
            <input type="text" name="groupInForFilter" th:value="${groupIn}" class="form-control" placeholder="Група"></th></form>
    <form th:action="@{controlResidents}" method="get" >
        <th><!--/*@thymesVar id="surname" type="actions"*/-->
            <input type="text" name="surnameForFilter" th:value="${surname}" class="form-control" placeholder="Прізвище"></th>
    </form>
    <title>Log in with your account</title>
<%--    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">--%>
</head>

<body>
<h>Нові користувачі</h>
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
        <c:forEach items="${unverifiedUsers}" var="users">
            <tr>
                <td>${users.username}</td>
                <td>${users.residents.surname}</td>
                <td>${users.residents.name}</td>
                <td>${users.residents.lastname}</td>
                <td>${users.residents.faculty}</td>
                <td>${users.residents.groupIn}</td>
                <td>${users.residents.phoneNumber}</td>
                <td>${users.residents.registration}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/admin" method="post">
                        <form:form>
                        <input type="hidden" name="userId" value="${users.id}"/>
                        <input type="hidden" name="action" value="accept"/>
                        <button type="submit">Прийняти</button></form:form>
                        <form:form>
                        <input type="hidden" name="userId" value="${users.id}"/>
                        <input type="hidden" name="action" value="reject"/>
                        <button type="submit">Відмовити</button>
                        </form:form>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

<h>Список студентів</h>
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
<c:forEach items="${studentUsers}" var="users">
    <tr>
    <td>${users.username}</td>
    <td>${users.residents.surname}</td>
    <td>${users.residents.name}</td>
    <td>${users.residents.lastname}</td>
    <td>${users.residents.faculty}</td>
    <td>${users.residents.groupIn}</td>
    <td>${users.residents.phoneNumber}</td>
    <td>${users.residents.registration}</td>
    </tr>
    </c:forEach>
</table>
    <a href="/">Главная</a>
    <a href="/floors">Этажи</a>
    <a href="/room">Комнаты</a>
    <a href="/applications">Заявки</a>
    <a href="/controlResidents">Управління студентами</a>
</div>
</body>
</html>

