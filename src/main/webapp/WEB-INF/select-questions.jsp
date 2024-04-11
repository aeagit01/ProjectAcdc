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
</head>
<body>
<div class="container" style="height: auto">
    <form method="post" class="block">
        <h4 class="card-title">Выберите вопросы, которые будут использоваться в квесте. Установите признак "Первый" для
            вопроса с которого будет начинаться квест.</h4>
        <div class="col-md-30 text-end">
            <div class="col-md-25 text-end">
                <c:if test="${!empty quest}">
                    <div style="width: 100%; border: 1px solid darkgray;"
                         class="d-flex flex-column flex-md-row p-4 hap-4 py-md-15 align-items-center justify-content-left">
                        <textarea type="text" name="description" value="${quest.description}"
                                  style="width: 80%; border: none; height: 80%" rows="4">${quest.description}</textarea>
                        <label style="width: 15%"></label>
                        <img src="images/noimage.png" alt="noimage" width="100" height="100"
                             style="align-items: flex-end"
                             class="flex-shrink-0 align-items-left">
                    </div>
                </c:if>
            </div>
            <div><label style="height: 3%"></label></div>
            <div style="width: 100%; height: 60%; float: left; overflow: auto;" name="questionslist">
                <div class="form-group col-md-auto" style="width: 50%;"></div>
                <c:if test="${!empty firstchk}">
                    <div style=" border: 1px solid darkgray;">
                        <label class="list-group-item d-flex gap-2 align-items-center justify-content-left"
                               style="width: 100%">
                            <div class="card-body">
                                <img src="/images/${firstchk.images[0]}" alt="/images/noimage.png" width="90"
                                     height="90"
                                     class="flex-shrink-0 align-items-right justify-content-right">
                            </div>

                            <div style="width: 100%; border: 1px solid darkgray; float: left" name="response"
                                 class="card">
                                <div class="card-body">
                                    <label style="width: 100%;"
                                           class="card-text d-flex flex-column flex-md-row align-items-center justify-content-left">${firstchk.description}</label>
                                </div>
                            </div>
                            <div class="card-body">
                                <label style="width: 3%"></label>
                            </div>
                        </label>
                        <label class="list-group-item d-flex gap-2 align-items-center justify-content-left"
                               style="width: 100%">
                            <div class="card-body">
                                <label style="width: 3%"></label>
                            </div>
                            <div class="row justify-content-between" style="width: 20%">
                                <div class="checklist-strikethrough"></div>
                                <div class="custom-control custom-checkbox col-md-auto">
                                    <input class="custom-control-input" type="checkbox"
                                           name="questionchk"
                                           id="questionlist${firstchk.id}" value="${firstchk.id}" checked>
                                    <span contenteditable="true">Выбран</span>
                                    <input class="custom-control-input" type="checkbox"
                                           name="firstchk"
                                           id="questionlist${firstchk.id}" value="${firstchk.id}" checked>
                                    <span contenteditable="true">Первый</span>
                                </div>
                            </div><!--end of individual checklist item-->
                        </label>
                    </div>
                </c:if>
                <c:if test="${!empty chkquestions}">
                    <c:forEach var="question" items="${chkquestions}">
                        <div style=" border: 1px solid darkgray;">
                            <label class="list-group-item d-flex gap-2 align-items-center justify-content-left"
                                   style="width: 100%">
                                <div class="card-body">
                                    <label style="width: 3%"></label>
                                    <img src="/images/${questionimage}" alt="twbs" width="90"
                                         height="90"
                                         class="flex-shrink-0 align-items-right justify-content-right">
                                </div>

                                <div style="width: 100%; border: 1px solid darkgray; float: left" name="response"
                                     class="card">
                                    <div class="card-body">
                                        <label style="width: 100%;"
                                               class="card-text d-flex flex-column flex-md-row align-items-center justify-content-left">${question.description}</label>
                                    </div>
                                </div>
                                <div class="card-body">
                                    <label style="width: 3%"></label>
                                </div>
                            </label>
                            <label class="list-group-item d-flex gap-2 align-items-center justify-content-left"
                                   style="width: 100%">
                                <div class="card-body">
                                    <label style="width: 3%"></label>
                                </div>
                                <div class="row justify-content-between" style="width: 20%">
                                    <div class="checklist-strikethrough"></div>
                                    <div class="custom-control custom-checkbox col-md-auto">
                                        <input class="custom-control-input" type="checkbox"
                                               name="questionchk"
                                               id="questionlist${question.id}" value="${question.id}" checked>
                                        <span contenteditable="true">Выбран</span>
                                        <input class="custom-control-input" type="checkbox"
                                               name="firstchk"
                                               id="questionlist${question.id}" value="${question.id}">
                                        <span contenteditable="true">Первый</span>
                                    </div>
                                </div><!--end of individual checklist item-->
                            </label>
                        </div>
                    </c:forEach>
                </c:if>
                <c:forEach var="question" items="${questions}">
                    <div style=" border: 1px solid darkgray;">
                        <label class="list-group-item d-flex gap-2 align-items-center justify-content-left"
                               style="width: 100%">
                            <div class="card-body">
                                <label style="width: 3%"></label>
                                <img src="/images/${questionimage}" alt="twbs" width="90"
                                     height="90"
                                     class="flex-shrink-0 align-items-right justify-content-right">
                            </div>

                            <div style="width: 100%; border: 1px solid darkgray; float: left" name="response"
                                 class="card">
                                <div class="card-body">
                                    <label style="width: 100%;"
                                           class="card-text d-flex flex-column flex-md-row align-items-center justify-content-left">${question.description}</label>
                                </div>
                            </div>
                            <div class="card-body">
                                <label style="width: 3%"></label>
                            </div>
                        </label>
                        <label class="list-group-item d-flex gap-2 align-items-center justify-content-left"
                               style="width: 100%">
                            <div class="card-body">
                                <label style="width: 3%"></label>
                            </div>
                            <div class="row justify-content-between" style="width: 20%">
                                <div class="checklist-strikethrough"></div>
                                <div class="custom-control custom-checkbox col-md-auto">
                                    <input class="form-check-input flex-shrink-0" type="checkbox"
                                           name="questionchk"
                                           id="questionlist${question.id}" value="${question.id}">
                                    <span contenteditable="true">Выбран</span>
                                    <input class="form-check-input flex-shrink-0" type="checkbox"
                                           name="firstchk"
                                           id="questionlist${question.id}" value="${question.id}">
                                    <span contenteditable="true">Первый</span>
                                </div>
                            </div><!--end of individual checklist item-->
                        </label>
                    </div>
                </c:forEach>
            </div>
        </div>
        <label style="height: 30px"></label>
        <button class="btn btn-outline-primary me-2 align-items-center justify-content-left" name="direct" value="exit"
                type="submit">Завершить
        </button>
    </form>
</div>
</body>
<%--todo add button to create new question--%>
<%@include file="include/footer.jsp" %>
