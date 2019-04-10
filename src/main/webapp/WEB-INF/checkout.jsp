
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<main role="main">
    <div class="container">
        <div class="py-5 text-center">
            <h2>Оформление заказ</h2>
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
                        <div class="col-auto">
                            <h6 class="my-0">Product name</h6>
                            <small class="text-muted">Brief description</small>
                        </div>
                        <div class="col-auto"><span class="text-muted">$12/day</span><br>
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

        <footer class="my-5 pt-5 text-muted text-center text-small">
            <p class="mb-1">&copy; 2019 Hotello Inc.</p>
        </footer>
    </div>
</main>
<jsp:include page="${pageContext.request.contextPath}/footer.jsp"/>
</body>
</html>
