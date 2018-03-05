<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show Menus</title>
</head>
<body>
<hr/>
<a href="menus?action=create&restId=${restId}">Add Menu</a>
<hr/>
<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th>Id</th>
        <th>Id Restaurant</th>
        <th>Add Date</th>
        <th></th>
    </tr>
    </thead>
    <c:forEach items="${menusList}" var="menu">
        <tr>
            <td><c:out value="${menu.id}"/></td>
            <td><c:out value="${menu.restId}"/></td>
            <td><c:out value="${menu.addDate}"/></td>
            <td><a href="meals?action=all&id=${menu.id}&restId=${restId}">Show the meal</a></td>
        </tr>
    </c:forEach>
</table>
<h3><a href="restaurants">Back to Restaurants</a></h3>
</body>
</html>
