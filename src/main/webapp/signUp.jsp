<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                    <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/app/signUp">
                        <c:if test="${not empty duplicateEmail and duplicateEmail eq true}">
                            <div class="alert alert-danger text-center" role="alert">
                                <fmt:message key="signUp.duplicateEmailMessage"/>
                            </div>
                        </c:if>
                        <div class="form-group">
                            <label for="firstName" class="cols-sm-2 control-label">
                                <fmt:message key="signUp.firstNameLabel"/>
                            </label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user fa"
                                                                       aria-hidden="true"></i></span>
                                    <input type="text" class="form-control" name="firstName" id="firstName"
                                           placeholder="<fmt:message key="signUp.firstNamePlaceholder"/>"
                                           minlength="4"
                                           value="<c:out value="${firstName}"/>"
                                    />
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
                                           placeholder="<fmt:message key="signUp.lastNamePlaceholder"/>"
                                           minlength="4"
                                           value="<c:out value="${lastName}" />"
                                    />
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
                                           pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"
                                           placeholder="<fmt:message key="signUp.emailPlaceholder"/>"
                                           value="<c:out value="${email}"/>"
                                    />
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
                                           placeholder="<fmt:message key="signUp.passwordPlaceholder"/>"
                                           minlength="4"
                                           oninput="checkPasswordsSame()"/>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="passwordConfirmation" class="cols-sm-2 control-label"><fmt:message
                                    key="signUp.confirmPasswordLabel"/></label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                                    <input type="password" class="form-control" name="confirm" id="passwordConfirmation"
                                           placeholder="<fmt:message key="signUp.confirmPasswordPlaceholder"/>"
                                           oninput="checkPasswordsSame()"/>
                                </div>
                            </div>
                        </div>
                        <div class="form-group ">
                            <input type="submit" class="btn btn-primary btn-lg btn-block login-button"
                                   value="<fmt:message key="signUp.registerButton"/>" id="submitButton"/>
                        </div>
                        <div class="alert alert-danger" role="alert" id="passwordConfirmationFailedAlert"
                             style="display: none">
                            <fmt:message key="passwordConfirmationFailedAlert"/>
                        </div>
                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
<jsp:include page="${pageContext.request.contextPath}/footer.jsp"/>
<script type="text/javascript">
    function checkPasswordsSame() {
        var passwordInput = document.getElementById('password');
        var passwordConfirmationInput = document.getElementById('passwordConfirmation');
        var submitButton = document.getElementById('submitButton');
        var alertLabel = document.getElementById('passwordConfirmationFailedAlert');
        if (passwordInput.value !== passwordConfirmationInput.value) {
            submitButton.disabled = true;
            alertLabel.style.display = 'block';
        } else {
            submitButton.disabled = false;
            alertLabel.style.display = 'none';

        }
    }
</script>
</body>
</html>