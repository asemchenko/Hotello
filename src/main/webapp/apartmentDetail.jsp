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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title>Hotello</title>
</head>
<body style="background-color: #F8F9FA;">
<jsp:include page="${headerPath}"/>
<main role="main">
    <div class="container">

        <div class="row justify-content-center">

            <div class="col-lg-8">
                <!--            <div style="width: 80%">-->

                <!-- Title -->
                <h1 class="mt-4">VIP номер в подвале гостинцы 'Уют'</h1>

                <hr>

                <!-- Preview Image -->
                <div class="text-center">
                    <img class="img-fluid rounded" src="/hotel_sketch.jpg" alt="apartments photo">
                </div>

                <hr>

                <!-- Post Content -->
                <p class="lead">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ducimus, vero, obcaecati, aut,
                    error quam sapiente nemo saepe quibusdam sit excepturi nam quia corporis eligendi eos magni
                    recusandae laborum minus inventore?</p>

                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ut, tenetur natus doloremque laborum quos
                    iste ipsum rerum obcaecati impedit odit illo dolorum ab tempora nihil dicta earum fugiat.
                    Temporibus, voluptatibus.</p>

                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Eos, doloribus, dolorem iusto blanditiis
                    unde eius illum consequuntur neque dicta incidunt ullam ea hic porro optio ratione repellat
                    perspiciatis. Enim, iure!</p>

                <blockquote class="blockquote">
                    <p class="mb-0">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat a
                        ante.</p>
                    <footer class="blockquote-footer">Someone famous in
                        <cite title="Source Title">Source Title</cite>
                    </footer>
                </blockquote>

                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Error, nostrum, aliquid, animi, ut quas
                    placeat totam sunt tempora commodi nihil ullam alias modi dicta saepe minima ab quo voluptatem
                    obcaecati?</p>

                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Harum, dolor quis. Sunt, ut, explicabo,
                    aliquam tenetur ratione tempore quidem voluptates cupiditate voluptas illo saepe quaerat numquam
                    recusandae? Qui, necessitatibus, est!</p>
            </div>

            <div class="col-md-4">

                <!-- Categories Widget -->
                <div class="card my-4">
                    <h5 class="card-header">Характеристики</h5>
                    <div class="card-body">
                        <div class="container">
                            <ul>
                                <!--                            <ul class="list-unstyled mb-0">-->
                                <li>
                                    Класс апартаментов - 1
                                </li>
                                <li>
                                    Количество мест - 2
                                </li>
                                <li>
                                    Количество комнат - 2
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
