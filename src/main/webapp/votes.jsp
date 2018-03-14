<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Vote list</title>
</head>
<body>
<section>
    <h3><a href="restaurants">Back to the Restaurant</a></h3>
    <h2>Votes</h2>
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
                        ${vote.dateTime}
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