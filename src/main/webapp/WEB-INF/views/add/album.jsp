<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>musicsite - add album</title>
    <%@include file="../fragments/head.html"%>
    <style>
        <%@include file="../css/style.css"%>
    </style>
</head>
<body>
<%@include file="../fragments/header.jspf"%>
<div class="container register-form">
    <h1>New album</h1>
    <c:choose>
        <c:when test="${duplicate == true}">
            <p class="error"><c:out value="Album already exists in database."/> </p>
        </c:when>
        <c:when test="${success == true}">
            <p class="success">
                <span><c:out value="Album has been successfully added."/></span>
                <br>
                <span><c:out value="Thank You!"/> </span>
            </p>
        </c:when>
    </c:choose>

    <form:form method="post" modelAttribute="album">

        Name: <br>
        <form:input path="name"/>
        <form:errors path="name" cssClass="error" element="div"/><br>

        Year (YYYY): <br>
        <form:input path="yearOfPublication" data-toggle="tooltip" data-placement="top" title="Must be: YYYY"/>
        <form:errors path="yearOfPublication" cssClass="error" element="div"/><br>

        Performer: <br>
        <input name="performerName"/><br>
        <c:if test="${emptyPerformerName == true}">
            <div class="error"> The field cannot be empty </div><br>
        </c:if>
        <c:if test="${performerDoesNotExists == true}">
            <div class="error"> The database does not contain such a performer.<br>
                If you want, first add the performer
            </div><br>
        </c:if>

        <form:select path="categories"
                     items="${categories}"
                     itemLabel="name"
                     itemValue="id"
                     multiple="true"/>
        <form:errors path="categories" cssClass="error" element="div"/><br>

        <br><input type="submit" value="Save album">

    </form:form>

    <ul class="nav justify-content-center">
        <li class="nav-item">
            <a class="nav-link active" href="/add/track">add track</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" href="/add/performer">add performer</a>
        </li>
    </ul>
</div>
</body>
</html>