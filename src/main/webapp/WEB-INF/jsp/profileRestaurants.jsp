<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/datatablesUtil.js" defer></script>
<script type="text/javascript" src="resources/js/profileRestaurantDatatables.js" defer></script>
<jsp:include page="fragments/profileBodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <div class="page-header">
            <h3><spring:message code="restaurant.title"/></h3>
        </div>
        <br/>
        <br/>
        <table class="table table-striped display" id="profileRestDatatable">
            <thead>
            <tr>
                <th><spring:message code="common.name"/></th>
                <th><spring:message code="restaurant.numberOfVotes"/></th>
                <th><spring:message code="restaurant.todays"/></th>
                <th><spring:message code="vote.voting"/></th>
            </tr>
            </thead>
        </table>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
<jsp:include page="fragments/i18n.jsp"/>
<script type="text/javascript">
    i18n["addTitle"] = '<spring:message code="restaurant.add"/>';
    i18n["editTitle"] = '<spring:message code="restaurant.edit"/>';
</script>
</html>