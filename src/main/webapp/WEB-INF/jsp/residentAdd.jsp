<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Регистрация</title>
</head>

<body>
<div>

    <form:form method="post" modelAttribute="residentForm">
        <h2>Регистрация Пользователя</h2>
        <div>
            <form:input type="text" path="surname" placeholder="Фамилия"></form:input>
            <form:errors path="surname"></form:errors>
                ${residentError}
        </div>
        <div>
            <form:input type="text" path="name" placeholder="Имя"></form:input>
        </div>
        <div>
            <form:input type="text" path="lastname" placeholder="Отчество"></form:input>
        </div>

        <div>
            <form:input type="text" path="faculty" placeholder="Факультет"></form:input>
        </div>
        <div>
            <form:input type="text" path="groupIn" placeholder="Группа"></form:input>
        </div>
        <div>
            <form:input type="text" path="phoneNumber" placeholder="Номер телефона"></form:input>
        </div>
        <div>
            <form:input type="text" path="registration" placeholder="Место регистрации"></form:input>
        </div>
        <button type="submit">Сохранить</button>
    </form:form>
    <a href="/">Главная</a>
</div>
</body>
</html>
