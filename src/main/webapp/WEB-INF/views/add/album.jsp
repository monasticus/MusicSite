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

        *Year: <br>
        <form:input path="yearOfPublication"/>
        <form:errors path="yearOfPublication" cssClass="error" element="div"/><br>

        Performer: <br>
        <form:input path="performer"/>
        <form:errors path="performer" cssClass="error" element="div"/><br>
        <a href="/add/performer" class="add-performer"> add performer</a>
        <p class="form-information">
            <c:out value="*not necessary to save the album"/>
        </p>
        <br><input type="submit" value="Save album">

    </form:form>
</div>
</body>
</html>