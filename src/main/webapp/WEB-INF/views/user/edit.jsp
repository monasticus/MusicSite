<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>musicsite - edit user</title>
    <%@include file="../fragments/head.html"%>
    <style>
        <%@include file="/resources/css/style.css"%>
    </style>
</head>
<body>
<%@include file="../fragments/header.jspf"%>
<div class="container register-form">
    <h1>Register</h1>
    <form:form method="post" modelAttribute="user">

        Username: <br>
        <form:input path="username"/>
        <form:errors path="username" cssClass="error" element="div"/><br>

        First Name: <br>
        <form:input path="firstName"/>
        <form:errors path="firstName" cssClass="error" element="div"/><br>

        Email: <br>
        <form:input path="email"/>
        <form:errors path="email" cssClass="error" element="div"/><br>

        Enter the password: <br>
        <form:password path="tempPassword"/>
        <form:errors path="tempPassword" cssClass="error" element="div"/><br>

        <br><input type="submit" value="Update">

    </form:form>
</div>
</body>
</html>