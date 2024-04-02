<%--
  Created by IntelliJ IDEA.
  User: scarab
  Date: 30.03.2024
  Time: 22:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="head.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <title>Title</title>
    <script>
        function openPopup(questionID) {
                window.open("http://localhost:8080/select-response?q=1", "_blank", "width=400, height=400, alwaysRaised=yes, modal=yes");
        }
    </script>
</head>
</head>
<body>
<div class="container" style="height: auto">
    <form method="post" class="block">
        <div class="col-md-30 text-end">
            <div class="col-md-25 text-end">
                <c:if test="${!empty quest}">
                    <div style="width: 100%; border: 1px solid darkgray;"
                         class="d-flex flex-column flex-md-row p-4 hap-4 py-md-15 align-items-center justify-content-left">
                        <textarea type="text" name="description" value="${quest.description}"
                                  style="width: 80%; border: none; height: 80%" rows="4">${quest.description}</textarea>
                        <label style="width: 3%"></label>
                        <img src="/images/pic-${quest.images[0]}" alt="twbs" width="100" height="100"
                             class="rounded-circle flex-shrink-0 align-items-left">
                    </div>
                </c:if>
            </div>
            <div style="width: 100%; height: 60%; float: left; border: 1px solid darkgray;overflow: auto;"
                 name="responseslist">
                <c:if test="${!empty chkquestions}">
                    <c:forEach var="question" items="${chkquestions}">
                        <div style="width: 90%; border: 1px solid darkgray;" name="question"
                             class="d-flex flex-column flex-md-row p-4 hap-4 py-md-15 align-items-center justify-content-left">
                            <input class="form-check-input flex-shrink-0" type="checkbox"
                                   name="responsechk"
                                   id="questionlist${question.id}" value="${question.id}" checked><br>
                            <label style="width: 3%"></label>
                            <label style="width: 80%">${question.description}</label>
                            <label style="width: 3%"></label>
                            <img src="/images/resp-${question.images[0]}" alt="twbs" width="90"
                                 height="90"
                                 class="rounded-circle flex-shrink-0 align-items-right justify-content-right">
                            <div style="width: 10%">
                                <label style="width: 3%"></label>
                                <button type="submit" name="edit" value="${question.id}">edit</button>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
                <c:forEach var="question" items="${questions}">
                    <div style="width: 100%; border: 1px solid darkgray;" name="response"
                         class="d-flex flex-column flex-md-row p-4 hap-4 py-md-15 align-items-center justify-content-left">
                        <input class="form-check-input flex-shrink-0" type="checkbox"
                               name="questionchk"
                               id="questionlist${question.id}" value="${question.id}"><br>
                        <label style="width: 80%;"
                               class="d-flex flex-column flex-md-row p-4 hap-4 py-md-15 align-items-center justify-content-left">${question.description}</label>
                        <label style="width: 3%"></label>
                        <img src="/images/resp-${question.images[0]}" alt="twbs" width="90"
                             height="90"
                             class="rounded-circle flex-shrink-0 align-items-right justify-content-right">
                        <div style="width: 10%">
                            <label style="width: 3%"></label>
                            <c:set var = "qId" value="${question.id}"></c:set>
                            <button name="edit" onclick="openPopup(qId)" value="${question.id}">edit</button>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div>
<%--              <label style="width: 3%"></label>--%>
            </div>
            <div class="col-md-3 text-end d-flex gp-2">
                <button class="btn btn-outline-primary me-2" name="direct" value="prev" type="submit">Назад</button>
                <button class="btn btn-outline-primary me-2" name="direct" value="next" type="submit">Далее</button>
            </div>
        </div>
    </form>
</div>
</body>

<%@include file="footer.jsp" %>
