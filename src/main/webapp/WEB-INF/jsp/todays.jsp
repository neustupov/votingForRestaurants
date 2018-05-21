<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <div class="page-header">
            <h3><spring:message code="menu.todays"/> ${param.restId}</h3>
        </div>
        <br/>
        <br/>
        <table class="table table-striped display">
            <thead>
            <tr>
                <th><spring:message code="common.name"/></th>
                <th><spring:message code="meal.price"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${mealsList}" var="meal">
                <tr>
                    <td><c:out value="${meal.name}"/></td>
                    <td><c:out value="${meal.price}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <br/>
        <a class="btn btn-primary mr-2" href="/restaurants">
            <span class="fa fa-mail-reply" aria-hidden="true"></span>
            <spring:message code="common.backToRestaurants"/>
        </a>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
<jsp:include page="fragments/i18n.jsp">
    <jsp:param name="page" value="meal"/>
</jsp:include>
</html>