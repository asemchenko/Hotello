<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<jsp:include page="/navbar.jsp"/>
<main role="main">
    <div class="my-3 p-3 bg-white rounded shadow-sm justify-content-center mx-auto border border-dark" style="width: 50%">
        <div class="row">
            <div class="col-sm"><h6 class="border-bottom border-gray pb-2 mb-0 text-left">Заказ №123456789</h6></div>
            <div class="col-sm"></div>
            <div class="col-sm"><h6 class="border-bottom border-gray pb-2 mb-0 text-right">Дата заказа - 15-01-2019</h6>
            </div>
        </div>
        <div class="media text-muted pt-3">

            <img class="mr-2 my-2" src="/img/apartmentLogo.png" alt="apartment logo" width="32" height="32">
            <p class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
                <strong class="d-block text-gray-dark">Номер класса люкс</strong>
                <a href="#">Просмотреть</a>
            </p>
            <h4 class="card-title pricing-card-title mr-0">$0
                <small class="text-muted">/ day</small>
            </h4>
        </div>
        <div class="media text-muted">
            <img class="mr-2 my-2" src="/img/checkInLogo.png" alt="check in logo" width="32" height="32">
            <p class="media-body my-3">
                Дата въезда - 01.02.2019
            </p>
        </div>
        <div class="media text-muted">
            <img class="mr-2 my-2" src="/img/checkOutLogo.png" alt="check in logo" width="32" height="32">
            <p class="media-body my-3">
                Дата выезда - 04.02.2019
            </p>
        </div>
        <div class="row">
            <div class="col-sm "><h6 class="border-bottom border-gray text-left text-muted">СТАТУС ЗАКАЗА - ОПЛАЧЕН</h6>
            </div>
            <div class="col-sm"><h4 class="card-title pricing-card-title mr-0 text-right text-muted">
                Total $0
            </h4></div>
        </div>
    </div>
</main>
</body>
</html>
