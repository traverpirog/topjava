<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="title" value="${meal.id == null ? 'Add' : 'Edit'}" />
<html>
<jsp:include page="/WEB-INF/jsp/ui/head.jsp">
    <jsp:param name="title" value="${title} meals"/>
</jsp:include>
<body>
<jsp:include page="/WEB-INF/jsp/ui/header.jsp">
    <jsp:param name="title" value='${title} meals'/>
</jsp:include>
<form action="meals" method="post">
    ${meal.id == null ? '' : '<input type="hidden" name="id" value="' + meal.id + '">'}
    <label>
        <span>DateTime:</span> <input type="datetime-local" name="date_time" value="${!empty meal.dateTime ? TimeUtil.prepareTimeForJsp(meal.dateTime) : ''}" />
    </label><br><br>
    <label>
        <span>Description:</span> <input type="text" name="description" value="${!empty meal.description ? meal.description : ''}"/>
    </label><br><br>
    <label>
        <span>Calories:</span> <input type="number" name="calories" value="${!empty meal.calories ? meal.calories : ''}">
    </label><br><br>
    <button type="submit">Save</button> <button type="button" onclick="window.history.back()">Cancel</button>
</form>
</body>
</html>
