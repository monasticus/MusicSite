<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>musicsite - ${performer.pseudonym}</title>
    <%@include file="../fragments/head.html" %>
    <style>
        <%@include file="../css/style.css"%>
    </style>
</head>
<body>
<%@include file="../fragments/header.jspf" %>
<%@include file="../fragments/dashboard.jspf" %>

<section class="ens-page">
    <div class="ens-top">
        <div class="ens-image">
            <i class="glyphicon glyphicon-user"></i>
        </div>
    </div>
    <div class="ens-basic-information">
        <div class="ens-name">
            ${performer.pseudonym}
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
                <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">

                    <p>
                        albums
                    </p>
                </div>
                <div class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
                    <p>
                        tracks
                    </p>

                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>