<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Vote</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <h2>${param.action == 'create' ? 'Create vote' : 'Edit vote'}</h2>
    <hr>
    <jsp:useBean id="vote" type="ru.neustupov.votingForRestaurants.model.Vote" scope="request"/>
    <form method="post" action="votes">
        <dl>
            <dt>ID:</dt>
            <dd><input type="number" value="${vote.id}" name="id" required></dd>
        </dl>
        <dl>
            <dt>User ID:</dt>
            <dd><input type="number" value="${vote.user.id}" name="userId" required></dd>
        </dl>
        <dl>
            <dt>Add DateTime</dt>
            <dd><input type="datetime-local" value="${vote.dateTime}" name="dateTime" required></dd>
        </dl>
        <dl>
            <dt>Restaurant:</dt>
            <dd><input type="number" value="${vote.restaurant.id}" name="restaurant" required></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section>
</body>
</html>
