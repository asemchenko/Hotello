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
    <title><c:out value="${apartment.title}"/></title>
</head>
<body style="background-color: #F8F9FA;">
<jsp:include page="${pageContext.request.contextPath}/navbar.jsp"/>
<main role="main">
    <div class="container">

        <div class="row justify-content-center">

            <div class="col-lg-8">

                <!-- Title -->
                <h1 class="mt-4"><c:out value="${apartment.title}"/></h1>

                <hr>

                <!-- Preview Image -->
                <div class="text-center">
                    <img class="img-fluid rounded" src="${pageContext.request.contextPath}/hotel_sketch.jpg"
                         alt="apartments photo">
                </div>

                <hr>

                <!-- Post Content -->
                <p class="lead">
                    <c:out value="${apartment.description}"/>
                </p>
            </div>

            <div class="col-md-4">

                <!-- Categories Widget -->
                <div class="card my-4">
                    <h5 class="card-header"><fmt:message key="apartmentDetail.characteristics"/></h5>
                    <div class="card-body">
                        <div class="container text-center">
                            <link rel="stylesheet"
                                  href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
                            <div class="container"><c:forEach begin="1" end="${apartment.starsAmount}" varStatus="loop">
                                <span class="fa fa-star checked"></span>
                            </c:forEach></div>
                            <ul>
                                <li>
                                    <fmt:message key="apartmentDetail.placesAmount"/>
                                    <c:out value="${apartment.placesAmount}"/>
                                </li>
                                <li>
                                    <fmt:message key="apartmentDetail.roomsAmount"/>
                                    <c:out value="${apartment.roomsAmount}"/>
                                </li>
                            </ul>
                            <div class="container text-center">
                                <h4 class="card-title pricing-card-title">
                                    <fmt:formatNumber type="CURRENCY" value="${apartment.pricePerDay / 100}"
                                                      maxFractionDigits="2"/>
                                    <small class="text-muted">/ <fmt:message key="perDay"/></small>
                                </h4>
                            </div>
                        </div>
                        <hr>
                        <div class="text-center">
                            <form action="${pageContext.request.contextPath}/app/booking" method="post">
                                <input type="text" name="apartmentId" value="${apartment.id}" hidden>
                                <button class="btn btn-outline-success" style="margin-left: 10px"><fmt:message
                                        key="apartmentDetail.bookButton"/></button>
                            </form>
                        </div>
                    </div>
                </div>

            </div>
        </div>

    </div>
</main>
<jsp:include page="${pageContext.request.contextPath}/footer.jsp"/>
</body>
</html>
