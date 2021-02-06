<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="ru">
<head>
    <title>Title</title>
    <style type="text/css">
        table.mealTable {
            width: 600px;
            text-align: left;
            font-size: 1.3em;
        }

        table.mealTable td, table.mealTable th {
            border: 1px solid #AAAAAA;
            padding: 5px 3px;
        }

        tr.redText {
            color: red;
        }

        tr.greenText {
            color: green;
        }
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>

<table class="mealTable">
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>
    <c:forEach var="meal" items="${meals}">
        <tr class="${meal.excess ? 'redText' : 'greenText'}">
            <td>
                <fmt:parseDate value="${meal.dateTime}" var="parsedDate" pattern="yyyy-MM-dd'T'HH:mm"/>
                <fmt:formatDate value="${parsedDate}" pattern="dd.MM.yyyy HH:mm"/>
            </td>
            <td><c:out value="${meal.description}"/></td>
            <td><c:out value="${meal.calories}"/></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
