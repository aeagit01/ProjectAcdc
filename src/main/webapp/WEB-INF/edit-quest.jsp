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
</head>
<body>
<div class="container" style="height: auto">
    <form method="post" class="block">
        <div class="col-md-30 text-end">
            <div class="col-md-25 text-end">
                <c:set var="newQuestion" value="${newQuestion}"/>
                    <c:if test="${!empty question}">
                        <c:set var="questID" value="${question.questID}"/>
                        <div style="width: 100%"
                             class="d-flex flex-column flex-md-row p-4 hap-4 py-md-15 align-items-center justify-content-left" >
                            <textarea type="text"  name="description" value="${question.description}" style="width: 80%; height: 80%" rows="4">${question.description}</textarea>
                            <label style="width: 3%"></label>
                            <img src="/images/pic-${question.images[0]}" alt="twbs" width="100" height="100"
                                 class="rounded-circle flex-shrink-0 align-items-left">
                        </div>
                    </c:if>
            </div>
            <table class="d-flex flex-column flex-md-row p-4 hap-4 py-md-15 " style="width: 100%">
                <tbody style="width: 100%">
<%--                <c:if test="${!empty responses}">--%>
                        <tr>
                            <td style="width: 80%; border: darkgrey; border: thin;"  >
                                <div style="width: 100%; float: left; scrollbar-width: thin; border: darkgrey; border-width: thin"  name="responseslist" >
                                    <c:if test="${!empty responses}">
                                        <c:forEach var="response" items="${chkedresponses}">
                                            <div style="width: 100%;" name="response"
                                                 class="d-flex flex-column flex-md-row p-4 hap-4 py-md-15 align-items-center justify-content-left">
                                                <input class="form-check-input flex-shrink-0" type="checkbox"
                                                       name="responsechk"
                                                       id="questionlist${response.id}" value="${response.id}" checked><br>
                                                <label style="width: 3%"></label>
                                                <label style="width: 80%" >${response.description}</label>
                                                <label style="width: 3%"></label>
                                                <img src="/images/resp-${response.images[0]}" alt="twbs" width="90"
                                                     height="90"
                                                     class="rounded-circle flex-shrink-0 align-items-right justify-content-right">
                                            </div>
                                        </c:forEach>
                                    </c:if>
                                    <c:forEach var="response" items="${responses}">
                                        <div style="width: 100%;"  name="response"
                                            class="d-flex flex-column flex-md-row p-4 hap-4 py-md-15 align-items-center justify-content-left">
                                            <input class="form-check-input flex-shrink-0" type="checkbox"
                                                   name="responsechk"
                                                   id="questionlist${response.id}" value="${response.id}"><br>
                                            <label style="width: 3%"></label>
                                            <label style="width: 80%">${response.description}</label>
                                            <label style="width: 3%"></label>
                                            <img src="/images/resp-${response.images[0]}" alt="twbs" width="90"
                                                 height="90"
                                                 class="rounded-circle flex-shrink-0 align-items-right justify-content-right">
                                        </div>
                                    </c:forEach>
                                </div>
                            </td>
                        </tr>
                </tbody>
            </table>

            <div class="col-md-3 text-end" >
                <label class="list-group-item d-flex gap-2" >
                    <button class="btn btn-outline-primary me-2" name="direct" value="prev" type="submit">Назад</button>
                    <button class="btn btn-outline-primary me-2" name="direct" value="next" type="submit">Далее</button>
                </label>
            </div>
        </div>
    </form>
</div>
</body>

<%@include file="footer.jsp" %>
