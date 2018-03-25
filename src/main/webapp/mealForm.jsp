<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setBundle basename="messages.app"/>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<section>
    <h3><a href="meals?restId=${restId}&menuId=${menuId}"><fmt:message key="meal.backToMeals"/></a></h3>
    <h2>${param.action == 'create' ? 'Create meal' : 'Edit meal'}</h2>
    <hr>
    <jsp:useBean id="meal" type="ru.neustupov.votingforrestaurants.model.Meal" scope="request"/>
    <form method="post" action="meals">
        <input type="hidden" name="mealId" value="${meal.id}">
        <input type="hidden" name="menuId" value="${menuId}">
        <input type="hidden" name="restId" value="${restId}">
        <dl>
            <dt><fmt:message key="common.name"/></dt>
            <dd><input type="text" value="${meal.name}" size=40 name="name" required></dd>
            <dt><fmt:message key="meal.price"/></dt>
            <dd><input type="number" value="${meal.price}" size=40 name="price" required></dd>
        </dl>
        <button type="submit"><fmt:message key="common.save"/></button>
        <button onclick="window.history.back()" type="button"><fmt:message key="common.cancel"/></button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
