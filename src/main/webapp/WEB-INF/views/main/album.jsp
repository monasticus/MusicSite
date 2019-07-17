<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>musicsite - ${album.name}</title>
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


<section class="ens-page">
    <div class="ens-top">
        <div class="ens-image">
            <i class="fas fa-headphones"></i>
        </div>
        <div class="ens-basic-information">
            <div class="ens-name">
                ${album.name} (${album.yearOfPublication})
            </div>
            <div class="album-performer">
                <a href="/performer/${album.performer.id}">${album.performer.pseudonym}</a>
            </div>
            <div class="ratings"

                    <c:choose>
                        <c:when test="${empty userAlbumRating}">
                            data-user-rating='0'>
                        </c:when>
                        <c:otherwise>
                            data-user-rating=${userAlbumRating}'>
                        </c:otherwise>
                    </c:choose>

            <div class="small-inf">
                Your rating:
            </div>
            <a href="/album/${album.id}/setRate/1">
                <i class="tune fas fa-music" data-rating-tune="1"></i>
            </a>
            <a href="/album/${album.id}/setRate/2">
                <i class="tune fas fa-music" data-rating-tune="2"></i>
            </a>
            <a href="/album/${album.id}/setRate/3">
                <i class="tune fas fa-music" data-rating-tune="3"></i>
            </a>
            <a href="/album/${album.id}/setRate/4">
                <i class="tune fas fa-music" data-rating-tune="4"></i>
            </a>
            <a href="/album/${album.id}/setRate/5">
                <i class="tune fas fa-music" data-rating-tune="5"></i>
            </a>
            </div>
        </div>
        <div class="ens-average">
            <p>
                ${album.average}
            </p>
        </div>
        <div class="ens-page-categories">
            Categories:
            <c:forEach var="category" items="${album.categories}">
                                            <span class="ens-categories"><c:out
                                                    value="${category.name}"/></span>
            </c:forEach>
        </div>
    </div>

    <div class="ens-mid">
        <div class="album-tracks">
            <c:choose>
                <c:when test="${empty album.tracks}">
                    <p class="no-data">
                        Track list is empty.
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
                    <ol class="track-list">
                        <c:forEach var="track" items="${album.tracks}">
                            <li class="album-single-track"><a
                                    href="/tracks/${track.id}">${track.name}
                            </a></li>
                        </c:forEach>
                    </ol>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
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