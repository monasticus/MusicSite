<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>musicsite - add performer</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <style>
        <%@include file="/resources/css/style.css"%>
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
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>


</body>
</html>