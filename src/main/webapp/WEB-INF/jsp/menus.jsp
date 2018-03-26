<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://votingforrestaurants.neustupov.ru/functions" %>

<fmt:setBundle basename="messages.app"/>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<hr/>
<a href="menus?action=create&restId=${restId}"><fmt:message key="menu.addMenu"/></a>
<hr/>
<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th>Id</th>
        <th><fmt:message key="menu.idRestaurant"/></th>
        <th><fmt:message key="common.addDate"/></th>
        <th></th>
    </tr>
    </thead>
    <c:forEach items="${menusList}" var="menu">
        <jsp:useBean id="menu" scope="page" type="ru.neustupov.votingforrestaurants.model.Menu"/>
        <tr>
            <td><c:out value="${menu.id}"/></td>
            <td><c:out value="${menu.restaurant.id}"/></td>
            <td>${fn:formatDateTime(menu.addDate)}</td>
            <td><a href="meals?action=all&menuId=${menu.id}&restId=${restId}"><fmt:message key="menu.showAllMeals"/></a></td>
            <td><a href="menus?action=delete&menuId=${menu.id}&restId=${restId}"><fmt:message key="common.delete"/></a></td>
        </tr>
    </c:forEach>
</table>
<h3><a href="restaurants"><fmt:message key="common.backToRestaurants"/></a></h3>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
