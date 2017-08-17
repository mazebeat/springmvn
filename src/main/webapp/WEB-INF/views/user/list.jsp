<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>User</title>
</head>
<body>
<s:url value="/user/add" var="addURL"/>
<a href="${addURL}">Add User</a>
<table>
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Email</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${list}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.email}</td>
            <td>
                <s:url value="/user/update/${user.id}" var="updateURL"/>
                <a href="${updateURL}">Update</a>
            </td>
            <td>
                <s:url value="/user/delete/${user.id}" var="deleteURL"/>
                <a href="${deleteURL}">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
