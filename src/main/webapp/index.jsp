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
    <!--    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">-->
    <!--    <link rel="stylesheet" href="/css/album.css">-->
    <!--    <link rel="stylesheet" href="/css/navbar.css">-->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title>Hotello</title>
</head>
<body style="background-color: #F8F9FA;">
<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
    <h5 class="my-0 mr-md-auto font-weight-normal">Hotello</h5>
    <nav class="my-2 my-md-0 mr-md-3">
        <a class="p-2 text-dark" href="#">Условия бронирования</a>
        <a class="p-2 text-dark" href="#">Оплата</a>
        <a class="p-2 text-dark" href="#">Поддержка</a>
        <a class="p-2 text-dark" href="#">О нас</a>
    </nav>
    <a class="btn btn-outline-primary" href="#">Зарегистироваться</a>
    <a class="btn btn-outline-primary" href="/app/signIn" style="margin-left: 10px">Войти</a>
</div>
<main role="main">
    <section class="jumbotron text-center">
    <form>
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

    <div class="album py-5 bg-light">
        <div class="container">

            <div class="row">
                <div class="col-md-4">
                    <div class="card mb-4 shadow-sm">
                        <img src="/hotel_sketch.jpg" alt="Apartment" width="100%" height="225">
                        <div class="card-body">
                            <p class="card-text">This is a wider card with supporting text below as a natural lead-in to
                                additional content. This content is a little bit longer.</p>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-sm btn-outline-secondary">View</button>

                                </div>

                                <span class="fa fa-star checked"></span>
                                <span class="fa fa-star checked"></span>
                                <span class="fa fa-star checked"></span>
                                <span class="fa fa-star"></span>
                                <span class="fa fa-star"></span>

                                <h4 class="card-title pricing-card-title">$0
                                    <small class="text-muted">/ day</small>
                                </h4>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card mb-4 shadow-sm">
                        <img src="/hotel_sketch.jpg" alt="Apartment" width="100%" height="225">
                        <div class="card-body">
                            <p class="card-text">This is a wider card with supporting text below as a natural lead-in to
                                additional content. This content is a little bit longer.</p>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                                </div>
                                <h4 class="card-title pricing-card-title">$0
                                    <small class="text-muted">/ day</small>
                                </h4>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card mb-4 shadow-sm">
                        <img src="/hotel_sketch.jpg" alt="Apartment" width="100%" height="225">
                        <div class="card-body">
                            <p class="card-text">This is a wider card with supporting text below as a natural lead-in to
                                additional content. This content is a little bit longer.</p>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-sm btn-outline-secondary">View</button>

                                </div>
                                <h4 class="card-title pricing-card-title">$0
                                    <small class="text-muted">/ day</small>
                                </h4>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="card mb-4 shadow-sm">
                        <img src="/hotel_sketch.jpg" alt="Apartment" width="100%" height="225">
                        <div class="card-body">
                            <p class="card-text">This is a wider card with supporting text below as a natural lead-in to
                                additional content. This content is a little bit longer.</p>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-sm btn-outline-secondary">View</button>

                                </div>
                                <h4 class="card-title pricing-card-title">$0
                                    <small class="text-muted">/ day</small>
                                </h4>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card mb-4 shadow-sm">
                        <img src="/hotel_sketch.jpg" alt="Apartment" width="100%" height="225">
                        <div class="card-body">
                            <p class="card-text">This is a wider card with supporting text below as a natural lead-in to
                                additional content. This content is a little bit longer.</p>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-sm btn-outline-secondary">View</button>

                                </div>
                                <h4 class="card-title pricing-card-title">$0
                                    <small class="text-muted">/ day</small>
                                </h4>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card mb-4 shadow-sm">
                        <img src="/hotel_sketch.jpg" alt="Apartment" width="100%" height="225">
                        <div class="card-body">
                            <p class="card-text">This is a wider card with supporting text below as a natural lead-in to
                                additional content. This content is a little bit longer.</p>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-sm btn-outline-secondary">View</button>

                                </div>
                                <h4 class="card-title pricing-card-title">$0
                                    <small class="text-muted">/ day</small>
                                </h4>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="card mb-4 shadow-sm">
                        <img src="/hotel_sketch.jpg" alt="Apartment" width="100%" height="225">
                        <div class="card-body">
                            <p class="card-text">This is a wider card with supporting text below as a natural lead-in to
                                additional content. This content is a little bit longer.</p>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-sm btn-outline-secondary">View</button>

                                </div>
                                <h4 class="card-title pricing-card-title">$0
                                    <small class="text-muted">/ day</small>
                                </h4>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card mb-4 shadow-sm">
                        <img src="/hotel_sketch.jpg" alt="Apartment" width="100%" height="225">
                        <div class="card-body">
                            <p class="card-text">This is a wider card with supporting text below as a natural lead-in to
                                additional content. This content is a little bit longer.</p>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-sm btn-outline-secondary">View</button>

                                </div>
                                <h4 class="card-title pricing-card-title">$0
                                    <small class="text-muted">/ day</small>
                                </h4>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card mb-4 shadow-sm">
                        <img src="/hotel_sketch.jpg" alt="Apartment" width="100%" height="225">
                        <div class="card-body">
                            <p class="card-text">This is a wider card with supporting text below as a natural lead-in to
                                additional content. This content is a little bit longer.</p>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-sm btn-outline-secondary">View</button>

                                </div>
                                <h4 class="card-title pricing-card-title">$0
                                    <small class="text-muted">/ day</small>
                                </h4>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
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