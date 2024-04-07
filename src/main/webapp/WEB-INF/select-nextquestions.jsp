<%--
  Created by IntelliJ IDEA.
  User: scarab
  Date: 02.04.2024
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="include/head.jsp" %>
<head>
    <title>Title</title>
</head>
<body>
<div class="container" style="height: auto">
    <form method="post" class="block">
        <div class="col-md-30 text-end">
            <div class="col-md-25 text-end">
                <c:if test="${!empty response}">
                    <div style="width: 100%; border: 1px solid darkgray;"
                         class="d-flex flex-column flex-md-row p-4 hap-4 py-md-15 align-items-center justify-content-left">
                        <textarea type="text" name="description" value="${response.description}"
                                  style="width: 80%; border: none; height: 80%"
                                  rows="4">${response.description}</textarea>
                        <label style="width: 3%"></label>
                    </div>
                </c:if>
            </div>
            <div style="width: 100%; height: 60%; float: left; border: 1px solid darkgray;overflow: auto;">
                <div>
                    <c:if test="${!empty questions}">
                        <c:forEach var="question" items="${questions}">
                            <div style="width: 100%; border: 1px solid darkgray"
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
                            </div>
                        </c:forEach>
                    </c:if>
<%--                </div>--%>
<%--                <div>--%>
                    <c:if test="${!empty finmessages}">
                        <c:forEach var="finmessage" items="${finmessages}">
                            <div style="width: 100%; border: 1px solid darkgray"
                                 class="d-flex flex-column flex-md-row p-4 hap-4 py-md-15 align-items-center justify-content-left">
                                <input class="form-check-input flex-shrink-0" type="checkbox"
                                       name="questionchk"
                                       id="questionlist${finmessage.id}" value="${finmessage.id}"><br>
                                <label style="width: 80%;"
                                       class="d-flex flex-column flex-md-row p-4 hap-4 py-md-15 align-items-center justify-content-left">${finmessage.description}</label>
                                <label style="width: 3%"></label>
                                <img src="/images/resp-${finmessage.image}" alt="twbs" width="90"
                                     height="90"
                                     class="rounded-circle flex-shrink-0 align-items-right justify-content-right">
                            </div>
                        </c:forEach>
                    </c:if>
                </div>
            </div>
            <div class="col-md-3 text-end d-flex gp-2">
                <button class="btn btn-outline-primary me-2" name="direct" value="prev" type="submit">Назад</button>
                <button class="btn btn-outline-primary me-2" name="direct" value="next" type="submit">Далее</button>
            </div>
        </div>
    </form>
</div>
</body>

