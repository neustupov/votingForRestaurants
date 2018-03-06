<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Meal</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<section>
    <h3><a href="meals?restId=${restId}&menuId=${menuId}">Back to Meals</a></h3>
    <h2>${param.action == 'create' ? 'Create meal' : 'Edit meal'}</h2>
    <hr>
    <jsp:useBean id="meal" type="ru.neustupov.votingForRestaurants.model.Meal" scope="request"/>
    <form method="post" action="meals">
        <input type="hidden" name="mealId" value="${meal.id}">
        <input type="hidden" name="menuId" value="${menuId}">
        <input type="hidden" name="restId" value="${restId}">
        <dl>
            <dt>Name:</dt>
            <dd><input type="text" value="${meal.name}" size=40 name="name" required></dd>
            <dt>Price:</dt>
            <dd><input type="number" value="${meal.price}" size=40 name="price" required></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section>
</body>
</html>
