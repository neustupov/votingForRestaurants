<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://votingforrestaurants.neustupov.ru/functions" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<section>
    <h3><a href="restaurants"><spring:message code="common.backToRestaurants"/></a></h3>
    <h2><spring:message code="vote.vote"/></h2>
    <table border="1" cellpadding="8" cellspacing="0">
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
                        ${fn:formatDateTime(vote.date)}
                </td>
                <td>
                        ${vote.restaurant.name}
                </td>
                <td><a href="votes?action=delete&id=${vote.id}"><spring:message code="common.delete"/></a></td>
            </tr>
        </c:forEach>
    </table>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>