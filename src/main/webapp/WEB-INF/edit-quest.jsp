<%--
  Created by IntelliJ IDEA.
  User: scarab
  Date: 30.03.2024
  Time: 22:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="include/head.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <title>Title</title>
</head>
<body>
<div class="container" style="height: auto">
    <form method="post" class="block">
        <div class="col-md-30 text-begin">
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
            <div style="width: 100%;" name="response"
                 class="d-flex flex-column flex-md-row p-4 hap-4 py-md-15 align-items-center justify-content-left">
                <label style="width: 3%"></label>
                <label style="width: 80%" >Определить список вопросов</label>
                <label style="width: 3%"></label>
                <button class="btn btn-outline-primary me-2" name="direct" value="selectquestion" type="submit">Изменить</button>
            </div>
            <div style="width: 100%;" name="response"
                 class="d-flex flex-column flex-md-row p-4 hap-4 py-md-15 align-items-center justify-content-left">
                <label style="width: 3%"></label>
                <label style="width: 80%" >Сопоставить список ответов</label>
                <label style="width: 3%"></label>
                <button class="btn btn-outline-primary me-2" name="direct" value="selectresponse" type="submit">Изменить</button>
            </div>
            <div style="width: 100%;" name="response"
                 class="d-flex flex-column flex-md-row p-4 hap-4 py-md-15 align-items-center justify-content-left">
                <label style="width: 3%"></label>
                <label style="width: 80%" >Определить список следующих за ответом вопросов</label>
                <label style="width: 3%"></label>
                <button class="btn btn-outline-primary me-2" name="direct" value="selectnext" type="submit">Изменить</button>
            </div>
            <div class="col-md-3 text-end" >
                <label class="list-group-item d-flex gap-2" >
                    <button class="btn btn-outline-primary me-2" name="direct" value="exit" type="submit">Закончить</button>
<%--                    <button class="btn btn-outline-primary me-2" name="direct" value="next" type="submit">Далее</button>--%>
                </label>
            </div>
        </div>
    </form>
</div>
</body>

<%@include file="include/footer.jsp" %>
