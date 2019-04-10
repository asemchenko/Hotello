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
                        <div class="md-3 ml-1"><span class="text-muted">$<c:out
                                value="${apartment.pricePerDay}"/>/day</span><br>
                            <span class="text-muted">x5 days</span></div>
                    </li>
                    <li class="list-group-item d-flex justify-content-between">
                        <span>Total (USD)</span>
                        <strong>$20</strong>
                    </li>
                </ul>
            </div>
            <div class="col-md-8 order-md-1">
                <h4 class="mb-3">Информация о заказе</h4>
                <form class="needs-validation" novalidate>
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="firstName">First name</label>
                            <input class="form-control" id="firstName" placeholder="" required type="text" value="">
                            <div class="invalid-feedback">
                                Valid first name is required.
                            </div>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="lastName">Last name</label>
                            <input class="form-control" id="lastName" placeholder="" required type="text" value="">
                            <div class="invalid-feedback">
                                Valid last name is required.
                            </div>
                        </div>
                    </div>

                    <div class="mb-3 w-50 mx-auto">
                        <label for="email">Mobile phone <span class="text-muted">(Optional)</span></label>
                        <input class="form-control" id="email" placeholder="+380123456789" type="email">
                    </div>

                    <div class="row">
                        <div class="col-mb-3 mx-auto">
                            <label for="checkInInput">Дата заезда</label>
                            <input class="form-control" type="date" id="checkInInput">
                        </div>
                        <div class="col-mb-3 mx-auto">
                            <label for="checkOutInput">Дата выезда</label>
                            <input class="form-control" type="date" id="checkOutInput">
                        </div>
                    </div>
                    <hr class="mb-4">
                    <button class="btn btn-primary btn-lg btn-block" type="submit">Подтвердить заказ</button>
                </form>
            </div>
        </div>
    </div>
</main>
<jsp:include page="${pageContext.request.contextPath}/footer.jsp"/>
</body>
</html>
