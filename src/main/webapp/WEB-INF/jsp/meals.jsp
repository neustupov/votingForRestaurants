<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<hr>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <hr>
        <a href="meals/create?menuId=${menuId}&restId=${restId}"><spring:message code="meal.addMeal"/></a>
        <hr/>
        <a class="btn btn-primary">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            <spring:message code="meal.addMeal"/>
        </a>
        <table class="table table-striped display">
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
                    <td><a><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a>
                        <a href="meals/delete?mealId=${meal.id}&menuId=${menuId}&restId=${restId}"><spring:message
                            code="common.delete"/></a>
                    </td>
                    <td><a><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>
                        <a href="meals/update?mealId=${meal.id}&menuId=${menuId}&restId=${restId}"><spring:message
                            code="common.update"/></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <a><span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span></a>
        <a href="menus?restId=${restId}"><spring:message code="meal.backToMenus"/></a>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
