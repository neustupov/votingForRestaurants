<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://votingForRestaurants.neustupov.ru/functions" %>
<html>
<head>
    <title>Vote list</title>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <h2>Votes</h2>
    <hr/>
    <a href="votes?action=create">Add Meal</a>
    <hr/>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Id</th>
            <th>User Id</th>
            <th>Add Date</th>
            <th>Restaurant</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${votes}" var="vote">
            <jsp:useBean id="vote" scope="page" type="ru.neustupov.votingForRestaurants.model.Vote"/>
            <tr>
                <td>
                        ${vote.id}
                </td>
                <td>
                        ${vote.user.id}
                </td>
                <td>
                        ${fn:formatDateTime(vote.dateTime)}
                </td>
                <td>
                        ${vote.restaurant.name}
                </td>
                <td><a href="votes?action=update&id=${vote.id}">Update</a></td>
                <td><a href="votes?action=delete&id=${vote.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>