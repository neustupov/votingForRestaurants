<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://votingforrestaurants.neustupov.ru/functions" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<hr/>
<a href="menus/create?restId=${restId}&menuId=${menuId}"><spring:message code="menu.addMenu"/></a>
<hr/>
<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th>Id</th>
        <th><spring:message code="menu.idRestaurant"/></th>
        <th><spring:message code="common.addDate"/></th>
        <th></th>
    </tr>
    </thead>
    <c:forEach items="${menusList}" var="menu">
        <jsp:useBean id="menu" scope="page" type="ru.neustupov.votingforrestaurants.model.Menu"/>
        <tr>
            <td><c:out value="${menu.id}"/></td>
            <td><c:out value="${menu.restaurant.id}"/></td>
            <td><c:out value="${menu.addDate}"/></td>
            <td><a href="meals?menuId=${menu.id}&restId=${restId}"><spring:message code="menu.showAllMeals"/></a></td>
            <td><a href="menus/delete?menuId=${menu.id}&restId=${restId}"><spring:message code="common.delete"/></a></td>
        </tr>
    </c:forEach>
</table>
<h3><a href="restaurants"><spring:message code="common.backToRestaurants"/></a></h3>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
