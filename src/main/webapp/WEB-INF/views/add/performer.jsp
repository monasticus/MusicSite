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
<div class="container register-form">
    <h1>New performer</h1>
    <c:choose>
       <c:when test="${duplicate == true}">
           <p class="error"><c:out value="Performer already exists in database."/> </p>
       </c:when>
        <c:when test="${success == true}">
            <p class="success"><c:out value="Performer has been successfully added.<br>Thank You!"/> </p>
        </c:when>
    </c:choose>

    <form:form method="post" modelAttribute="performer">

        Pseudonym / Group name: <br>
        <form:input path="pseudonym"/>
        <form:errors path="pseudonym" cssClass="error" element="div"/><br>

        First Name: <br>
        <form:input path="firstName"/>
        <form:errors path="firstName" cssClass="error" element="div"/><br>

        Last Name: <br>
        <form:input path="lastName"/>
        <form:errors path="lastName" cssClass="error" element="div"/><br>

        Password: <br>
        <form:password path="tempPassword"/>
        <form:errors path="tempPassword" cssClass="error" element="div"/><br>

        <br><input type="submit" value="Register">

    </form:form>
</div>
</body>
</html>