<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="message"/>

<div class="btn-group dropleft">
    <button class="btn btn-outline-primary" type="button" id="dropdownMenuButton" data-toggle="dropdown"
            aria-haspopup="true" aria-expanded="false">
        <img src="${pageContext.request.contextPath}/img/user_logo.svg" alt="user_logo" width="25">
    </button>
    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
        <a class="dropdown-item" href="${pageContext.request.contextPath}/app/profile">
            <fmt:message key="navbar.user.profile" />
        </a>
        <a class="dropdown-item" href="${pageContext.request.contextPath}/app/orders">
            <fmt:message key="navbar.client.myOrders" />
        </a>
        <a class="dropdown-item" href="${pageContext.request.contextPath}/app/logout">
            <fmt:message key="navbar.user.logout" />
        </a>
    </div>
</div>
<jsp:include page="${pageContext.request.contextPath}/js/jquery.jsp"/>
<jsp:include page="${pageContext.request.contextPath}/js/popper_min.jsp"/>
<jsp:include page="${pageContext.request.contextPath}/js/bootstrap_min.jsp"/>