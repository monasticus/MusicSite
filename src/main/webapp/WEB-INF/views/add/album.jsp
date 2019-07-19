<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>musicsite - add album</title>
    <!-- Bootstrap -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!--FONT AWESOME-->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
          integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
    <style>
        <%@include file="/resources/css/style.css"%>
    </style>
</head>
<body>
<%@include file="../fragments/header.jspf" %>
<div class="container my-form">
    <h1>New album</h1>
    <c:choose>
        <c:when test="${duplicate == true}">
            <p class="error"><c:out value="Album already exists in database."/></p>
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

        <div class="form-group">
            *Name: <br>
            <form:input path="name" cssClass="form-control"/>
            <form:errors path="name" cssClass="error" element="div"/><br>
        </div>
        <div class="form-group">
            Year (YYYY): <br>
            <form:input path="yearOfPublication" cssClass="form-control" data-toggle="tooltip" data-placement="top"
                        title="Must be: YYYY"/>
            <form:errors path="yearOfPublication" cssClass="error" element="div"/><br>
        </div>
        <div class="form-group">
            Performer: <br>
            <input name="performerName" class="form-control"
                   value="<c:out value="${loadPerformerName}" default=""/>"/><br>
        </div>
        <c:if test="${emptyPerformerName == true}">
            <div class="error"> The field cannot be empty</div>
            <br>
        </c:if>
        <c:if test="${performerDoesNotExists == true}">
            <div class="error"> The database does not contain such a performer.<br>
                If you want, first add the performer
            </div>
            <br>
        </c:if>


        <form:select path="categories"
                     items="${categories}"
                     itemLabel="name"
                     itemValue="id"
                     multiple="true"
                     cssClass="form-control"/>
        <form:errors path="categories" cssClass="error" element="div"/><br>
        <p class="form-information">
            <c:out value="*can be \"Various Artists\""/>
        </p>

        <br><input type="submit" value="Save" class=" btn btn-primary">

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

<%@include file="../fragments/footer.jspf" %>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>

<script type="text/javascript" src="/resources/js/Ens-Pages.js"></script>
</body>
</html>