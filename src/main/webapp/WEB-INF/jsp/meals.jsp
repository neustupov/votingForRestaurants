<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setBundle basename="messages.app"/>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<hr>
<jsp:include page="fragments/bodyHeader.jsp"/>
<hr>
<a href="meals?action=create&menuId=${menuId}&restId=${restId}"><fmt:message key="meal.addMeal"/></a>
</hr>
<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th>Id</th>
        <th><fmt:message key="common.name"/></th>
        <th><fmt:message key="meal.menuId"/></th>
        <th><fmt:message key="meal.price"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${mealsList}" var="meal">
        <tr>
            <td><c:out value="${meal.id}"/></td>
            <td><c:out value="${meal.name}"/></td>
            <td><c:out value="${meal.menu.id}"/></td>
            <td><c:out value="${meal.price}"/></td>
            <td><a href="meals?action=delete&mealId=${meal.id}&menuId=${menuId}&restId=${restId}"><fmt:message key="common.delete"/></a>
            </td>
            <td><a href="meals?action=update&mealId=${meal.id}&menuId=${menuId}&restId=${restId}"><fmt:message key="common.update"/></a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<h3><a href="menus?action=all&restId=${restId}"><fmt:message key="meal.backToMenus"/></a></h3>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
