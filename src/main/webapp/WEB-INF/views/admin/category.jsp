<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>musicsite - add category</title>
    <%@include file="../fragments/head.html"%>
    <style>
        <%@include file="../css/style.css"%>
    </style>
</head>
<body>
<%@include file="../fragments/header.jspf"%>
<%@include file="../fragments/dashboard.jspf" %>
<div class="container register-form">
    <h1>New category</h1>
    <c:choose>
       <c:when test="${duplicate == true}">
           <p class="error"><c:out value="Category already exists in database."/> </p>
       </c:when>
        <c:when test="${success == true}">
            <p class="success">
                <span><c:out value="Category has been successfully added."/></span>
                <br>
                <span><c:out value="Thank You!"/> </span>
            </p>
        </c:when>
    </c:choose>

    <form:form method="post" modelAttribute="category">

        Name: <br>
        <form:input path="name"/>
        <form:errors path="name" cssClass="error" element="div"/><br>

        <br><input type="submit" value="Save category">

    </form:form>

    <ul class="nav justify-content-center">
        <li class="nav-item">
            <a class="nav-link active" href="/add/performer">add performer</a>
        </li>
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