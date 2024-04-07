<%--
  Created by IntelliJ IDEA.
  User: scarab
  Date: 24.03.2024
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="include/head.jsp" %>
<head>
    <title>Выберите квест</title>
</head>
<body class="py-3 my-4">
<form method="post">
    <div class="container">
        <c:forEach var="quest" items="${requestScope.quests}">
        <div class="col-md-30 ">
             <label class="list-group-item d-flex gap-2 align-items-center justify-content-left" style="width: 100%">
                <div class="card" style="width: 90%">
                    <div class="card-body">
                        <h5 class="card-title">${quest.name}</h5>
                        <p class="card-text">${quest.description}</p>
                    </div>
                </div>
                <div class="card">
                    <button class="btn btn-primary" type="submit" name="edit" value="${quest.id}">edit</button>
                    <label style="height: 7px;width: 10%"> </label>
                    <button class="btn btn-primary" type="submit" name="start" value="${quest.id}">start</button>
                </div>
            </label>
         </div>
        <label style="height: 5px"> </label>
        </c:forEach>

        <div class="col-md-3 text-end">
            <label class="list-group-item d-flex gap-2">
                <button class="btn btn-outline-primary me-2" name="OK" type="submit">Create</button>
            </label>
        </div>

</form>
</body>

<%--//todo add button for create new qwest--%>

<%@include file="include/footer.jsp" %>
