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

<section class="ens-page">
    <div class="ens-top">
        <div class="ens-image">
            <i class="track-icon fas fa-music"></i>
        </div>
        <div class="ens-basic-information">
            <div class="ens-name">
                ${track.name}
            </div>

            <div class="ens-average">
                <p>
                    ${track.average}
                </p>
            </div>

            <div class="ens-performer">
                <a href="/performer/${track.performer.id}">${track.performer.pseudonym}</a>
            </div>

            <div class="ratings"

                    <c:choose>
                        <c:when test="${empty userTrackRating}">
                            data-user-rating='0'>
                        </c:when>
                        <c:otherwise>
                            data-user-rating=${userTrackRating}'>
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

        <div class="ens-page-categories">
            Category:
            <span class="ens-categories">
            <c:out value="${track.category.name}"/>
        </span>
        </div>
    </div>


    <div class="ens-mid">
        <iframe width="896" height="500" src="https://www.youtube.com/embed/${trackHyperlink}" frameborder="0"
                allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture"
                allowfullscreen></iframe>
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