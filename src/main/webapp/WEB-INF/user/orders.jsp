<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="message"/>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <style type="text/css">
        .checked {
            color: orange;
        }
    </style>
    <jsp:include page="${pageContext.request.contextPath}/css/bootstrap_min.jsp"/>
    <title>Hotello</title>
</head>
<body style="background-color: #F8F9FA;">
<jsp:include page="${pageContext.request.contextPath}/navbar.jsp"/>
<main role="main">
    <c:choose>
        <c:when test="${empty orders}">
            <div class="alert alert-warning text-center w-50 mx-auto" role="alert">
                <fmt:message key="orders.noOrdersYet"/>
            </div>
        </c:when>
        <c:otherwise>
            <c:forEach var="order" items="${orders}">
                <div class="my-3 p-3 bg-white rounded shadow-sm justify-content-center mx-auto border border-dark"
                     style="width: 50%">
                    <div class="row">
                        <div class="col-sm"><h6 class="border-bottom border-gray pb-2 mb-0 text-left">
                                <fmt:message key="orders.orderNumber"/>
                                <fmt:formatNumber value="${order.id}"/>
                        </div>
                        <div class="col-sm"></div>
                        <div class="col-sm">
                            <h6 class="border-bottom border-gray pb-2 mb-0 text-right">
                                <fmt:message key="orders.orderCreationDate"/>
<%--                                FIXME--%>
                               <%-- <fmt:parseDate value="${order.creationTime}" type="BOTH" pa
                                <fmt:formatDate value="${order.creationTime}" type="BOTH"/></h6>--%>
                        </div>
                    </div>
                    <div class="media text-muted pt-3">

                        <img class="mr-2 my-2" src="${pageContext.request.contextPath}/img/apartmentLogo.png"
                             alt="apartment logo"
                             width="32" height="32">
                        <p class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
                            <strong class="d-block text-gray-dark"><c:out value="${order.apartment.title}"/></strong>
                            <a href="${pageContext.request.contextPath}/app/apartment?apartmentId=<c:out value="${order.apartment.id}" />">
                                <fmt:message key="orders.viewApartment"/>
                            </a>
                        </p>
                        <h4 class="card-title pricing-card-title mr-0">
                            <fmt:formatNumber type="CURRENCY" value="${order.pricePerDayAtTheTimeOfOrder / 100}"
                                              maxFractionDigits="2"/>
                            <small class="text-muted">/ <fmt:message key="orders.perDay"/></small>
                        </h4>
                    </div>
                    <div class="media text-muted">
                        <img class="mr-2 my-2" src="${pageContext.request.contextPath}/img/checkInLogo.png"
                             alt="check in logo"
                             width="32" height="32">
                        <p class="media-body my-3">
                            <fmt:message key="orders.checkInDate"/>
                            <%--FIXME
                            <fmt:formatDate value="${order.checkInDate}"/>--%>
                        </p>
                    </div>
                    <div class="media text-muted">
                        <img class="mr-2 my-2" src="${pageContext.request.contextPath}/img/checkOutLogo.png"
                             alt="check in logo"
                             width="32" height="32">
                        <p class="media-body my-3">
                            <fmt:message key="orders.checkOutDate"/>
                            <%--FIXME
                            <fmt:formatDate value="${order.checkOutDate}"/>--%>
                        </p>
                    </div>
                    <div class="row">
                        <div class="col-sm ">
                            <h6 class="border-bottom border-gray text-left text-muted">
                                <fmt:message key="orders.orderStatus"/>
                                <c:out value="${order.status}"/>
                            </h6>
                        </div>
                        <div class="col-sm"><h4 class="card-title pricing-card-title mr-0 text-right text-muted">
                            <fmt:message key="orders.total"/>
                            <fmt:formatNumber type="CURRENCY" value="${order.totalPrice / 100}" maxFractionDigits="2"/>
                        </h4></div>
                    </div>
                    <c:if test="${order.status eq 'PAYMENT_EXPECTED'}">
                        <div class="row justify-content-center">
                            <form method="post" action="${pageContext.request.contextPath}/app/payForOrder">
                                <button class="btn btn-success"><fmt:message key="orders.payForOrder"/></button>
                                <input type="text" value="${order.id}" name="orderId" hidden>
                            </form>
                        </div>
                    </c:if>
                </div>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</main>
<jsp:include page="${pageContext.request.contextPath}/footer.jsp"/>
</body>
</html>
