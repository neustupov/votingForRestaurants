<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show All Meals</title>
</head>
<body>
<table border=1>
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>IdMenu</th>
        <th>Price</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${mealsList}" var="meal">
        <tr>
            <td><c:out value="${meal.id}"/></td>
            <td><c:out value="${meal.name}"/></td>
            <td><c:out value="${meal.idMenu}"/></td>
            <td><c:out value="${meal.price}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<h3><a href="index.html">Home</a></h3>
</body>
</html>
