<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <hr/>
        <a href="restaurants/create"><spring:message code="restaurants.addRestaurant"/></a>
        <a href="votes"><spring:message code="restaurant.viewAllVotes"/></a>
        <hr/>
        <a class="btn btn-primary">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            <spring:message code="restaurants.addRestaurant"/>
        </a>
        <a class="btn btn-primary">
            <span class="glyphicon" aria-hidden="true"></span>
            <spring:message code="restaurant.viewAllVotes"/>
        </a>
        <table class="table table-striped display">
            <thead>
            <tr>
                <th>Id</th>
                <th><spring:message code="common.name"/></th>
                <th><spring:message code="restaurant.numberOfVotes"/></th>
                <th></th>
                <th></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${restaurantsList}" var="restaurant">
                <tr>
                    <td><c:out value="${restaurant.id}"/></td>
                    <td><c:out value="${restaurant.name}"/></td>
                    <td><c:out value="${restaurant.numberOfVotes}"/>
                    <td><a href="menus/getTodaysMenuWithMeals?restId=${restaurant.id}"><spring:message
                            code="menu.showTodaysMenu"/></a></td>
                    <td><a><span class="glyphicon glyphicon-th-list" aria-hidden="true"></span></a>
                        <a href="menus?restId=${restaurant.id}"><spring:message code="restaurant.showAllMenus"/></a>
                    </td>
                    <td><a><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a>
                        <a href="restaurants/delete?id=${restaurant.id}"><spring:message code="common.delete"/></a></td>
                    <td><a><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>
                        <a href="restaurants/update?id=${restaurant.id}"><spring:message code="common.update"/></a></td>
                    <td><a><span class="glyphicon glyphicon-ok" aria-hidden="true"></span></a>
                        <a href="votes/updateOrCreate?restId=${restaurant.id}"><spring:message
                            code="restaurant.vote"/></a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
