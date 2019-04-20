<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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
            <h2>Оформление заказа</h2>
            <p class="lead">
                Укажите необходимые данные и подтвердите заказ
            </p>
        </div>

        <div class="row">
            <div class="col-md-4 order-md-2 mb-4">
                <h4 class="d-flex justify-content-between align-items-center mb-3">
                    <span class="text-muted">Your cart</span>
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
                                $<c:out value="${apartment.pricePerDay}"/>/day
                            </span>
                        <br>
                        <span class="text-muted" id="daysAmount" style="visibility: hidden;">x5 days</span>
                    </div>
                </div>
            </div>
            <div class="col-md-8 order-md-1">
                <h4 class="mb-3">Информация о заказе</h4>
                <form method="post" action="${pageContext.request.contextPath}/app/makeOrder">
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="firstName">First name</label>
                            <input class="form-control" id="firstName" placeholder="" required type="text"
                                   value="${user.firstName}" disabled>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="lastName">Last name</label>
                            <input class="form-control" id="lastName" placeholder="" required type="text"
                                   value="${user.lastName}" disabled>
                        </div>
                    </div>

                    <div class="mb-3 w-50 mx-auto">
                        <label for="email">Email</label>
                        <input class="form-control" id="email" type="email" value="${user.email}" disabled>
                    </div>

                    <div class="row">
                        <div class="col-mb-3 mx-auto">
                            <label for="checkInInput">Дата заезда</label>
                            <input class="form-control" type="date" id="checkInInput" onchange="updatePrice(this)"
                                   name="checkInDate" required>
                        </div>
                        <div class="col-mb-3 mx-auto">
                            <label for="checkOutInput">Дата выезда</label>
                            <input class="form-control" type="date" id="checkOutInput" onchange="updatePrice(this)"
                                   name="checkOutDate" required>
                        </div>
                        <input type="number" hidden value="${apartment.id}" name="apartmentId">
                    </div>
                    <hr class="mb-4">
                    <button class="btn btn-primary btn-lg btn-block" type="submit">Подтвердить заказ</button>
                </form>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        function updatePrice(dateTag) {
            var checkInDate = document.getElementById("checkInInput").valueAsDate;
            var checkOutDate = document.getElementById("checkOutInput").valueAsDate;
            var daysAmount = document.getElementById("daysAmount");
            var daysDiff = dateDiffInDays(checkInDate, checkOutDate);
            if (daysDiff <= 0) {
                daysAmount.style.visibility = 'hidden';
                dateTag.value = '';
                alert('Check out date must be after check in date');
            } else {
                // updating days amount
                daysAmount.textContent = 'x' + daysDiff + ' days';
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
    </script>
</main>
<jsp:include page="${pageContext.request.contextPath}/footer.jsp"/>
</body>
</html>
