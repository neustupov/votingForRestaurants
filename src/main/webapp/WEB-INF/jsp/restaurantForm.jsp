<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <jsp:useBean id="restaurant" type="ru.neustupov.votingforrestaurants.model.Restaurant" scope="request"/>
        <h3><a href="restaurants"><spring:message code="common.backToRestaurants"/></a></h3>
        <h2><spring:message code="${restaurant.isNew() ? 'restaurant.add' : 'restaurant.edit'}"/></h2>
        <hr>
        <form method="post" action="restaurants">
            <input type="hidden" name="id" value="${restaurant.id}">
            <dl>
                <dt><spring:message code="common.name"/></dt>
                <dd><input type="text" value="${restaurant.name}" size=40 name="name" required></dd>
            </dl>
            <button type="submit"><spring:message code="common.save"/></button>
            <button onclick="window.history.back()" type="button"><spring:message code="common.cancel"/></button>
        </form>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>