<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>musicsite - Home Page</title>
    <%@include file="../fragments/head.html"%>
    <style>
        <%@include file="/resources/css/style.css"%>
    </style>
</head>
<body>
<%@include file="../fragments/header.jspf" %>
<c:if test="${not empty id}">
    <%@include file="../fragments/dashboard.jspf" %>
</c:if>

</body>
</html>