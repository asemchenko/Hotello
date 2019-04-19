<%@ page contentType="text/html; charset=UTF-8" %>
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
    <%--Search bar--%>
    <section class="jumbotron text-center">
        <form method="get" action="${pageContext.request.contextPath}/app/findApartment">
            <div class="form-row justify-content-center">
                <div class="col-auto">
                    <label for="checkInInput">
                        <fmt:message key="searchBar.checkIn"/>
                    </label>
                    <input class="form-control" type="date" id="checkInInput" name="checkIn" required>
                </div>
                <div class="col-auto">
                    <label for="checkOutInput">
                        <fmt:message key="searchBar.checkOut"/>
                    </label>
                    <input class="form-control" type="date" id="checkOutInput" name="checkOut" required>
                </div>
                <div class="col-auto">
                    <label for="inlineFormCustomSelectPref">
                        <fmt:message key="searchBar.apartmentClass"/>
                    </label>
                    <select class="custom-select" id="inlineFormCustomSelectPref" name="starsAmount" required>
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
                    <label for="visitorsAmount">
                        <fmt:message key="searchBar.placesAmount"/>
                    </label>
                    <input class="form-control" type="number" min="1" id="visitorsAmount" name="placesAmount" required>
                </div>
                <div class="col-auto">
                    <button type="submit" style="margin-top: 30px;" class="btn btn-primary">
                        <fmt:message key="searchBar.search"/>
                    </button>
                </div>
            </div>
        </form>


    </section>
</main>
<jsp:include page="${pageContext.request.contextPath}/footer.jsp"/>
</body>
</html>
