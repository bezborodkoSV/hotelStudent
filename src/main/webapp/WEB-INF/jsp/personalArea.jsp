<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE HTML>
<html>
<head>
    <title>Власний кабінет</title>
    <meta charset=UTF-8"/>
</head>
<body>
<div>
    <h1>Особистий кабінет</h1>
    <table>
        <h>Власні данні</h>
        <thead>
        <th>Користувач</th>
        <th>Прізвище</th>
        <th>Ім'я</th>
        <th>Побатькові</th>
        <th>Факультет</th>
        <th>Група</th>
        <th>Номер телефону</th>
        <th>Місце регістр.</th>
        <th>Номер кімнати</th>
        <th>номер поверху</th>
        </thead>
        <c:forEach items="${userInfo}" var="resident">
            <tr>
                <td>${resident.users.username}</td>
                <td>${resident.surname}</td>
                <td>${resident.name}</td>
                <td>${resident.lastname}</td>
                <td>${resident.faculty}</td>
                <td>${resident.groupIn}</td>
                <td>${resident.phoneNumber}</td>
                <td>${resident.registration}</td>
                <td>${resident.rooms.numberRoom}</td>
                <td>${resident.rooms.floors.numberFloor}</td>
                <td>
                    <c:forEach items="${resident.users.roles}" var="role">${role.name}; </c:forEach>
                </td>
            </tr>
        </c:forEach>
    </table>

    <table>
        <h>Заявки на заселення</h>
        <thead>
        <th>ФІО</th>
        <th>Факультет</th>
        <th>Група</th>
        <th>Номер телефону</th>
        <th>Номер кімн.</th>
        <th>Дата подання</th>
        <th>Дата перегляду</th>
        <th>Статус</th>
        </thead>
        <c:forEach items="${userApplicatonsList}" var="applications">
            <tr>
                <td>${applications.nameSurnameLastname}</td>
                <td>${applications.faculty}</td>
                <td>${applications.groupIn}</td>
                <td>${applications.phoneNumber}</td>
                <td>${applications.rooms.numberRoom}</td>
                <td>${applications.creationDate}</td>
                <td>${applications.dateOfChange}</td>
                <td>${applications.status}</td>
            </tr>
        </c:forEach>
    </table>
<a href="/first">Back</a>
</div>
</body>
</html>
