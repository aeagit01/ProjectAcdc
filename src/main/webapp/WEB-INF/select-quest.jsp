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
<form method="post">
    <div class="container">
        <c:forEach var="quest" items="${requestScope.quests}">
        <div class="col-md-30 ">
            <div class="col-md-25">
                <label class="list-group-item d-flex gap-2 align-items-center justify-content-left" style="width: 100%">
                    <div
                            class="d-flex flex-column flex-md-row p-4 hap-4 py-md-15 align-items-center justify-content-left">
                        <a href="quest?id=${quest.id}">${quest.name}</a>
                    </div>
                    <c:set var="questID" value="${quest.id}"></c:set>
                    <div style="width: 70%">
                            ${quest.description}
                        <label style="width: 3%"></label>
                        <button type="submit" name="edit" value="${quest.id}">edit</button>
                        <button type="submit" name="start" value="${quest.id}">start</button>
                    </div>
                </label>
            </div>
            </c:forEach>
        </div>
</form>
</body>
<%@include file="footer.jsp" %>
