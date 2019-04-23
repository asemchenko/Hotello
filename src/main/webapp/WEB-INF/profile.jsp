<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="message"/>

<html>
<head>
    <title><fmt:message key="profile.title"/></title>
    <jsp:include page="${pageContext.request.contextPath}/css/bootstrap_min.jsp"/>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/navbar.jsp"/>
<main role="main">
    <div class="container" style="width: 60%;">
        <div class="card">
            <div class="card-header">
                <h4><fmt:message key="profile.userProfile"/></h4>
            </div>
            <div class="card">
                <div class="card-body">
                    <div class="row">
                        <div class="col-sm-6 text-right">
                            <img alt="User Pic"
                                 height="100" id="profile-image1"
                                 src="https://x1.xingassets.com/assets/frontend_minified/img/users/nobody_m.original.jpg"
                                 width="100">
                        </div>
                        <div class="col-sm-6 my-auto">
                            <h4 style="color:#00b1b1;">
                                <c:out value="${user.firstName}"/>
                                <c:out value="${user.lastName}"/>
                            </h4>
                            </span>
                        </div>
                    </div>
                    <hr style="margin:5px 0 5px 0;">
                    <div class="row">
                        <div class="col-sm-5 col-xs-6 tital "><fmt:message key="profile.firstName"/></div>
                        <div class="col-sm-7 col-xs-6 ">
                            <c:out value="${user.firstName}"/>
                        </div>
                        <div class="clearfix"></div>
                        <div class="bot-border"></div>
                    </div>
                    <div class="row">
                        <div class="col-sm-5 col-xs-6 tital "><fmt:message key="profile.lastName"/></div>
                        <div class="col-sm-7">
                            <c:out value="${user.lastName}"/>
                        </div>
                        <div class="clearfix"></div>
                        <div class="bot-border"></div>
                    </div>
                    <div class="row">
                        <div class="col-sm-5 col-xs-6 tital "><fmt:message key="profile.email"/></div>
                        <div class="col-sm-7">
                            <c:out value="${user.email}"/>
                        </div>
                        <div class="clearfix"></div>
                        <div class="bot-border"></div>
                    </div>
                </div>
            </div>
            <form action="${pageContext.request.contextPath}/app/changePassword" method="post" class="mx-5">
                <div class="panel-heading text-center mt-2">
                    <h4><fmt:message key="profile.changePassword"/></h4>
                </div>
                <c:if test="${not empty invalidPassword and invalidPassword eq true}">
                    <div class="alert alert-danger text-center" role="alert">
                        <fmt:message key="profile.invalidPasswordAlert"/>
                    </div>
                </c:if>
                <div class="w-50 mx-auto">
                    <div>
                        <input class="form-control" id="currentPassword"
                               placeholder="<fmt:message key="profile.oldPasswordPlaceholder"/>"
                               required type="password" name="oldPassword">
                    </div>
                    <div style="margin-top: 10px;">
                        <input class="form-control" id="newPassword"
                               placeholder="<fmt:message key="profile.newPasswordPlaceholder"/>"
                               required type="password" name="newPassword" oninput="checkPasswordsSame()">
                    </div>
                    <div style="margin-top: 10px;">
                        <input class="form-control" id="passwordConfirmation"
                               placeholder="<fmt:message key="profile.newPasswordConfirmPlaceholder"/>"
                               required type="password" name="newPasswordConfirmation" oninput="checkPasswordsSame()">
                    </div>
                    <div class="mx-auto w-75 my-2">
                        <button class="btn btn-lg btn-primary btn-block" type="submit" id="submitButton">
                            <fmt:message key="profile.changePasswordButton"/>
                        </button>
                    </div>
                    <div class="alert alert-danger" role="alert" id="passwordConfirmationFailedAlert"
                         style="display: none">
                        <fmt:message key="passwordConfirmationFailedAlert"/>
                    </div>
                </div>
            </form>
        </div>
    </div>
</main>
<jsp:include page="${pageContext.request.contextPath}/footer.jsp"/>
<script type="text/javascript">
    function checkPasswordsSame() {
        var passwordInput = document.getElementById('newPassword');
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