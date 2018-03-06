<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Restaurant</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<section>
    <h3><a href="restaurants">Back to Restaurants</a></h3>
    <h2>${param.action == 'create' ? 'Create restaurant' : 'Edit restaurant'}</h2>
    <hr>
    <jsp:useBean id="restaurant" type="ru.neustupov.votingForRestaurants.model.Restaurant" scope="request"/>
    <form method="post" action="restaurants">
        <input type="hidden" name="restId" value="${restaurant.id}">
        <dl>
            <dt>Name:</dt>
            <dd><input type="text" value="${restaurant.name}" size=40 name="name" required></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section>
</body>
</html>