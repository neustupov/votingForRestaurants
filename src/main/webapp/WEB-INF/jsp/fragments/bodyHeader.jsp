<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<nav class="navbar navbar-dark bg-dark">
    <div class="container">
        <a href="/restaurants" class="navbar-brand">
            <img src="resources/images/vote_ico.png">
            <spring:message code="app.title"/></a>
        <form class="form-inline my-2">
            <a class="btn btn-info mr-2" href="users"><spring:message code="user.title"/></a>
            <a class="btn btn-info mr-2" href="logout">
                <span class="fa fa-sign-out"></span>
            </a>
        </form>
    </div>
</nav>