<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<hr/>
<a href="restaurants?action=create"><spring:message code="restaurants.addRestaurant"/></a>
<a href="votes?action=all"><spring:message code="restaurant.viewAllVotes"/></a>
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
    </tr>
    </thead>
    <c:forEach items="${restaurantsList}" var="restaurant">
    <tr>
        <td><c:out value="${restaurant.id}"/></td>
        <td><c:out value="${restaurant.name}"/></td>
        <td><c:out value="${restaurant.numberOfVotes}"/>
        <td><a href="menus?action=all&restId=${restaurant.id}"><spring:message code="restaurant.showAllMenus"/></a></td>
        <td><a href="restaurants?action=delete&restId=${restaurant.id}"><spring:message code="common.delete"/></a></td>
        <td><a href="restaurants?action=update&restId=${restaurant.id}"><spring:message code="common.update"/></a></td>
        <td><a href="votes?action=create&restId=${restaurant.id}"><spring:message code="restaurant.vote"/></a></td>
    </tr>
    </c:forEach>
</table>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
