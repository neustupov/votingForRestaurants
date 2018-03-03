<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show All Restaurants</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<table border=1>
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${restaurantsList}" var="restaurant">
        <tr>
            <td><c:out value="${restaurant.id}"/></td>
            <td><c:out value="${restaurant.name}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<h3><a href="index.html">Home</a></h3>
</body>
</html>
