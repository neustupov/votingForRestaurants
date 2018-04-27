<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <p/>
        <form method="post" action="users" class="form-inline">
            <label><spring:message code="app.login"/></label>
            <select name="userId" class="form-control mx-3">
                <option value="100000" selected>User</option>
                <option value="100001">Admin</option>
            </select>
            <button type="submit" class="btn btn-info mr-2"><spring:message code="common.select"/></button>
        </form>
        <ul>
            <li><a href="users"><spring:message code="user.title"/></a></li>
            <li><a href="restaurants"><spring:message code="restaurant.title"/></a></li>
        </ul>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>