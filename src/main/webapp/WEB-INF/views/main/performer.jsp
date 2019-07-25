<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../../../resources/css/style.css"/>
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
    <div class="ens-top bg-light">
        <div class="d-flex bd-highlight mb-3">
            <div class="ens-image p-2">
                <i class="fa fa-user-circle"></i>
            </div>
            <div class="ens-name align-self-start">
                <p>${performer.pseudonym}</p>

                <div class="small-inf">
                    Your rating:
                </div>
                <div class="ens-user-rating">
                    <div class="ratings"
                            <c:choose>
                                <c:when test="${empty userPerformerRating}">
                                    data-user-rating='0'>
                                </c:when>
                                <c:otherwise>
                                    data-user-rating='${userPerformerRating}'>
                                </c:otherwise>
                            </c:choose>


                    <a class="text-success" href="/performer/${performer.id}/setRate/1">
                        <i class="tune fas fa-music" data-rating-tune="1"></i>
                    </a>
                    <a class="text-success" href="/performer/${performer.id}/setRate/2">
                        <i class="tune fas fa-music" data-rating-tune="2"></i>
                    </a>
                    <a class="text-success" href="/performer/${performer.id}/setRate/3">
                        <i class="tune fas fa-music" data-rating-tune="3"></i>
                    </a>
                    <a class="text-success" href="/performer/${performer.id}/setRate/4">
                        <i class="tune fas fa-music" data-rating-tune="4"></i>
                    </a>
                    <a class="text-success" href="/performer/${performer.id}/setRate/5">
                        <i class="tune fas fa-music" data-rating-tune="5"></i>
                    </a>
                    </div>
                </div>

                <div class="ens-categories">
                    Categories:
                    <c:forEach var="category" items="${performer.categories}">
                        <span><c:out value="${category.name}"/></span>
                    </c:forEach>
                </div>
            </div>

            <div class="ens-top-right ml-auto p-2">


                <div class="ens-average rounded  border border-dark bg-success d-flex align-items-start">
                    <div>
                        <p>
                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${performer.average}"/>
                        </p>
                    </div>


                </div>
                <div class="user-themes d-flex align-items-center">
                    <c:choose>
                        <c:when test="${recommendation == true}">
                            <a href="/performer/${performer.id}/setRecomm" title="recommend"><i class="fas fa-bullhorn recommendation-true"></i></a>
                        </c:when>
                        <c:otherwise>
                            <a href="/performer/${performer.id}/setRecomm" title="recommend"><i class="fas fa-bullhorn"></i></a>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${favorite == true}">
                            <a href="/performer/${performer.id}/setFavorite" title="add to favorite"><i class="fas fa-heart favorite-true"></i></a>
                        </c:when>
                        <c:otherwise>
                            <a href="/performer/${performer.id}/setFavorite" title="add to favorite"><i class="far fa-heart"></i></a>
                        </c:otherwise>
                    </c:choose>


                </div>
                <div class="number-informations d-flex">
                    Ratings: ${ratingCounter}<br>
                    Recomm.: ${recommendationCounter}
                </div>
            </div>
        </div>
    </div>

    <div class="ens-mid bg-light">
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
                    <c:choose>
                        <c:when test="${empty performerAlbums}">
                            <p class="empty-track-list">
                                Album list is empty.
                            </p>
                        </c:when>
                        <c:otherwise>
                            <ul class="list-group">
                                <c:forEach var="album" items="${performerAlbums}">
                                    <li class="list-group-item list-group-item-primary d-flex mb-3">

                                        <div class="mr-auto p-2 bd-highlight">
                                            <a href="/album/${album.id}">${album.name}</a>
                                        </div>
                                        <div class="p-2 bd-highlight">
                                            <c:out value="${album.yearOfPublication}" default="---"/>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
                        </c:otherwise>

                    </c:choose>

                </div>
                <div class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
                    <c:choose>
                        <c:when test="${empty performerTracks}">
                            <p class="empty-track-list">
                                Track list is empty.
                            </p>
                        </c:when>
                        <c:otherwise>
                            <ul class="list-group">
                                <c:forEach var="track" items="${performerTracks}">
                                    <li class="list-group-item list-group-item-primary d-flex mb-3">
                                        <div class="mr-auto p-2 bd-highlight">
                                            <a href="/track/${track.id}">(${track.yearOfPublication}) ${track.name} </a>
                                        </div>
                                        <div class="p-2 bd-highlight">
                                            Album: <c:out value="${track.album.name}" default="---"/>
                                        </div>

                                    </li>
                                </c:forEach>
                            </ul>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>


    <c:if test="${not empty performer.comments || not empty loggedUserId}">
        <div class="ens-bottom bg-light">
            <div class="border border-primary bg-info">
                <c:if test="${not empty loggedUserId}">
                    <div class="comment-area d-block p-2">
                        <h1>New comment</h1>
                        <form:form method="post" modelAttribute="comment">

                            <div class="form-group">
                                <form:textarea path="content" rows="3" cssClass="form-control bg-light"/>
                                <form:errors path="content" cssClass="error" element="div"/>
                            </div>
                            <form:hidden path="user" value="${loggedUserId}"/>
                            <div class="d-flex flex-row-reverse">
                                <input type="submit" class="btn btn-warning" value="Comment" title="Comment">
                            </div>
                        </form:form>
                    </div>
                </c:if>
                <c:if test="${not empty performer.comments}">
                    <h3 class="comments-h2">Comments</h3>
                    <div class="comment-area overflow-auto d-block p-2">
                        <c:forEach var="comment" items="${performer.comments}">
                            <div class="comment-div bg-secondary">
                                <div class="comment-content">
                                        ${comment.content}
                                </div>

                                <p class="comment-username text-warning">
                                    by ${comment.user.username}
                                    <c:if test="${loggedUserId == comment.user.id}">
                                        <a href="/usr/comment-remove/${comment.id}" class="btn btn-danger btn-sm"
                                           title="Remove comment">Remove</a>
                                    </c:if>
                                </p>
                            </div>
                        </c:forEach>
                    </div>
                </c:if>
            </div>
        </div>
    </c:if>

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