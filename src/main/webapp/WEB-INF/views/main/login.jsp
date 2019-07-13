<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>musicsite - login</title>
    <%@include file="../fragments/head.html"%>
    <link rel="stylesheet" href="/WEB-INF/css/style.css">
    <style>
        <%@include file="../css/style.css"%>
    </style>
</head>
<body>
<%@include file="../fragments/header.jspf"%>
<div class="container login-form">
    <h1>Login</h1>
    <c:if test="${correct == false}">
        <p class="error">Data is not correct!</p>
    </c:if>
    <form method="post">
        Email: <br>
        <input type="email" name="email" required/><br>
        Password: <br>
        <input type="password" name="password" required/><br>

        <br><input type="submit" value="Log In">
    </form>
        <span> <a class="register" href="/register"> Register </a></span>

</div>
</body>
</html>