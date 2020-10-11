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
    <title>Meals</title>
    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }

        th, td {
            padding: 15px;
            text-align: center;
        }

    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<li><a href="meals?action=create">Add meal</a></li>
<table>
    <thead>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <tbody>


    <jsp:useBean id="mealsTo" scope="page" type="ru.javawebinar.topjava.model.MealTo"/>
    <c:forEach items="${mealsTo}" var="meal">
        <tr style="color: ${meal.excess ? "forestgreen" : "darkred"} ">
            <td>
                <fmt:parseDate value="${meal.dateTime}"
                               pattern="yyyy-MM-dd'T'HH:mm"
                               var="parsedDateTime"/>
                <fmt:formatDate value="${parsedDateTime}" pattern="yyyy.MM.dd HH:mm" />
            </td>

            <td><c:out value="${meal.description}"/></td>
            <td><c:out value="${meal.calories}"/></td>
            <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
            <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
        </tr>
    </c:forEach>

    </tbody>
</table>
</body>
</html>
