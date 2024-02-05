<%--
  Created by IntelliJ IDEA.
  User: Igor
  Date: 05.02.2024
  Time: 21:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="/WEB-INF/jsp/ui/head.jsp">
    <jsp:param name="title" value="Edit meals"/>
</jsp:include>
<body>
<jsp:include page="/WEB-INF/jsp/ui/header.jsp">
    <jsp:param name="title" value="Edit meals"/>
</jsp:include>
<form action="meals?action=<%= request.getParameter("action") %>" method="post">
    <input type="hidden" name="id" value="${meal.id}">
    <label>
        <span>DateTime:</span> <input type="datetime-local" name="date_time" value="${TimeUtil.prepareTimeForJsp(meal.dateTime)}">
    </label><br><br>
    <label>
        <span>Description:</span> <input type="text" name="description" value="${meal.description}">
    </label><br><br>
    <label>
        <span>Calories:</span> <input type="number" name="calories" value="${meal.calories}">
    </label><br><br>
    <button type="submit">Save</button> <button type="button" onclick="window.history.back()">Cancel</button>
</form>
</body>
</html>
