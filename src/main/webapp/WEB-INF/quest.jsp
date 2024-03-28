<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: scarab
  Date: 25.03.2024
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="head.jsp"%>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <tr>
        <td>${question.description}</td>
        <td><img src="${question.images[0]}"></td>
    </tr>
        <p></p>
<c:if test="${!empty responses}">
<c:forEach var="response" items="${responses}">
    <tr>
        <td><input type="radio" value="${response.id}">${response.description}</td>
        <td><img src="${response.images[0]}"></td>
    </tr>
    <p></p>
</c:forEach>
</c:if>
</table>
<p>
    <button name="OK" type="button" onclick="">Ответить</button>
</p>
</body>
<%@include file="footer.jsp"%>
