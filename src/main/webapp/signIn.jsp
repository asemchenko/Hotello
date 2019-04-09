<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${not empty param.invalidCredentials}">
    <%--    TODO стыд-позор или качественное решение?) --%>
    <% response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); %>
</c:if>
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
    <div class="container"><h1 class="h3 mb-3 font-weight-normal text-center">Please sign in</h1>
        <%--    Error message--%>
        <c:if test="${not empty param.invalidCredentials and param.invalidCredentials eq true}">
            <div class="alert alert-danger text-center" role="alert">
                Invalid email/password.<br>
                Try again
            </div>
        </c:if>
        <%--    Input fields--%>
        <label for="inputEmail" class="sr-only">Email address</label>
        <input type="email" id="inputEmail" class="form-control" placeholder="Email address" name="email"
               required
               autofocus
               value="<c:out value="${param.email}" />"
        >
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword" class="form-control" placeholder="Password" name="password" required>
        <div class="text-center mb-3">
            <a href="${pageContext.request.contextPath}/signUp.jsp" class="text-center">Do not have an account? Just sign up</a>
        </div>
        <%--        Sign in button--%>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    </div>
</form>
</body>
</html>