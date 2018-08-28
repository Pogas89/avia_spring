<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>User list</title>
    <link rel="stylesheet" href="resourses/css/style.css">
</head>
<body>
    <h2>User list</h2>
    <section>
        <table border="1" cellpadding="8" cellspacing="0">
            <thead>
            <tr>
                <th>Email</th>
                <th>Firstname</th>
                <th>Lastname</th>
            </tr>
            </thead>
            <c:forEach items="${userList}" var="user">
                <jsp:useBean id="user" scope="page" type="com.epam.ivanou.avia.model.User"/>
                <tr>
                    <td>${user.email}</td>
                    <td>${user.firstname}</td>
                    <td>${user.lastname}</td>
                </tr>
            </c:forEach>
        </table>
    </section>
<footer>Приложение Авиакомпания</footer>
</body>
</html>
