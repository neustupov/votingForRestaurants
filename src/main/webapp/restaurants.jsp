<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show All Restaurants</title>
</head>
<body>
<hr/>
<a href="restaurants?action=create">Add Restaurant</a>
<a href="votes?action=all">View all votes</a>
<hr/>
<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Total votes</th>
        <th></th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <c:forEach items="${restaurantsList}" var="restaurant">
    <tr>
        <td><c:out value="${restaurant.id}"/></td>
        <td><c:out value="${restaurant.name}"/></td>
        <td><c:out value="${restaurant.numberOfVotes}"/>
        <td><a href="menus?action=all&restId=${restaurant.id}">Show the menus</a></td>
        <td><a href="restaurants?action=delete&restId=${restaurant.id}">Delete</a></td>
        <td><a href="restaurants?action=update&restId=${restaurant.id}">Update</a></td>
        <td><a href="votes?action=create&restId=${restaurant.id}">Vote</a></td>
    </tr>
    </c:forEach>
</table>
</body>
</html>
