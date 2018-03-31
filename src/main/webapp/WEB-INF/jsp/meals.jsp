<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<hr>
<jsp:include page="fragments/bodyHeader.jsp"/>
<hr>
<a href="meals/create?menuId=${menuId}&restId=${restId}"><spring:message code="meal.addMeal"/></a>
</hr>
<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th>Id</th>
        <th><spring:message code="common.name"/></th>
        <th><spring:message code="meal.menuId"/></th>
        <th><spring:message code="meal.price"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${mealsList}" var="meal">
        <tr>
            <td><c:out value="${meal.id}"/></td>
            <td><c:out value="${meal.name}"/></td>
            <td><c:out value="${meal.menu.id}"/></td>
            <td><c:out value="${meal.price}"/></td>
            <td><a href="meals/delete?mealId=${meal.id}&menuId=${menuId}&restId=${restId}"><spring:message code="common.delete"/></a>
            </td>
            <td><a href="meals/update?mealId=${meal.id}&menuId=${menuId}&restId=${restId}"><spring:message code="common.update"/></a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<h3><a href="menus?restId=${restId}"><spring:message code="meal.backToMenus"/></a></h3>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
