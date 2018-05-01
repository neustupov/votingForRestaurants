<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<script type="text/javascript" src="resources/js/datatablesUtil.js" defer></script>
<script type="text/javascript" src="resources/js/menuDatatables.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>
<input id='restIdValue' type='hidden' value='${param.restId}'/>
<div class="jumbotron">
    <div class="container">
        <div class="page-header">
            <h3><spring:message code="menu.all"/> ${param.restId}</h3>
        </div>
        <br/>
        <br/>
        <a class="btn btn-info mr-2" onclick="createMenu(${param.restId})">
            <span class="fa fa-plus" aria-hidden="true"></span>
            <spring:message code="menu.addMenu"/>
        </a>
        <table class="table table-striped display" id="menuDatatable">
            <thead>
            <tr>
                <th><spring:message code="menu.Date"/></th>
                <th><spring:message code="menu.allMeals"/></th>
                <th><spring:message code="common.delete"/></th>
            </tr>
            </thead>
        </table>
        <br/>
        <a class="btn btn-info mr-2" onclick="redirectToRestaurants()">
            <span class="fa fa-mail-reply" aria-hidden="true"></span>
            <spring:message code="common.backToRestaurants"/>
        </a>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
<script type="text/javascript">
    var i18n = [];

    <c:forEach var="key" items='<%=new String[]{"common.deleted","common.saved","common.enabled","common.disabled","common.errorStatus"}%>'>
    i18n["${key}"] = "<spring:message code="${key}"/>";
    </c:forEach>
</script>
</html>
