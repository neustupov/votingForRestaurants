<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<nav class="navbar navbar-dark bg-dark">
    <div class="container">
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <a href="/restaurants" class="navbar-brand">
                <img src="resources/images/vote_ico.png">
                <spring:message code="app.title"/></a>
        </sec:authorize>
        <sec:authorize access="!hasRole('ROLE_ADMIN')">
            <a href="/profileRestaurants" class="navbar-brand">
                <img src="resources/images/vote_ico.png">
                <spring:message code="app.title"/></a>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <form:form class="form-inline my-2" action="logout" method="post">
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <a class="btn btn-primary mr-2" href="users"><spring:message code="user.title"/></a>
                </sec:authorize>
                <a class="btn btn-primary mr-2" href="profile">${userTo.name} <spring:message code="app.profile"/></a>
                <button class="btn btn-primary mr-2" type="submit">
                    <span class="fa fa-sign-out" aria-hidden="true"></span>
                </button>
            </form:form>
        </sec:authorize>
    </div>
</nav>