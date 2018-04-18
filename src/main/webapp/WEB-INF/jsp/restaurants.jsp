<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/datatablesUtil.js" defer></script>
<script type="text/javascript" src="resources/js/restaurantDatatables.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <h3><spring:message code="restaurant.title"/></h3>
        <a class="btn btn-primary" onclick="add()">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            <spring:message code="restaurants.addRestaurant"/>
        </a>
        <a class="btn btn-primary" onclick="redirectToVotes()">
            <span class="glyphicon" aria-hidden="true"></span>
            <spring:message code="restaurant.viewAllVotes"/>
        </a>
        <table class="table table-striped display" id="restDatatable">
            <thead>
            <tr>
                <th><spring:message code="common.name"/></th>
                <th><spring:message code="restaurant.numberOfVotes"/></th>
                <th></th>
                <th></th>
                <th></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${restaurantsList}" var="restaurant">
                <tr>
                    <td><c:out value="${restaurant.name}"/></td>
                    <td><c:out value="${restaurant.numberOfVotes}"/>
                    <td>
                        <a class="glyphicon glyphicon-cutlery" aria-hidden="false"
                           onclick="getTodaysMenuWithMeals(${restaurant.id})"></a>
                    </td>
                    <td>
                        <a class="glyphicon glyphicon-th-list" aria-hidden="true"
                           onclick="redirectToMenus(${restaurant.id})"></a>
                    </td>
                    <td>
                        <a class="glyphicon glyphicon-remove" aria-hidden="true"
                           onclick="deleteRow(${restaurant.id})"></a>
                    <td>
                        <a class="glyphicon glyphicon-pencil" aria-hidden="true"></a>
                        <a href="restaurants/update?id=${restaurant.id}">
                            <spring:message code="common.update"/></a>
                    </td>
                    <td>
                        <a class="glyphicon glyphicon-ok" aria-hidden="true"
                           onclick="createVote(${restaurant.id})"></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<div class="modal fade" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title" id="modalTitle"><spring:message code="restaurant.add"/></h2>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="detailsForm">
                    <input type="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="name" class="control-label col-xs-3"><spring:message
                                code="restaurant.name"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="name" name="name"
                                   placeholder="<spring:message code="restaurant.name"/>">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button class="btn btn-primary" type="button" onclick="save()">
                                <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
