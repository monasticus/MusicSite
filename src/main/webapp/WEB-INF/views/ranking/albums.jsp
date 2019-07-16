<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>musicsite - albums</title>
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

<c:if test="${not empty loggedUserId}">
    <%@include file="../fragments/dashboard.jspf" %>
</c:if>


<section class="ranking-page">
    <h1 class="ranking-ens-type">Albums</h1>


    <c:choose>
        <c:when test="${empty albums}">

            <p class="no-data">
                Albums list is empty.
            </p>
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

        </c:when>
        <c:otherwise>
            <table class="ranking table table-hover">
                <tbody>
                <c:forEach var="album" items="${albums}" varStatus="nums">
                    <tr class="rangking-item">
                        <th class="ranking-ordering-num" scope="row"><div>${nums.count}</div></th>

                        <td class="ranking-ens-name">

                            <a href="/album/${album.id}">
                                    ${album.name}
                            </a>

                        </td>
                        <td class="ranking-ens-average"><div>${album.average}</div></td>

                        <c:if test="${not empty capo}">
                            <td class="admin-column">
                                <button class="admin-options" onclick="document.location.href='/adm/album/remove/${album.id}'">Remove</button> <br>
                                <button class="admin-options" onclick="document.location.href='/adm/album/edit/${album.id}'">Edit</button>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>


                </tbody>
            </table>
        </c:otherwise>
    </c:choose>


</section>


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