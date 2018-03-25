<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setBundle basename="messages.app"/>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<section>
    <h3><a href="restaurants"><fmt:message key="common.backToRestaurants"/></a></h3>
    <h2>${param.action == 'create' ? '<fmt:message key="restaurants.addRestaurant"/>' : 'Edit restaurant'}</h2>
    <hr>
    <jsp:useBean id="restaurant" type="ru.neustupov.votingforrestaurants.model.Restaurant" scope="request"/>
    <form method="post" action="restaurants">
        <input type="hidden" name="restId" value="${restaurant.id}">
        <dl>
            <dt><fmt:message key="common.name"/></dt>
            <dd><input type="text" value="${restaurant.name}" size=40 name="name" required></dd>
        </dl>
        <button type="submit"><fmt:message key="common.save"/></button>
        <button onclick="window.history.back()" type="button"><fmt:message key="common.cancel"/></button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>