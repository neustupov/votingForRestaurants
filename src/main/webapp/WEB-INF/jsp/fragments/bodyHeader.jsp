<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<nav class="navbar navbar-expand-md navbar-dark bg-dark py-0">
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

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <sec:authorize access="isAuthenticated()">
                    <li class="nav-item">
                        <form:form class="form-inline my-2" action="logout" method="post">
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <a class="btn btn-info mr-1" href="users"><spring:message code="user.title"/></a>
                            </sec:authorize>
                            <a class="btn btn-info mr-1" href="profile">${userTo.name} <spring:message
                                    code="app.profile"/></a>
                            <button class="btn btn-primary" type="submit">
                                <span class="fa fa-sign-out"></span>
                            </button>
                        </form:form>
                    </li>
                </sec:authorize>
                <sec:authorize access="isAnonymous()">
                    <li class="nav-item">
                        <form:form class="form-inline my-2" action="spring_security_check" method="post">
                            <input class="form-control mr-1" type="text" placeholder="Email" name="username">
                            <input class="form-control mr-1" type="password" placeholder="Password" name="password">
                            <button class="btn btn-success" type="submit">
                                <span class="fa fa-sign-in"></span>
                            </button>
                        </form:form>
                    </li>
                </sec:authorize>
            </ul>
        </div>

    </div>
</nav>