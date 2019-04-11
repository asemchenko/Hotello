<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="${pageContext.request.contextPath}/css/bootstrap_min.jsp"/>
</head>
<body>
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
                <ul class="list-group mb-3">
                    <li class="list-group-item d-flex justify-content-between lh-condensed">
                        <div class="md-3 mr-1">
                            <h6 class="my-0">
                                <a
                                        href="${pageContext.request.contextPath}/app/apartment?apartment_id=<c:out value="${apartment.id}" />"
                                        target="_blank">
                                    <c:out value="${apartment.title}"/>
                                </a>
                            </h6>
                        </div>
                        <div class="md-3 ml-1">
                            <span class="text-muted" id="apartmentPrice">
                                $<c:out value="${apartment.pricePerDay}"/>/day
                            </span>
                            <br>
                            <span class="text-muted" id="daysAmount" style="visibility: hidden;">x5 days</span>
                        </div>
                    </li>
<%--                    <li class="list-group-item d-flex justify-content-between">--%>
<%--                        <span>Total (USD)</span>--%>
<%--                        <strong id="totalPrice">$20</strong>--%>
<%--                    </li>--%>
                </ul>
            </div>
            <div class="col-md-8 order-md-1">
                <h4 class="mb-3">Информация о заказе</h4>
                <form method="post" action="${pageContext.request.contextPath}/app/confirmOrder">
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
                            <input class="form-control" type="date" id="checkInInput" onchange="updatePrice(this)" name="checkInDate" required>
                        </div>
                        <div class="col-mb-3 mx-auto">
                            <label for="checkOutInput">Дата выезда</label>
                            <input class="form-control" type="date" id="checkOutInput" onchange="updatePrice(this)" name="checkOutDate" required>
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
