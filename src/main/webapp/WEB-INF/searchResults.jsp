<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <style type="text/css">
        .checked {
            color: orange;
        }
    </style>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title>Search results</title>
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
    <%--Apartments--%>
    <div class="album py-5 bg-light">
        <div class="container">
            <div class="row">
                <c:forEach var="apartment" items="${apartments}">
                    <div class="col-md-4">
                        <div class="card mb-4 shadow-sm">
                            <img src="/hotel_sketch.jpg" alt="Apartment" width="100%" height="225">
                            <div class="card-body">
                                <p class="card-text">
                                    <c:out value="${apartment.title}"/>
                                </p>
                                <div class="d-flex justify-content-between align-items-center">
                                    <div class="btn-group">
                                        <a href="/app/apartment?apartment_id=<c:out value="${apartment.id}" />"
                                                   class="btn btn-sm btn-outline-secondary">
                                        View
                                        </a>
                                    </div>

                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star"></span>
                                    <span class="fa fa-star"></span>

                                    <h4 class="card-title pricing-card-title">
                                        <c:out value="${apartment.pricePerDay}"/>
                                        <small class="text-muted">/ day</small>
                                    </h4>
                                </div>
                            </div>
                        </div>
                    </div>

                </c:forEach>
            </div>
        </div>
    </div>
    <%--Pagination--%>
    <div class="container">
        <nav aria-label="...">
            <ul class="pagination justify-content-center">
                <li class="page-item disabled">
                    <a class="page-link" href="#" tabindex="-1">Previous</a>
                </li>
                <li class="page-item"><a class="page-link" href="#">1</a></li>
                <li class="page-item active">
                    <a class="page-link" href="#">2 <span class="sr-only">(current)</span></a>
                </li>
                <li class="page-item"><a class="page-link" href="#">3</a></li>
                <li class="page-item">
                    <a class="page-link" href="#">Next</a>
                </li>
            </ul>
        </nav>
    </div>
</main>
</body>
</html>