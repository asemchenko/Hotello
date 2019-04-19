<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="message"/>

<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title><fmt:message key="signUp.title"/></title>
    <jsp:include page="${pageContext.request.contextPath}/css/bootstrap_min.jsp"/>
</head>
<body>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header"><fmt:message key="signUp.register"/></div>
                <div class="card-body">
                    <%--todo поменяй ссылку action в форме --%>
                    <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/app/signUp">

                        <div class="form-group">
                            <label for="firstName" class="cols-sm-2 control-label">
                                <fmt:message key="signUp.firstNameLabel"/>
                            </label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user fa"
                                                                       aria-hidden="true"></i></span>
                                    <input type="text" class="form-control" name="firstName" id="firstName"
                                           placeholder="<fmt:message key="signUp.firstNamePlaceholder"/>"/>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="lastName" class="cols-sm-2 control-label">
                                <fmt:message key="signUp.lastNameLabel"/>
                            </label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user fa"
                                                                       aria-hidden="true"></i></span>
                                    <input type="text" class="form-control" name="lastName" id="lastName"
                                           placeholder="<fmt:message key="signUp.lastNamePlaceholder"/>"/>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="email" class="cols-sm-2 control-label">
                                <fmt:message key="signUp.emailLabel"/>
                            </label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
                                    <input type="text" class="form-control" name="email" id="email"
                                           placeholder="<fmt:message key="signUp.emailPlaceholder"/>"/>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="password" class="cols-sm-2 control-label"><fmt:message
                                    key="signUp.passwordLabel"/></label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                                    <input type="password" class="form-control" name="password" id="password"
                                           placeholder="<fmt:message key="signUp.passwordPlaceholder"/>"/>
                                </div>
                            </div>
                        </div>
                        <%--                        TODO реализуй проверку что пароли совпадают--%>
                        <div class="form-group">
                            <label for="confirm" class="cols-sm-2 control-label"><fmt:message
                                    key="signUp.confirmPasswordLabel"/></label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                                    <input type="password" class="form-control" name="confirm" id="confirm"
                                           placeholder="<fmt:message key="signUp.confirmPasswordPlaceholder"/>"/>
                                </div>
                            </div>
                        </div>
                        <div class="form-group ">
                            <input type="submit" class="btn btn-primary btn-lg btn-block login-button"
                                   value="<fmt:message key="signUp.registerButton"/>"/>
                        </div>
                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
<jsp:include page="${pageContext.request.contextPath}/footer.jsp"/>
</body>
</html>