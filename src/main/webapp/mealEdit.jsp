<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <link type="text/css" href="https://ajax.aspnetcdn.com/ajax/jquery.ui/1.8.18/themes/flick/jquery-ui.css"
          rel="stylesheet"/>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/jquery-ui.min.js"></script>
    <title>Edit Meal</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Edit Meal</h2>

<script>
    $(function () {
        $('input[name=dateTime]').datepicker();
    });
</script>

<jsp:useBean id="meal" scope="request" type="ru.javawebinar.topjava.model.Meal"/>
<form method="POST" action="meals">
    <input type="hidden" name="id" value="${meal.id}">
    DateTime : <input type="datetime-local" name="dateTime" value="${meal.dateTime}"/> <br/>
    Description : <input type="text" name="description"  value="${meal.description}"/> <br/>
    Calories : <input type="text" name="calories" value="${meal.calories}"/> <br/>
    <input type="submit" value="Save"/>
    <input type="button" value="Cancel" onclick="window.location.href='meals'"/>
</form>
</body>
</html>
