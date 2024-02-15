<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>--%>
<html>
<head>
    <title>Meal list</title>
    <style>
        .normal {
            color: green;
        }

        .excess {
            color: red;
        }
    </style>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <hr/>
    <h2>Meals</h2>
    <a href="meals?action=create">Add Meal</a>
    <br><br>
    <form action="meals" style="display: flex; gap: 24px; flex-wrap: wrap">
        <label>
            <span style="display: inline-block;margin-bottom: 8px;">От даты (включая)</span><br>
            <input type="date" name="startDate" value="${param.startDate}">
        </label>
        <label>
            <span style="display: inline-block;margin-bottom: 8px;">До даты (включая)</span><br>
            <input type="date" name="endDate" value="${param.endDate}">
        </label>
        <label>
            <span style="display: inline-block;margin-bottom: 8px;">От времени (включая)</span><br>
            <input type="time" name="startTime" value="${param.startTime}">
        </label>
        <label>
            <span style="display: inline-block;margin-bottom: 8px;">До времени (исключая)</span><br>
            <input type="time" name="endTime" value="${param.endTime}">
        </label>
        <div style="width: 100%">
            <input type="submit" value="Filter">
        </div>
    </form>
    <br><br>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${requestScope.meals}" var="meal">
            <jsp:useBean id="meal" type="ru.javawebinar.topjava.to.MealTo"/>
            <tr class="${meal.excess ? 'excess' : 'normal'}">
                <td>
                        <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                        <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                        <%--${fn:replace(meal.dateTime, 'T', ' ')}--%>
                        ${fn:formatDateTime(meal.dateTime)}
                </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
                <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>