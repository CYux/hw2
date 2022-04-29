<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Chan
  Date: 2022/4/21
  Time: 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reservation</title>
</head>
<body>
<%@include file="header.jsp"%>

<h1>Reservation</h1>
<table>
    <tr>
        <th>Book ID</th>
        <th>Book Name</th>
        <th>Topic</th>
    </tr>

<c:forEach items="${books}" var="book">
    <tr>
    <td>${book.getBook_id()}</td>
    <td>${book.getBook_name()}</td>
    <td>${book.getTopic_name()}</td>
    </tr>
</c:forEach>


</table>
</body>
</html>
