<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>
<%--
  Created by IntelliJ IDEA.
  User: Igor
  Date: 05.02.2024
  Time: 6:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="/WEB-INF/jsp/ui/head.jsp">
    <jsp:param name="title" value="Meals"/>
</jsp:include>
<body>
<jsp:include page="/WEB-INF/jsp/ui/header.jsp">
    <jsp:param name="title" value="Meals"/>
</jsp:include>
<a href="meals?action=create">Add meal</a>
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
    <c:forEach items="${meals}" var="meal">
        <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealTo"/>
        <tr style="color: ${meal.excess ? 'red' : 'green'}">
            <td>${TimeUtil.prepareTimeForJsp(meal.dateTime)}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td><a href="meals?id=${meal.id}&action=update">Update</a></td>
            <td><a href="meals?id=${meal.id}&action=delete">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
