<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="message"/>
<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
    <a class="navbar-brand my-0 mr-md-auto font-weight-normal" href="${pageContext.request.contextPath}/">Hotello</a>
    <nav class="my-2 my-md-0 mr-md-3">
        <a class="p-2 text-dark" href="#"><fmt:message key="navbar.booking_conditions"/> </a>
        <a class="p-2 text-dark" href="#"><fmt:message key="navbar.payment"/></a>
        <a class="p-2 text-dark" href="#"><fmt:message key="navbar.support"/></a>
        <a class="p-2 text-dark" href="#"><fmt:message key="navbar.aboutUs"/></a>
    </nav>
    <c:choose>
        <c:when test="${not empty navbarPath}">
            <jsp:include page="${navbarPath}"/>
        </c:when>
        <c:otherwise>
            <a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/signIn.jsp"
               style="margin-left: 10px"><fmt:message key="navbar.guest.signIn"/></a>
        </c:otherwise>
    </c:choose>
</div>
