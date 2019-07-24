<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>musicsite - add track</title>
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
<section class="ens-page container border border-succes bg-light">
    <h1 class="heading">Add tracks to album <c:out value="${albumName}" default=""/> </h1>

    <div class="categories-form">
        <div class="numbers">Number of tracks:</div>
        <form method="post">
            <div class="form-group">
                <div class="form-check form-check-inline">

                    <select name="counter">
                        <c:forEach var="num" begin="1" end="35">
                            <option value="${num}">${num}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <input type="submit" value="Generate form" class="btn btn-outline-primary">
        </form>
    </div>
    <c:if test="${not empty counter}">
        <div class="album-add-tracks">
        <form method="post">
        <c:forEach var="num" begin="1" end="${counter}">
            <span class="ordinal-number"> ${num} </span>
            <input type="text" name="trackName">
            <select name="category">
                <c:forEach var="category" items="${categories}">
                    <option value="${category.name}">${category.name}</option>
                </c:forEach>
            </select><br>
        </c:forEach>
            <br><input type="submit" value="Save" class="btn btn-primary">
        </form>
        </div>
<%--        <c:if test="${not empty performers}">--%>
<%--            <h1 class="ens-type">Performers</h1>--%>
<%--            <%@include file="../fragments/performers-table.jspf" %>--%>
<%--        </c:if>--%>
<%--        <c:if test="${not empty albums}">--%>
<%--            <h1 class="ens-type">Albums</h1>--%>
<%--            <%@include file="../fragments/albums-table.jspf" %>--%>
<%--        </c:if>--%>
<%--        <c:if test="${not empty tracks}">--%>
<%--            <h1 class="ens-type">Tracks</h1>--%>
<%--            <%@include file="../fragments/tracks-table.jspf" %>--%>
<%--        </c:if>--%>

    </c:if>
</section>

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