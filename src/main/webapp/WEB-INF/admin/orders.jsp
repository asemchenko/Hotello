<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                Нет заказов
            </div>
        </c:when>
        <c:otherwise>
            <c:forEach var="order" items="${orders}">
                <div class="my-3 p-3 bg-white rounded shadow-sm justify-content-center mx-auto border border-dark"
                     style="width: 50%">
                    <div class="row">
                        <div class="col-sm"><h6 class="border-bottom border-gray pb-2 mb-0 text-left">Заказ №<c:out
                                value="${order.id}"/></h6>
                        </div>
                        <div class="col-sm"></div>
                        <div class="col-sm">
                            <h6 class="border-bottom border-gray pb-2 mb-0 text-right">
                                Дата заказа - <c:out value="${order.creationTime}"/>
                            </h6>
                        </div>
                    </div>
                    <div class="media text-muted pt-3">

                        <img class="mr-2 my-2" src="${pageContext.request.contextPath}/img/apartmentLogo.png"
                             alt="apartment logo"
                             width="32" height="32">
                        <p class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
                            <strong class="d-block text-gray-dark"><c:out value="${order.apartment.title}"/></strong>
                            <a href="${pageContext.request.contextPath}/app/apartment?apartment_id=<c:out value="${order.apartment.id}" />">
                                Просмотреть
                            </a>
                        </p>
                        <h4 class="card-title pricing-card-title mr-0">
                            $<c:out value="${order.pricePerDayAtTheTimeOfOrder}"/>
                            <small class="text-muted">/ day</small>
                        </h4>
                    </div>
                    <div class="media text-muted">
                        <img class="mr-2 my-2" src="${pageContext.request.contextPath}/img/checkInLogo.png"
                             alt="check in logo"
                             width="32" height="32">
                        <p class="media-body my-3">
                            Дата въезда - <c:out value="${order.checkInDate}"/>
                        </p>
                    </div>
                    <div class="media text-muted">
                        <img class="mr-2 my-2" src="${pageContext.request.contextPath}/img/checkOutLogo.png"
                             alt="check in logo"
                             width="32" height="32">
                        <p class="media-body my-3">
                            Дата выезда - <c:out value="${order.checkOutDate}"/>
                        </p>
                    </div>
                    <div class="row">
                        <div class="col-sm ">
                            <h6 class="border-bottom border-gray text-left text-muted">
                                СТАТУС ЗАКАЗА - <c:out value="${order.status}"/>
                            </h6>
                        </div>
                        <div class="col-sm"><h4 class="card-title pricing-card-title mr-0 text-right text-muted">
                            Total $<c:out value="${order.totalPrice}"/>
                        </h4></div>
                    </div>
                    <c:if test="${order.status eq 'CONFIRMATION_EXPECTED'}">
                        <hr>
                        <div class="row">
                                <%--                            FIXME получается что админ не может принять уже отмененный заказ, даже если он просто ошибся кнопкой. Исправь это--%>
                            <div class="col-sm text-center">
                                <form action="${pageContext.request.contextPath}/app/confirmOrder" method="post">
                                    <button type="submit" class="btn btn-success">Одобрить</button>
                                    <input type="text" value="${order.id}" hidden name="orderId">
                                </form>
                            </div>
                            <div class="col-sm text-center">
                                <form action="${pageContext.request.contextPath}/app/disapproveOrder" method="post">
                                    <button type="submit" class="btn btn-danger">Отклонить</button>
                                    <input type="text" value="${order.id}" hidden name="orderId">
                                </form>
                            </div>
                        </div>
                        <%--                        TODO add user info--%>
                    </c:if>
                    <hr>
                        <%--Client full name--%>
                    <div class="row">
                        <div class="col-sm">
                            Client full name
                        </div>
                        <div class="col-sm">
                                ${order.user.fullName}
                        </div>
                    </div>
                        <%--Client email--%>
                    <div class="row">
                        <div class="col-sm">
                            Client email
                        </div>
                        <div class="col-sm">
                                ${order.user.email}
                        </div>
                    </div>
                </div>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</main>
<jsp:include page="${pageContext.request.contextPath}/footer.jsp"/>
</body>
</html>