<%@ page contentType="text/html; charset=UTF-8" %>
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
    <%--Search bar--%>
    <section class="jumbotron text-center">
        <form method="get" action="/app/findApartment">
            <div class="form-row justify-content-center">
                <div class="col-auto">
                    <label for="checkInInput">Дата заезда</label>
                    <input class="form-control" type="date" id="checkInInput">
                </div>
                <div class="col-auto">
                    <label for="checkOutInput">Дата выезда</label>
                    <input class="form-control" type="date" id="checkOutInput">
                </div>
                <div class="col-auto">
                    <label for="inlineFormCustomSelectPref">Класс апартаментов</label>
                    <select class="form-control" id="inlineFormCustomSelectPref">
                        <option selected>Choose...</option>
                        <option value="1">One</option>
                        <option value="2">Two</option>
                        <option value="3">Three</option>
                    </select>
                </div>
                <div class="col-auto">
                    <label for="visitorsAmount">Кол-во мест</label>
                    <input class="form-control" type="number" min="1" id="visitorsAmount">
                </div>
                <div class="col-auto">
                    <button type="submit" style="margin-top: 30px;" class="btn btn-primary">Поиск</button>
                </div>
            </div>
        </form>


    </section>
</main>
</body>
</html>
