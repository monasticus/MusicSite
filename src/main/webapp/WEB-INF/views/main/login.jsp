<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>musicsite - login</title>
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
    <h1>Login</h1>
    <c:if test="${correct == false}">
        <p class="error">Data is not correct!</p>
    </c:if>

    <c:choose>
        <c:when test="${justConfirmed == true}">
            <div class="success">
                Thank You for registration on
                <p class="music-side">
                <span class="musicsite-music">music</span><span class="musicsite-site">site.</span> !
                </p>
                    You can log in now.
            </div>
        </c:when>
        <c:when test="${justConfirmed == false}">
            <p class="error">
                You have not confirmed your account yet. <br>
                Check your mailbox!
            </p>
        </c:when>
    </c:choose>

    <div class="success">
        Thank You for registration on
        <div class="musicsite-elem-parent">
            <p class="musicsite-elem">
                <span class="musicsite-music">music</span><span class="musicsite-site">site.</span>
            </p>
        </div>
        You can log in now.
    </div>


    <form method="post">
        <div class="form-group">
            Email: <br>
            <input type="email" name="email" class="form-control" required/>
        </div>

        <div class="form-group">
            Password: <br>
            <input type="password" name="password" class="form-control" required/>
            <p id="register-link"><a class="register" href="/register"> Register </a></p>
        </div>

        <br><input type="submit" value="Log In" class=" btn btn-primary">
    </form>


</div>

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