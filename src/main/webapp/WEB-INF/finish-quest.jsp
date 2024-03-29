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
                    <div style="width: 70%" class="d-flex flex-column flex-md-row p-4 hap-4 py-md-15 align-items-center justify-content-left" >
                        ${question.description}
                    </div>
                    <div style="width: 30%"  >
                        <img src="/images/pic-${question.images[0]}" alt="twbs" width="100" height="100" class="rounded-circle flex-shrink-0 align-items-left">
                    </div>
                </label>
            </div>
            <div class="col-md-3 text-end">
                <label class="list-group-item d-flex gap-2">
                    <button class="btn btn-outline-primary me-2" name="OK" type="submit" >ОК</button>
                </label>
            </div>


        </div>
    </form>
</div>
</body>

<%@include file="footer.jsp" %>
