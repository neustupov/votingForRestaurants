<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/datatablesUtil.js" defer></script>
<script type="text/javascript" src="resources/js/menuDatatables.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <div class="page-header">
            <h3>
                <c:if test="${menusList.size() != 0}">
                    ${menusList.get(0).restaurant.name}
                </c:if>
            </h3>
        </div>
        <a class="btn btn-primary" onclick="createMenu(${menu.id}, ${restId})">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            <spring:message code="menu.addMenu"/>
        </a>
        <table class="table table-striped display" id="menuDatatable">
            <thead>
            <tr>
                <th><spring:message code="menu.Date"/></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${menusList}" var="menu">
                <jsp:useBean id="menu" scope="page" type="ru.neustupov.votingforrestaurants.model.Menu"/>
                <tr>
                    <td><c:out value="${menu.addDate}"/></td>
                    <td>
                        <a class="glyphicon glyphicon-th-list" aria-hidden="true"
                           onclick="redirectToMeals(${restId}, ${menu.id})">
                        </a>
                    </td>
                    <td>
                        <a class=" glyphicon glyphicon-remove" aria-hidden="true"
                           onclick="deleteMenu(${menu.id}, ${restId})">
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br/>
        <a><span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span></a>
        <a href="restaurants"><spring:message code="common.backToRestaurants"/></a>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
