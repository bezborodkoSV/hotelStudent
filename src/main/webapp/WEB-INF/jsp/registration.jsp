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

        <form:form method="post" modelAttribute="userForm">
        <h2>Регистрация</h2>
        <div>
            <form:input type="text" path="username" placeholder="Username"
                        autofocus="true"></form:input>
            <form:errors path="username"></form:errors>
                ${usernameError}
        </div>
        <div>
            <form:input type="password" path="password" placeholder="Password"></form:input>
        </div>
        <div>
            <form:input type="password" path="passwordConfirm"
                        placeholder="Confirm your password"></form:input>
            <form:errors path="password"></form:errors>
                ${passwordError}
        </div>
            <h2>Личные данные</h2>
            <div>
                <input type="text" name="name" placeholder="Имя">
            </div>
            <div>
                <input type="text" name="lastname" placeholder="Отчество">
            </div>
            <div>
                <input type="text" name="surname" placeholder="Фамилия">
            </div>
            <div>
                <input type="text" name="faculty" placeholder="Факультет">
            </div>
            <div>
                <input type="text" name="groupIn" placeholder="Группа">
            </div>
            <div>
                <input type="text" name="phoneNumber" placeholder="Номер телефона">
            </div>
            <div>
                <input type="text" name="registration" placeholder="Место регистрации">
            </div>

            <button type="submit">Зарегистрироваться</button>
    </form:form>
    <a href="/">Главная</a>
</div>
</body>
</html>