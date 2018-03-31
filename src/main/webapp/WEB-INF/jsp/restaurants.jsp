<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<hr/>
<a href="restaurants/create"><spring:message code="restaurants.addRestaurant"/></a>
<a href="votes"><spring:message code="restaurant.viewAllVotes"/></a>
<hr/>
<table border="1" cellpadding="8" cellspacing="0">
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
        <td><a href="menus/getTodaysMenuWithMeals?restId=${restaurant.id}"><spring:message code="menu.showTodaysMenu"/></a></td>
        <td><a href="menus?restId=${restaurant.id}"><spring:message code="restaurant.showAllMenus"/></a></td>
        <td><a href="restaurants/delete?id=${restaurant.id}"><spring:message code="common.delete"/></a></td>
        <td><a href="restaurants/update?id=${restaurant.id}"><spring:message code="common.update"/></a></td>
        <td><a href="votes/create?restId=${restaurant.id}"><spring:message code="restaurant.vote"/></a></td>
    </tr>
    </c:forEach>
</table>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
