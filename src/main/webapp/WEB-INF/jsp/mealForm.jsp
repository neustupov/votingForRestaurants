<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<section>
    <h3><a href="meals?restId=${restId}&menuId=${menuId}"><spring:message code="meal.backToMeals"/></a></h3>
    <h2>${param.action == 'create' ? 'Create meal' : 'Edit meal'}</h2>
    <hr>
    <jsp:useBean id="meal" type="ru.neustupov.votingforrestaurants.model.Meal" scope="request"/>
    <form method="post" action="meals">
        <input type="hidden" name="mealId" value="${meal.id}">
        <input type="hidden" name="menuId" value="${menuId}">
        <input type="hidden" name="restId" value="${restId}">
        <dl>
            <dt><spring:message code="common.name"/></dt>
            <dd><input type="text" value="${meal.name}" size=40 name="name" required></dd>
            <dt><spring:message code="meal.price"/></dt>
            <dd><input type="number" value="${meal.price}" size=40 name="price" required></dd>
        </dl>
        <button type="submit"><spring:message code="common.save"/></button>
        <button onclick="window.history.back()" type="button"><spring:message code="common.cancel"/></button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
