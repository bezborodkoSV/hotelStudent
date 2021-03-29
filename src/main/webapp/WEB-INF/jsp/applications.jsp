<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Регистрация</title>
</head>

<body>
<div>
    </form>

        <%--    Форма для создания комнаты--%>

        <%--       вывод таблицы комнат     --%>
        <div>
            <table>
                <thead>
                <th>Id</th>
                <th>Користувач</th>
                <th>ФІО</th>
                <th>Факультет</th>
                <th>Група</th>
                <th>Номер телефону</th>
                <th>Номер кімн.</th>
                <th>Дата подання</th>

                </thead>
                ${forAdminMessage}
                <c:forEach items="${unverifiedApplications}" var="applications">
                    <tr>
                        <td>${applications.id}</td>
                        <td>${applications.users.username}</td>
                        <td>${applications.nameSurnameLastname}</td>
                        <td>${applications.faculty}</td>
                        <td>${applications.groupIn}</td>
                        <td>${applications.phoneNumber}</td>
                        <td>${applications.rooms.numberRoom}</td>
                        <td>${applications.creationDate}</td>
                            <%--                 Исправить    --%>
                        <td>${rooms.applicationsForAccommodation.nameSurnameLastname}</td>
                            <%--                 Исправить    --%>
                        <td>${rooms.floors.numberFloor}</td>
                        <td>

                                <%--                        Форма для Комнаты по текущему Id в строке--%>
                            <form action="${pageContext.request.contextPath}/applications" method="post">
                                <form:form>
                                <input type="hidden" name="applicationId" value="${applications.id}"/>
                                <input type="hidden" name="action" value="deflect"/>
                                <button type="submit">Відклонити</button>
                                </form:form>
                                <form:form>
                                    <input type="hidden" name="applicationId" value="${applications.id}"/>
                                    <input type="hidden" name="action" value="accept"/>
                                    <button type="submit">Прийняти</button>
                                </form:form>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>

<table>
            <h2>Прийнято</h2>
            <thead>
            <th>Користувач</th>
            <th>ФІО</th>
            <th>Факультет</th>
            <th>Група</th>
            <th>Номер телефону</th>
            <th>Номер кімн.</th>
            <th>Дата подання</th>
            <th>Дата відхилення</th>
            <th>Статус</th>
            </thead>
            ${forAdminMessage}
            <c:forEach items="${acceptApplications}" var="applications">
                <tr>
                    <td>${applications.users.username}</td>
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



<table>
    <h2>Відмовлено</h2>
<thead>
<th>Користувач</th>
<th>ФІО</th>
<th>Факультет</th>
<th>Група</th>
<th>Номер телефону</th>
<th>Номер кімн.</th>
<th>Дата подання</th>
<th>Дата відхилення</th>
<th>Статус</th>
</thead>
${forAdminMessage}
<c:forEach items="${deflectApplications}" var="applications">
    <tr>
    <td>${applications.users.username}</td>
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



        </div>
        <a href="/">Главная</a>
        <a href="/admin">Назад</a>
</div>
</body>
</html>
