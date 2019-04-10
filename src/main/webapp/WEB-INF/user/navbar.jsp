<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="btn-group dropleft">
    <button class="btn btn-outline-primary" type="button" id="dropdownMenuButton" data-toggle="dropdown"
            aria-haspopup="true" aria-expanded="false">
        <img src="${pageContext.request.contextPath}/img/user_logo.svg" alt="user_logo" width="25">
    </button>
    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
        <a class="dropdown-item" href="${pageContext.request.contextPath}/app/profile">Профиль</a>
        <a class="dropdown-item" href="${pageContext.request.contextPath}/app/orders">Мои заказы</a>
        <a class="dropdown-item" href="${pageContext.request.contextPath}/app/logout">Выйти</a>
    </div>
</div>
<jsp:include page="${pageContext.request.contextPath}/js/jquery.jsp"/>
<jsp:include page="${pageContext.request.contextPath}/js/popper_min.jsp"/>
<jsp:include page="${pageContext.request.contextPath}/js/bootstrap_min.jsp"/>