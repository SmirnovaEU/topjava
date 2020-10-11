<%--
  Created by IntelliJ IDEA.
  User: Елена
  Date: 05.10.2020
  Time: 20:52
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Edit meals</title>
    <style>
        dl {
            display: inline-block;
        }
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>


<form method="post" action="MealServlet">
    <input type="hidden" name="id" value="${meal.id}">
    <dl>
        <dt>DateTime:</dt>
        <dd><input type="datetime-local" name="dateTime" value="${meal.dateTime}"></dd>
    </dl>
    <label>Description:
        <input type="text" name="description"><br />
    </label>
    <label>Calories:
        <input type="number" name="calories"><br />
    </label>
    <button type="submit">Save</button>
    <button onclick="window.history.back()">Cancel</button>
</form>
</body>
</html>
