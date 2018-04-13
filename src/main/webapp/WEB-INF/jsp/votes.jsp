<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <h2><spring:message code="vote.vote"/></h2>
        <table class="table table-striped display">
            <thead>
            <tr>
                <th>Id</th>
                <th><spring:message code="user.userId"/></th>
                <th><spring:message code="common.addDate"/></th>
                <th><spring:message code="vote.restaurant"/></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${votesList}" var="vote">
                <jsp:useBean id="vote" scope="page" type="ru.neustupov.votingforrestaurants.model.Vote"/>
                <tr>
                    <td>
                            ${vote.id}
                    </td>
                    <td>
                            ${vote.user.id}
                    </td>
                    <td>
                        <fmt:formatDate value="${vote.date}" pattern="dd-MMMM-yyyy"/>
                    </td>
                    <td>
                            ${vote.restaurant.name}
                    </td>
                    <td><a href="votes?action=delete&id=${vote.id}"><spring:message code="common.delete"/></a></td>
                </tr>
            </c:forEach>
        </table>
        <a><span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span></a>
        <a href="restaurants"><spring:message code="common.backToRestaurants"/></a>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>