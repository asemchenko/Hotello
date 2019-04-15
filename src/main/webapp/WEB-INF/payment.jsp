<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Оплата заказа</title>
    <jsp:include page="${pageContext.request.contextPath}/css/bootstrap_min.jsp"/>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/navbar.jsp"/>
<div class="container">
    <div class="py-5 text-center">
        <h2>Оплата заказа</h2>
    </div>

    <div class="row justify-content-center">
        <div class="col-md-8 order-md-1">
            <form class="needs-validation" novalidate action="${pageContext.request.contextPath}/app/processPayment" method="post">
                <input type="text" name="orderId" value="${order.id}" hidden>
                <h4 class="mb-3 text-center">Способ оплаты</h4>

                <div class="row my-3 text-center">
                    <div class="custom-control custom-radio col">
                        <input id="credit" name="paymentMethod" type="radio" class="custom-control-input" checked
                               required>
                        <label class="custom-control-label" for="credit">Credit card</label>
                    </div>
                    <div class="custom-control custom-radio col">
                        <input id="debit" name="paymentMethod" type="radio" class="custom-control-input" required>
                        <label class="custom-control-label" for="debit">Debit card</label>
                    </div>
                    <div class="custom-control custom-radio col">
                        <input id="paypal" name="paymentMethod" type="radio" class="custom-control-input" required>
                        <label class="custom-control-label" for="paypal">PayPal</label>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="cc-name">Name on card</label>
                        <input type="text" class="form-control" id="cc-name" placeholder="" required>
                        <small class="text-muted">Full name as displayed on card</small>
                        <div class="invalid-feedback">
                            Name on card is required
                        </div>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="cc-number">Credit card number</label>
                        <input type="text" class="form-control" id="cc-number" placeholder="" required>
                        <div class="invalid-feedback">
                            Credit card number is required
                        </div>
                    </div>
                </div>
                <div class="row justify-content-center">
                    <div class="col-md-3 mb-3">
                        <label for="cc-expiration">Expiration</label>
                        <input type="text" class="form-control" id="cc-expiration" placeholder="" required>
                        <div class="invalid-feedback">
                            Expiration date required
                        </div>
                    </div>
                    <div class="col-md-3 mb-3">
                        <label for="cc-cvv">CVV</label>
                        <input type="text" class="form-control" id="cc-cvv" placeholder="" required>
                        <div class="invalid-feedback">
                            Security code required
                        </div>
                    </div>
                </div>
                <div class="row justify-content-center">
                        <h4 class="card-title pricing-card-title mr-0 text-right text-muted">
                            Total price: $<c:out value="${order.totalPrice}"/>
                        </h4>
                </div>
                <hr class="mb-4">
                <button class="btn btn-primary btn-lg btn-block" type="submit">Continue to checkout</button>
            </form>
        </div>
    </div>
</div>
<jsp:include page="${pageContext.request.contextPath}/footer.jsp"/>
</body>
</html>
