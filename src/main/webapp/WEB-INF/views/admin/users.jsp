<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>musicsite - propositions</title>
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

<section class="ens-page border border-info bg-dark">
    <h1 class="ranking-ens-type">Users</h1>

    <div class="propositions-table">
        <table class="table table-hover">
            <thead>
            <tr>
                <th scope="col"> #</th>
                <th scope="col"> Username</th>
                <th scope="col"> First Name</th>
                <th scope="col"> Email</th>
                <th scope="col"> Confirmed</th>
                <th scope="col"> Action</th>
            </tr>
            </thead>

            <tbody>

            <c:forEach var="user" items="${users}" varStatus="nums">
                <tr>
                    <th scope="row"> ${nums.count}</th>
                    <td> ${user.username} </td>
                    <td> ${user.firstName} </td>
                    <td> ${user.email} </td>
                    <td>
                        <c:choose>
                            <c:when test="${user.confirmed}">
                                <span class="success">Yes</span>
                            </c:when>
                            <c:otherwise>
                                <span class="error">No</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <a href="/adm/user/remove/${user.id}" class="btn btn-danger"
                           data-toggle="modal"
                           data-target="#deleteModal"
                           data-item-id="${user.id}"
                           data-item-type="user"
                           data-item-name="${user.username}"
                           title="Remove user">Remove</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>


</section>

<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Removing confirmation</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>Are you sure you want to remove user <span id="itemName"></span>?</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button id="deleteId" type="button" class="btn btn-danger">Remove</button>
            </div>
        </div>
    </div>
</div>
<div class="push-users"></div>


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
<script type="text/javascript" src="/resources/js/Admin.js"></script>
</body>
</html>
