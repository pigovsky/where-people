<%--
  Created by IntelliJ IDEA.
  User: yuriy
  Date: 06.04.15
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<c:choose>
    <c:when test="${!empty people}">
        <h3>People</h3>
        <table class="table table-bordered table-striped">
            <thead>
            <tr>
                <th>Id</th>
                <td>Username</td>
                <th>Name</th>
                <th>Avatar</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${people}" var="person">
                <tr>
                    <td>${person.id}</td>
                    <td>${person.username}</td>
                    <td>${person.name}</td>
                    <td><img src="/static/avatar/${person.avatar}"/> </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <h3>The db has no persons</h3>
    </c:otherwise>
</c:choose>
</body>
</html>
