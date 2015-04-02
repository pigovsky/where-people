<!doctype html>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="utf-8">
    <link href="http://twitter.github.io/bootstrap/assets/css/bootstrap.css" rel="stylesheet">
    <link href="http://twitter.github.io/bootstrap/assets/css/bootstrap-responsive.css" rel="stylesheet">
</head>
<body>
    <div class="containter">
        <div class="row">
            <div class="span8 offset2">
                <h1>Users</h1>
                <form:form method="post" action="add" modelAttribute="person" class="form-horizontal" enctype="multipart/form-data">
                    <div class="control-group">
                        <form:label  cssClass="control-label" path="name">Name:</form:label>
                        <div class="controls">
                            <form:input path="name"/>
                            <form:errors path="name"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <form:label  cssClass="control-label" path="username">Username:</form:label>
                        <div class="controls">
                            <form:input path="username"/>
                            <form:errors path="username"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <form:label  cssClass="control-label" path="password">Password:</form:label>
                        <div class="controls">
                            <form:input path="password"/>
                            <form:errors path="password"/>
                        </div>
                    </div>
                    <div class="control-group">
                        Avatar:
                        <div class="controls">
                            <input type="file" name="avatarImage"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <div class="controls">
                            <input type="submit" value="Add Person" class="btn"/>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
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

    </div>


</body>
</html>