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
    <title>Hotello</title>
</head>
<body style="background-color: #F8F9FA;">
<jsp:include page="/navbar.jsp"/>
<main role="main">
    <div class="container">

        <div class="row justify-content-center">

            <div class="col-lg-8">

                <!-- Title -->
                <h1 class="mt-4">VIP номер в подвале гостинцы 'Уют'</h1>

                <hr>

                <!-- Preview Image -->
                <div class="text-center">
                    <img class="img-fluid rounded" src="/hotel_sketch.jpg" alt="apartments photo">
                </div>

                <hr>

                <!-- Post Content -->
                <p class="lead">
                    <c:out value="${apartment.description}" />
                </p>
            </div>

            <div class="col-md-4">

                <!-- Categories Widget -->
                <div class="card my-4">
                    <h5 class="card-header">Характеристики</h5>
                    <div class="card-body">
                        <div class="container">
                            <ul>
                                <li>
                                    Класс апартаментов - 1
                                </li>
                                <li>
                                    Количество мест - <c:out value="${apartment.placesAmount}" />
                                </li>
                                <li>
                                    Количество комнат - <c:out value="${apartment.roomsAmount}" />
                                </li>
                            </ul>
                        </div>
                        <hr>
                        <div class="text-center">
                            <a class="btn btn-outline-success" href="#"
                               style="margin-left: 10px">Забронировать</a>
                        </div>
                    </div>
                </div>

            </div>
        </div>

    </div>
</main>
</body>
</html>
