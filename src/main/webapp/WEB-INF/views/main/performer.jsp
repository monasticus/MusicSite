<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>musicsite - ${performer.pseudonym}</title>
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

<section class="ens-page border border-info">
    <div class="ens-top">
        <div class="ens-image">
            <i class="fa fa-user-circle"></i>
        </div>
        <div class="ens-basic-information">
            <div class="ens-name">
                ${performer.pseudonym}
            </div>
            <div class="ranking-rate rounded-sm border border-dark bg-success">
                <div>
                    ${performer.average}
                </div>
            </div>
            <div class="ratings"

                    <c:choose>
                        <c:when test="${empty userPerformerRating}">
                            data-user-rating='0'>
                        </c:when>
                        <c:otherwise>
                            data-user-rating=${userPerformerRating}'>
                        </c:otherwise>
                    </c:choose>

            <div class="small-inf">
                Your rating:
            </div>
            <a href="/performer/${performer.id}/setRate/1">
                <i class="tune fas fa-music" data-rating-tune="1"></i>
            </a>
            <a href="/performer/${performer.id}/setRate/2">
                <i class="tune fas fa-music" data-rating-tune="2"></i>
            </a>
            <a href="/performer/${performer.id}/setRate/3">
                <i class="tune fas fa-music" data-rating-tune="3"></i>
            </a>
            <a href="/performer/${performer.id}/setRate/4">
                <i class="tune fas fa-music" data-rating-tune="4"></i>
            </a>
            <a href="/performer/${performer.id}/setRate/5">
                <i class="tune fas fa-music" data-rating-tune="5"></i>
            </a>
            </div>
        </div>

        <div class="ens-page-categories">
            Categories:
            <c:forEach var="category" items="${performer.categories}">
                                            <span class="ens-categories"><c:out
                                                    value="${category.name}"/></span>
            </c:forEach>
        </div>
    </div>

    <div class="ens-mid">
        <div class="related-enta">
            <nav>
                <div class="nav nav-tabs" id="nav-tab" role="tablist">
                    <a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab"
                       aria-controls="nav-home" aria-selected="true">Albums</a>
                    <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" href="#nav-profile" role="tab"
                       aria-controls="nav-profile" aria-selected="false">Tracks</a>
                </div>
            </nav>
            <div class="tab-content" id="nav-tabContent">
                <div class="tab-pane show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
                    <ul class="list-group">
                        <c:forEach var="album" items="${performerAlbums}">
                            <li class="list-group-item list-group-item-primary"><a
                                    href="/album/${album.id}">${album.name}
                                (${album.yearOfPublication})</a></li>
                        </c:forEach>
                    </ul>
                </div>
                <div class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
                    <c:choose>
                        <c:when test="${empty performer.tracks}">
                            <p class="no-data">
                                Track list is empty.
                            </p>
                        </c:when>
                        <c:otherwise>
                            <ul class="list-group">
                                <c:forEach var="track" items="${performerTracks}">
                                    <li class="list-group-item list-group-item-primary"><a
                                            href="/track/${track.id}">(${track.yearOfPublication}) ${track.name}
                                    </a> <br>
                                        <span class="performers-track-album">Album: <c:out value="${track.album.name}"
                                                                                           default="---"/> </span>
                                    </li>
                                </c:forEach>
                            </ul>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>


    <div class="border border-primary bg-light">
        <c:if test="${not empty loggedUserId}">
            <div class="comment-area d-block p-2 bg-info">
                <h1>Comment</h1>
                <form:form method="post" modelAttribute="comment">

                    <div class="form-group">
                        <form:textarea path="content" rows="3" cssClass="form-control bg-light"/>
                        <form:errors path="content" cssClass="error" element="div"/>
                    </div>
                    <form:hidden path="user" value="${loggedUserId}"/>
                    <div class="d-flex flex-row-reverse">
                        <input type="submit" class="btn btn-warning" value="Comment">
                    </div>
                </form:form>
            </div>
        </c:if>
        <c:if test="${not empty performer.comments}">
            <div class="comment-area d-block p-2 bg-light">
                <c:forEach var="comment" items="${performer.comments}">
                    <div class="comment-div">
                        <span>${comment.user.username}</span>
                        <div class="comment-single bg-secondary">
                                ${comment.content}
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:if>
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