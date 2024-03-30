<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: scarab
  Date: 25.03.2024
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="head.jsp" %>
<head>
    <title>Title</title>
</head>
<body>
<div class="container">
    <form method="post" class="block">
        <div class="col-md-30 text-end">
            <div class="col-md-25 text-end">
                <label class="list-group-item d-flex gap-2 align-items-center justify-content-left" style="width: 100%">
                    <c:set var="questID" value="${question.questID}"/>
                    <div style="width: 70%" class="d-flex flex-column flex-md-row p-4 hap-4 py-md-15 align-items-center justify-content-left" >
                        ${question.description}
                    </div>
                    <div style="width: 30%"  >
                        <img src="/images/pic-${question.images[0]}" alt="twbs" width="100" height="100" class="rounded-circle flex-shrink-0 align-items-left">
                    </div>
                </label>
            </div>
            <table class="d-flex flex-column flex-md-row p-4 hap-4 py-md-15 ">
                <c:if test="${!empty responses}">
                    <c:forEach var="response" items="${responses}">
                        <tr>
                            <td style="width: 700px">
                                <label class="list-group-item d-flex gap-3 py-md-15">
                                    <input class="form-check-input flex-shrink-0" type="radio" name="responselist"
                                           id="responselist + ${response.id}" value="${response.id}" checked="">
                                    <div >
                                        ${response.description}
                                        <small class="d-block text-body-secondary"></small>
                                    </div>
                                </label>
                            </td>
                        <%--                            <input type="radio" class="form-check-input flex-shrink-0" name="responselist"--%>
                        <%--                                                                 value="${response.id}">${response.description}</td>--%>
                            <td>
                                <label class="list-group-item d-flex gap-2 align-items-right justify-content-right">
                                <span>
                                    <img src="/images/resp-${response.images[0]}" alt="twbs" width="100" height="100" class="rounded-circle flex-shrink-0 align-items-right justify-content-right">
                        <%--        <small class="d-block text-body-secondary">${response.images[0]}</small>--%>
                                </span>
                                </label>
                            </td>
                        <%--    <td class="col-4 themed-grid-col"><img src="${response.images[0]}"></td>--%>
                        </tr>
                    </c:forEach>
                </c:if>
            </table>

            <div class="col-md-3 text-end">
                <label class="list-group-item d-flex gap-2">
                    <button class="btn btn-outline-primary me-2" name="OK" type="submit">Ответить</button>
                </label>
            </div>


        </div>
    </form>
</div>
</body>

<%@include file="footer.jsp" %>
