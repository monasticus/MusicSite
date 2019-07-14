<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>musicsite - add performer</title>
    <%@include file="../fragments/head.html"%>
    <style>
        <%@include file="../css/style.css"%>
    </style>
</head>
<body>
<%@include file="../fragments/header.jspf"%>
<%@include file="../fragments/dashboard.jspf" %>
<div class="container register-form">
    <h1>New performer</h1>
    <c:choose>
       <c:when test="${duplicate == true}">
           <p class="error"><c:out value="Performer already exists in database."/> </p>
       </c:when>
        <c:when test="${success == true}">
            <p class="success">
                <span><c:out value="Performer has been successfully added."/></span>
                <br>
                <span><c:out value="Thank You!"/> </span>
            </p>
        </c:when>
    </c:choose>

    <form:form method="post" modelAttribute="performer">

        Pseudonym / Group name: <br>
        <form:input path="pseudonym"/>
        <form:errors path="pseudonym" cssClass="error" element="div"/><br>

        *First Name: <br>
        <form:input path="firstName"/>
        <form:errors path="firstName" cssClass="error" element="div"/><br>

        *Last Name: <br>
        <form:input path="lastName"/>
        <form:errors path="lastName" cssClass="error" element="div"/><br>

        <p class="form-information">
            <c:out value="*not necessary to save performer"/>
        </p>
        <br><input type="submit" value="Save performer">

    </form:form>

    <ul class="nav justify-content-center">
        <li class="nav-item">
            <a class="nav-link active" href="/add/album">add album</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" href="/add/track">add track</a>
        </li>
    </ul>
</div>
</body>
</html>