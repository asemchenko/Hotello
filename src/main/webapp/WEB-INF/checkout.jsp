<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="message"/>

<jsp:useBean id="now" class="java.util.Date"/>
<fmt:formatDate var="nowDate" value="${now}" pattern="yyyy-MM-dd"/>

<html>
<head>
    <title><fmt:message key="checkout.title"/></title>
    <jsp:include page="${pageContext.request.contextPath}/css/bootstrap_min.jsp"/>
    <style type="text/css">
        .btn-link {
            border: none;
            outline: none;
            background: none;
            cursor: pointer;
            color: #0000EE;
            padding: 0;
            text-decoration: underline;
            font-family: inherit;
            font-size: inherit;
        }
    </style>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/navbar.jsp"/>
<main role="main">
    <div class="container">
        <div class="py-5 text-center">
            <h2><fmt:message key="checkout.header"/></h2>
            <p class="lead">
                <fmt:message key="checkout.instructionsParagraph"/>
            </p>
        </div>

        <div class="row">
            <div class="col-md-4 order-md-2 mb-4">
                <h4 class="d-flex justify-content-between align-items-center mb-3">
                    <span class="text-muted">
                        <fmt:message key="checkout.yourCartSpan"/>
                    </span>
                    <span class="badge badge-secondary badge-pill">1</span>
                </h4>
                <div class="row border rounded">
                    <div class="col">
                        <form action="${pageContext.request.contextPath}/app/apartment" method="get" target="_blank">
                            <input type="text" name="apartmentId" value="${apartment.id}" hidden>
                            <button type="submit" class="btn-link">${apartment.title}</button>
                        </form>
                    </div>
                    <div class="col">
                        <span class="text-muted" id="apartmentPrice">
                            <fmt:formatNumber type="CURRENCY" maxFractionDigits="2"
                                              value="${apartment.pricePerDay / 100}"/>
                            / <fmt:message key="perDay"/>
                            </span>
                        <br>
                        <span class="text-muted" id="daysAmount" style="visibility: hidden;">x5 days</span>
                    </div>
                </div>
            </div>
            <div class="col-md-8 order-md-1">
                <h4 class="mb-3">
                    <fmt:message key="checkout.orderInfo"/>
                </h4>
                <form method="post" action="${pageContext.request.contextPath}/app/makeOrder">
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="firstName"><fmt:message key="checkout.firstNameLabel"/></label>
                            <input class="form-control" id="firstName" placeholder="" required type="text"
                                   value="${user.firstName}" disabled>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="lastName">
                                <fmt:message key="checkout.lastNameLabel"/>
                            </label>
                            <input class="form-control" id="lastName" placeholder="" required type="text"
                                   value="${user.lastName}" disabled>
                        </div>
                    </div>

                    <div class="mb-3 w-50 mx-auto">
                        <label for="email"><fmt:message key="checkout.emailLabel"/></label>
                        <input class="form-control" id="email" type="email" value="${user.email}" disabled>
                    </div>

                    <div class="row">
                        <div class="col-mb-3 mx-auto">
                            <label for="checkInInput">
                                <fmt:message key="checkout.checkInLabel"/>
                            </label>
                            <input class="form-control" type="date" id="checkInInput" min="${nowDate}"
                                   oninput="updatePrice(this); checkDates();"
                                   name="checkInDate" required>
                        </div>
                        <div class="col-mb-3 mx-auto">
                            <label for="checkOutInput">
                                <fmt:message key="checkout.checkOutLabel"/>
                            </label>
                            <input class="form-control" type="date" id="checkOutInput" min="${nowDate}"
                                   oninput="updatePrice(this); checkDates();"
                                   name="checkOutDate" required>
                        </div>
                        <input type="number" hidden value="${apartment.id}" name="apartmentId">
                    </div>
                    <hr class="mb-4">
                    <button class="btn btn-primary btn-lg btn-block" type="submit" id="submitButton">
                        <fmt:message key="checkout.confirmOrderButton"/>
                    </button>
                </form>
            </div>
        </div>
    </div>
</main>
<jsp:include page="${pageContext.request.contextPath}/footer.jsp"/>
<script type="text/javascript">
    function updatePrice(dateTag) {
        var checkInDate = document.getElementById("checkInInput").valueAsDate;
        var checkOutDate = document.getElementById("checkOutInput").valueAsDate;
        var daysAmount = document.getElementById("daysAmount");
        if (checkInDate == null || checkOutDate == null) {
            daysAmount.style.visibility = 'hidden';
            return;
        }
        var daysDiff = dateDiffInDays(checkInDate, checkOutDate);
        if (daysDiff <= 0) {
            daysAmount.style.visibility = 'hidden';
        } else {
            // updating days amount
            daysAmount.textContent = '<fmt:message key="checkout.totalDays"/>' + daysDiff;
            // daysAmount.textContent = 'x' + daysDiff + ' days';
            daysAmount.style.visibility = 'visible';
        }
    }

    function dateDiffInDays(a, b) {

        // a and b are javascript Date objects
        var _MS_PER_DAY = 1000 * 60 * 60 * 24;
        // Discard the time and time-zone information.
        var utc1 = Date.UTC(a.getFullYear(), a.getMonth(), a.getDate());
        var utc2 = Date.UTC(b.getFullYear(), b.getMonth(), b.getDate());

        return Math.floor((utc2 - utc1) / _MS_PER_DAY);
    }

    function checkDates() {
        var checkIn = new Date(document.getElementById('checkInInput').value);
        var checkOut = new Date(document.getElementById('checkOutInput').value);
        var searchButton = document.getElementById('submitButton');
        if (checkIn < checkOut) {
            searchButton.disabled = false;
        } else {
            searchButton.disabled = true;
        }
    }
</script>
</body>
</html>
