<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<html lang="ru">
<head>
    <title>Users</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Users</h2>
<a href="users?action=create">Add User</a>
<br><br>
<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th>Name</th>
        <th>E-mail</th>
        <th>Roles</th>
        <th>Action</th>
        <th>Registered</th>
        <th>&nbsp;</th>
        <th>&nbsp;</th>
    </tr>
    </thead>
    <c:forEach items="${users}" var="user">
        <jsp:useBean id="user" type="ru.javawebinar.topjava.model.User"/>
        <tr>
            <td>${user.name}</td>
            <td>${user.email}</td>
            <td>${user.roles}</td>
            <td>${user.enabled}</td>
            <td>
                    ${fn:formatDate(user.registered)}
            </td>
            <td><a href="users?action=update&id=${user.id}">Update</a></td>
            <td><a href="users?action=delete&id=${user.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>