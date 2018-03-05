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
<hr/>
<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <c:forEach items="${restaurantsList}" var="restaurant">
        <tr>
            <td><c:out value="${restaurant.id}"/></td>
            <td><c:out value="${restaurant.name}"/></td>
            <td><a href="menus?action=all&restId=${restaurant.id}">Show the menu</a></td>
            <td><a href="restaurants?action=delete&restId=${restaurant.id}">Delete the Restaurant</a></td>
            <td><a href="votes?action=create&restId=${restaurant.id}">Votes for this Restaurant</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
