<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="message"/>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/signin.css">
    <jsp:include page="${pageContext.request.contextPath}/css/bootstrap_min.jsp"/>
    <title>Sign in</title>
</head>
<body style="background-color: #F5F5F5;">
<form class="form-signin" method="post" action="${pageContext.request.contextPath}/app/signIn">
    <div class="container"><h1 class="h3 mb-3 font-weight-normal text-center">
        <fmt:message key="signIn.pleaseSignIn"/>
    </h1>
        <%--    Error message--%>
        <c:if test="${not empty invalidCredentials and invalidCredentials eq true}">
            <div class="alert alert-danger text-center" role="alert">
                <fmt:message key="signIn.invalidCredentialsMessage"/>
            </div>
        </c:if>
        <%--    Input fields--%>
        <label for="inputEmail" class="sr-only">Email address</label>
        <input type="email" id="inputEmail" class="form-control"
               placeholder="<fmt:message key="signIn.emailAddressPlaceholder"/> " name="email"
               required
               autofocus
               value="<c:out value="${email}" />"
        >
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword" class="form-control"
               placeholder="<fmt:message key="signIn.passwordPlaceholder" /> " name="password"
               required>
        <div class="text-center mb-3">
            <a href="${pageContext.request.contextPath}/signUp.jsp" class="text-center">
                <fmt:message key="signIn.signUpLink"/>
            </a>
        </div>
        <%--        Sign in button--%>
        <button class="btn btn-lg btn-primary btn-block" type="submit">
            <fmt:message key="signIn.signInButton"/>
        </button>
    </div>
</form>
</body>
</html>