<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>musicsite - add track</title>
    <%@include file="../fragments/head.html"%>
    <style>
        <%@include file="../css/style.css"%>
    </style>
</head>
<body>
<%@include file="../fragments/header.jspf"%>
<div class="container register-form">
    <h1>New track</h1>
    <c:choose>
        <c:when test="${duplicate == true}">
            <p class="error"><c:out value="Track already exists in database."/> </p>
        </c:when>
        <c:when test="${success == true}">
            <p class="success">
                <span><c:out value="Track has been successfully added."/></span>
                <br>
                <span><c:out value="Thank You!"/> </span>
            </p>
        </c:when>
    </c:choose>

    <form:form method="post" modelAttribute="track">

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

        Album: <br>
        <input name="albumName" placeholder="if not applicable - skip"/><br>
        <c:if test="${albumDoesNotExists == true}">
            <div class="error"> The database does not contain such an album.<br>
                If you want, first add the album.
            </div><br>
        </c:if>

        Category:<br>
        <form:select path="category"
                     items="${categories}"
                     itemLabel="name"
                     itemValue="id"/> <br>

        <br><input type="submit" value="Save track">

    </form:form>

    <ul class="nav justify-content-center">
        <li class="nav-item">
            <a class="nav-link active" href="/add/album">add album</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" href="/add/performer">add performer</a>
        </li>
    </ul>

</div>
</body>
</html>