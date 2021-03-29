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
    <h1>${numberOfApplicationsExceeded}</h1>
    <form:form method="post" modelAttribute="applicationsForAccommodationForm">
        <h2>Add applications</h2>
        <div>
            <form:input type="text" path="nameSurnameLastname" placeholder="Имя Фамилия Отчество"></form:input>
<%--            <form:errors path="nameSurnameLastname"></form:errors>--%>
<%--                ${nameError}--%>
        </div>
        <div>
            <form:input type="text" path="faculty" placeholder="Факкультет"></form:input>
        </div>
        <div>
            <form:input type="text" path="groupIn" placeholder="Группа"></form:input>
        </div>

        <div>
            <form:input type="text" path="phoneNumber" placeholder="Номер телефона"></form:input>
        </div>
        <div>
            <input type="number" name="numberRoom" placeholder="Номер комнаты">
        </div>
        <button type="submit">Сохранить</button>
    </form:form>
    <a href="/">Главная</a>
</div>
</body>
</html>
