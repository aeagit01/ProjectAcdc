<%--
  Created by IntelliJ IDEA.
  User: scarab
  Date: 19.03.2024
  Time: 13:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>Вопрос №</title>
</head>

<body>
<form method="get" action="insert.jsp" enctype=text/plain>
    <INPUT TYPE="radio" name="command" value="0"/>Run
    <INPUT TYPE="radio" NAME="command" VALUE="1"/>Walk
    <INPUT TYPE="submit" VALUE="submit" />

<a href="send-response">Ответить</a>
<a href="get-finish">Завершить</a>
</body>
<%@include file="footer.jsp"%>