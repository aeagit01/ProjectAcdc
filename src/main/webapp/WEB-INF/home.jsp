<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="include/head.jsp" %>
<body class="py-3 my-4">
<div class="container">
    <h1><%= "Привет готов пройти квест!" %>
    </h1>
    <br/>
    <form class="form-horizontal" action="login" method="post">
        <fieldset>
            <!-- Form Name -->
            <legend> Представься *)</legend>
            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="userLogin">Имя</label>
                <div class="col-md-4">
                    <input id="userLogin" name="login" type="text" placeholder="set login" class="form-control input-md"
                           required=""
                           value="Carl">
                </div>
            </div>
            <!-- Password input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="userPassword">Пароль</label>
                <div class="col-md-4">
                    <input id="userPassword" name="password" type="password" placeholder="pass req"
                           class="form-control input-md" required=""
                           value="admin">

                </div>
            </div>
            <!-- Button -->
            <div class=" form-group">
                <label class="col-md-4 control-label" for="submit"></label>
                <div class="col-md-4">
                    <button id="submit" name="loginButton"
                            class="btn btn-success">Войти
                    </button>
                </div>
            </div>

        </fieldset>
    </form>

    <%--    <a href="list-score">Рекорды</a>--%>
    <a href="select-quest">Начать квест</a>
    <%--    <a href="list-user">Пользователи</a>--%>
</div>
</body>
<%@include file="include/footer.jsp" %>