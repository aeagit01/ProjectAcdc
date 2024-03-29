<%--
  Created by IntelliJ IDEA.
  User: scarab
  Date: 24.03.2024
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="head.jsp" %>
<head>
    <title>Выберите квест</title>
</head>
<body class="py-3 my-4">
<div class="container">
    <c:forEach var="quest" items="${requestScope.quests}">
    <div class="col-md-30 ">
        <div class="col-md-25">
            <label class="list-group-item d-flex gap-2 align-items-center justify-content-left" style="width: 100%">
                <div
                     class="d-flex flex-column flex-md-row p-4 hap-4 py-md-15 align-items-center justify-content-left">
                    <a href="quest?id=${quest.id}">${quest.name}</a>
                </div>
                <div style="width: 70%">
                        ${quest.description}
                </div>
            </label>
        </div>
<%--        <p>--%>
<%--            <a href="quest?id=${quest.id}">${quest.name} " | " ${quest.description} </a>--%>
<%--        </p>--%>
        </c:forEach>
    </div>
</body>
<%@include file="footer.jsp" %>
