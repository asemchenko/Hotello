<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="message"/>

<jsp:useBean id="now" class="java.util.Date"/>
<fmt:formatDate var="nowDate" value="${now}" pattern="yyyy-MM-dd"/>

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
    <title>Search results</title>
</head>
<body style="background-color: #F8F9FA;" onload="setStarsAmount();">
<jsp:include page="${pageContext.request.contextPath}/navbar.jsp"/>
<main role="main">
    <%--Search bar--%>
    <section class="jumbotron text-center">
        <form method="get" action="${pageContext.request.contextPath}/app/findApartment">
            <div class="form-row justify-content-center">
                <div class="col-auto">
                    <label for="checkInInput"><fmt:message key="searchBar.checkIn"/></label>
                    <input class="form-control" type="date" id="checkInInput" name="checkIn"
                           value="<c:out value="${checkIn}" />" min="${nowDate}" oninput="checkDates()" required>
                </div>
                <div class="col-auto">
                    <label for="checkOutInput"><fmt:message key="searchBar.checkOut"/></label>
                    <input class="form-control" type="date" id="checkOutInput" name="checkOut"
                           value="<c:out value="${checkOut}" />" min="${nowDate}" oninput="checkDates()" required>
                </div>
                <div class="col-auto">
                    <label for="starsAmountSelect"><fmt:message key="searchBar.apartmentClass"/></label>
                    <select class="custom-select" id="starsAmountSelect" required name="starsAmount">
                        <option value="1">
                            <fmt:message key="searchBar.oneStar"/>
                        </option>
                        <option value="2">
                            <fmt:message key="searchBar.twoStars"/>
                        </option>
                        <option value="3">
                            <fmt:message key="searchBar.threeStars"/>
                        </option>
                        <option value="4">
                            <fmt:message key="searchBar.fourStars"/>
                        </option>
                        <option value="5">
                            <fmt:message key="searchBar.fiveStars"/>
                        </option>
                    </select>
                </div>
                <div class="col-auto">
                    <label for="visitorsAmount"><fmt:message key="searchBar.placesAmount"/></label>
                    <input class="form-control" type="number" min="1" id="visitorsAmount"
                           value="<c:out value="${placesAmount}" />" name="placesAmount" required>
                </div>
                <div class="col-auto">
                    <button type="submit" style="margin-top: 30px;" class="btn btn-primary" id="searchButton">
                        <fmt:message
                                key="searchBar.search"/></button>
                </div>
            </div>
        </form>


    </section>
    <%--Apartments--%>
    <div class="album py-5 bg-light">
        <div class="container">
            <div class="row">
                <c:forEach var="apartment" items="${apartments}">
                    <div class="col-md-4">
                        <div class="card mb-4 shadow-sm">
                            <img src="${pageContext.request.contextPath}/hotel_sketch.jpg" alt="Apartment" width="100%"
                                 height="225">
                            <div class="card-body">
                                <p class="card-text">
                                    <c:out value="${apartment.title}"/>
                                </p>
                                <div class="d-flex justify-content-between align-items-center">
                                    <div class="btn-group">
                                        <form action="${pageContext.request.contextPath}/app/apartment" method="get">
                                            <input type="text" name="apartmentId"
                                                   value="<c:out value="${apartment.id}" />" hidden>
                                            <button class="btn btn-sm btn-outline-secondary">
                                                <fmt:message key="searchResults.viewButton"/>
                                            </button>
                                        </form>
                                    </div>

                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star"></span>
                                    <span class="fa fa-star"></span>

                                    <h4 class="card-title pricing-card-title">
                                        <fmt:formatNumber value="${apartment.pricePerDay / 100}" type="CURRENCY"
                                                          maxFractionDigits="2"/>
                                        <small class="text-muted">/ day</small>
                                    </h4>
                                </div>
                            </div>
                        </div>
                    </div>

                </c:forEach>
            </div>
        </div>
    </div>
    <%--Pagination--%>
    <div class="container">
        <nav aria-label="...">
            <ul class="pagination justify-content-center">
                <li
                        <c:choose>
                            <c:when test="${pageNumber eq 1}">
                                class="page-item disabled"
                            </c:when>
                            <c:otherwise>
                                class="page-item active"
                            </c:otherwise>
                        </c:choose>
                >
                    <form action="">
                        <input type="text" name="checkIn" hidden value="${checkIn}">
                        <input type="text" name="checkOut" hidden value="${checkOut}">
                        <input type="text" name="starsAmount" hidden value="${starsAmount}">
                        <input type="text" name="placesAmount" hidden value="${placesAmount}">
                        <input type="text" name="pageNumber" hidden value="${pageNumber - 1}">
                        <button type="submit" class="page-link"><fmt:message key="pagination.previous"/></button>
                    </form>
                </li>
                <li class="page-item">
                    <a class="page-link" href="#"><c:out value="${pageNumber}"/>
                        <span class="sr-only">(current)</span>
                    </a>
                </li>
                <li
                        <c:choose>
                            <c:when test="${lastPage}">
                                class="page-item disabled"
                            </c:when>
                            <c:otherwise>
                                class="page-item active"
                            </c:otherwise>
                        </c:choose>
                >
                    <form action="">
                        <input type="text" name="checkIn" hidden value="${checkIn}">
                        <input type="text" name="checkOut" hidden value="${checkOut}">
                        <input type="text" name="starsAmount" hidden value="${starsAmount}">
                        <input type="text" name="placesAmount" hidden value="${placesAmount}">
                        <input type="text" name="pageNumber" hidden value="${pageNumber + 1}">
                        <button type="submit" class="page-link"><fmt:message key="pagination.next"/></button>
                    </form>
                </li>
            </ul>
        </nav>
    </div>
</main>
<jsp:include page="${pageContext.request.contextPath}/footer.jsp"/>
<script type="text/javascript">
    function setStarsAmount() {
        var s = document.getElementById('starsAmountSelect');
        s.value =<c:out value="${starsAmount}" />;
    }

    function checkDates() {
        var checkIn = new Date(document.getElementById('checkInInput').value);
        var checkOut = new Date(document.getElementById('checkOutInput').value);
        var searchButton = document.getElementById('searchButton');
        if (checkIn < checkOut) {
            searchButton.disabled = false;
        } else {
            searchButton.disabled = true;
        }
    }
</script>
</body>
</html>
