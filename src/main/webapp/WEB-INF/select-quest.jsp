<%--
  Created by IntelliJ IDEA.
  User: scarab
  Date: 24.03.2024
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="head.jsp"%>
<head>
    <title>Выберите квест</title>
</head>
<body class="py-3 my-4">
<c:forEach var="quest" items="${requestScope.quests}">
    <p>
        <a href="quest?id=${quest.id}">${quest.name} " | " ${quest.description} </a>
    </p>
</c:forEach>
</body>
<%@include file="footer.jsp"%>
