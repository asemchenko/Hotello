<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="message"/>

<html>
<head>
    <title><fmt:message key="payment.title"/></title>
    <jsp:include page="${pageContext.request.contextPath}/css/bootstrap_min.jsp"/>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/navbar.jsp"/>
<div class="container">
    <div class="py-5 text-center">
        <h2><fmt:message key="payment.header"/></h2>
    </div>

    <div class="row justify-content-center">
        <div class="col-md-8 order-md-1">
            <form class="needs-validation" action="${pageContext.request.contextPath}/app/processPayment"
                  method="post">
                <input type="text" name="orderId" value="${order.id}" hidden>
                <h4 class="mb-3 text-center"><fmt:message key="payment.paymentTypeHeader"/></h4>

                <div class="row my-3 text-center">
                    <div class="custom-control custom-radio col">
                        <input id="credit" name="paymentMethod" type="radio" class="custom-control-input" checked
                               required>
                        <label class="custom-control-label" for="credit">
                            <fmt:message key="payment.type.creditCard"/>
                        </label>
                    </div>
                    <div class="custom-control custom-radio col">
                        <input id="debit" name="paymentMethod" type="radio" class="custom-control-input" required>
                        <label class="custom-control-label" for="debit">
                            <fmt:message key="payment.type.debitCard"/>
                        </label>
                    </div>
                    <div class="custom-control custom-radio col">
                        <input id="paypal" name="paymentMethod" type="radio" class="custom-control-input" required>
                        <label class="custom-control-label" for="paypal">
                            <fmt:message key="payment.type.PayPal"/>
                        </label>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="cc-name"><fmt:message key="payment.cardOwnerLabel"/></label>
                        <input type="text" class="form-control" id="cc-name" placeholder="" name="cardOwnerFullName"
                               required>
                        <small class="text-muted"><fmt:message key="payment.cardOwnerFullName"/></small>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="cc-number"><fmt:message key="payment.cardNumberLabel"/></label>
                        <input type="text" class="form-control" id="cc-number" placeholder="" name="creditCardNumber"
                               required>
                        <div class="invalid-feedback">
                            Credit card number is required
                        </div>
                    </div>
                </div>
                <div class="row justify-content-center">
                    <div class="col-md-3 mb-3">
                        <label for="cc-expiration">
                            <fmt:message key="payment.cardExpirationDate"/>
                        </label>
                        <input type="text" class="form-control" id="cc-expiration" placeholder="" name="expirationDate"
                               required>
                        <div class="invalid-feedback">
                            <%--                            FIXME проверь отображается ли этот текст и если да то локализируй его--%>
                            Expiration date required
                        </div>
                    </div>
                    <div class="col-md-3 mb-3 pt-4">
                        <label for="cc-cvv">
                            <fmt:message key="payment.cardCvvCode"/>
                        </label>
                        <input type="text" class="form-control" id="cc-cvv" placeholder="" name="cvvCode" required>
                        <div class="invalid-feedback">
                            Security code required
                        </div>
                    </div>
                </div>
                <div class="row justify-content-center">
                    <h4 class="card-title pricing-card-title mr-0 text-right text-muted">
                        <fmt:formatNumber type="CURRENCY" maxFractionDigits="2" value="${order.totalPrice / 100}"/>
                    </h4>
                </div>
                <hr class="mb-4">
                <button class="btn btn-primary btn-lg btn-block" type="submit">
                    <fmt:message key="payment.payButton"/>
                </button>
            </form>
        </div>
    </div>
</div>
<jsp:include page="${pageContext.request.contextPath}/footer.jsp"/>
</body>
</html>
