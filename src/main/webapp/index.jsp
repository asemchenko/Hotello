<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<jsp:include page="${pageContext.request.contextPath}/navbar.jsp"/>
<main role="main">
    <%--Search bar--%>
    <section class="jumbotron text-center">
        <form method="get" action="${pageContext.request.contextPath}/app/findApartment">
            <div class="form-row justify-content-center">
                <div class="col-auto">
                    <label for="checkInInput">Дата заезда</label>
                    <input class="form-control" type="date" id="checkInInput" name="checkIn" required>
                </div>
                <div class="col-auto">
                    <label for="checkOutInput">Дата выезда</label>
                    <input class="form-control" type="date" id="checkOutInput" name="checkOut" required>
                </div>
                <div class="col-auto">
                    <label for="inlineFormCustomSelectPref">Класс апартаментов</label>
                    <select class="form-control" id="inlineFormCustomSelectPref" name="starsAmount" required>
                        <option value="1">1 звезда</option>
                        <option value="2">2 звезды</option>
                        <option value="3">3 звезды</option>
                        <option value="4">4 звезды</option>
                        <option value="5">5 звезд</option>
                    </select>
                </div>
                <div class="col-auto">
                    <label for="visitorsAmount">Кол-во мест</label>
                    <input class="form-control" type="number" min="1" id="visitorsAmount" name="placesAmount" required>
                </div>
                <div class="col-auto">
                    <button type="submit" style="margin-top: 30px;" class="btn btn-primary">Поиск</button>
                </div>
            </div>
        </form>


    </section>
</main>
<jsp:include page="${pageContext.request.contextPath}/footer.jsp"/>
</body>
</html>
