<%--
  Created by IntelliJ IDEA.
  User: yuriy
  Date: 06.04.15
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security"
           uri="http://www.springframework.org/security/tags" %>
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
                    <td><img src="/static/avatar/${person.avatar}" width="50" height="50"/> </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <h3>The db has no people</h3>
    </c:otherwise>
</c:choose>

<security:authorize access="hasRole('ROLE_USER')">
    <security:authentication var="user" property="principal" />
    Hello, <security:authentication property="principal.name"/>!
    <img src="/static/avatar/${user.avatar}"/>
    <a href="/j_spring_security_logout"> logout </a>
</security:authorize>
</body>
</html>
