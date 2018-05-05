<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/datatablesUtil.js" defer></script>
<script type="text/javascript" src="resources/js/mealDatatables.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>
<input id='menuIdValue' type='hidden' value='${param.menuId}'/>
<div class="jumbotron">
    <div class="container">
        <div class="page-header">
            <h3><spring:message code="meal.all"/> ${param.menuId}</h3>
        </div>
        <br/>
        <br/>
        <a class="btn btn-info mr-2" data-toggle="modal" onclick="addMeal(${param.menuId})">
            <span class="fa fa-plus" aria-hidden="true"></span>
            <spring:message code="meal.addMeal"/>
        </a>
        <table class="table table-striped display" id="mealDatatable">
            <thead>
            <tr>
                <th><spring:message code="common.name"/></th>
                <th><spring:message code="meal.price"/></th>
                <th><spring:message code="common.edit"/></th>
                <th><spring:message code="common.delete"/></th>
            </tr>
            </thead>
        </table>
        <br/>
        <a class="btn btn-info mr-2" onclick="redirectToMenus(${param.restId})">
            <span class="fa fa-mail-reply" aria-hidden="true"></span>
            <spring:message code="meal.backToMenus"/>
        </a>
    </div>
</div>

<div class="modal fade" tabindex="-1" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="modalTitle"></h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <form id="detailsForm">
                    <input type="hidden" id="id" name="id">
                    <input type="hidden" id="menuId" name="menuId">

                    <div class="form-group">
                        <label for="name" class="col-form-label"><spring:message
                                code="common.name"/></label>

                        <input type="text" class="form-control" id="name" name="name"
                               placeholder="<spring:message code="common.name"/>">
                    </div>
                    <div class="form-group">
                        <label for="name" class="col-form-label"><spring:message
                                code="meal.price"/></label>

                        <input type="text" class="form-control" id="price" name="price"
                               placeholder="<spring:message code="meal.price"/>">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">
                            <span class="fa fa-close" aria-hidden="true"></span>
                        </button>
                        <button type="button" onclick="saveMeal(${param.menuId})" class="btn btn-primary">
                            <span class="fa fa-check" aria-hidden="true"></span>
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
<jsp:include page="fragments/i18n.jsp"/>
<script type="text/javascript">
    i18n["addTitle"] = '<spring:message code="meal.add"/>';
    i18n["editTitle"] = '<spring:message code="meal.edit"/>';
</script>
</html>
