package example.company.controller.command.order;

import example.company.controller.command.Command;
import example.company.model.entity.Apartment;
import example.company.model.entity.Order;
import example.company.model.entity.User;
import example.company.model.service.ApartmentService;
import example.company.model.service.OrderService;
import example.company.model.service.exceptions.ApartmentAlreadyBookedException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

public class MakeOrder implements Command {
    private ApartmentService apartmentService;
    private OrderService orderService;

    public MakeOrder(ApartmentService apartmentService, OrderService orderService) {
        this.apartmentService = apartmentService;
        this.orderService = orderService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Order order = extractOrderInfo(request);
        try {
            orderService.makeOrder(order);
        } catch (ApartmentAlreadyBookedException e) {
            response.sendRedirect("/error/alreadyBookedError.jsp");
            return;
        }
        response.sendRedirect("/app/orders");
    }

    private User getCurrentUser(HttpServletRequest request) {
        return (User) request.getSession(false).getAttribute("user");
    }

    private Order extractOrderInfo(HttpServletRequest request) {
        Order order = new Order();
        // setting checkIn && checkOut dates
        order.setCheckInDate(extractCheckInDate(request));
        order.setCheckOutDate(extractCheckOutDate(request));
        checkDatesRange(order.getCheckInDate(), order.getCheckOutDate());
        // setting apartment
        Apartment apartment = loadApartment(extractApartmentId(request)).get();
        order.setApartment(apartment);
        // setting user
        order.setUser(getCurrentUser(request));
        // setting prices
        order.setPricePerDayAtTheTimeOfOrder(apartment.getPricePerDay());
        order.setTotalPrice(calcTotalPrice(order.getCheckInDate(), order.getCheckOutDate(), apartment));
        return order;
    }

    private long calcTotalPrice(LocalDate checkIn, LocalDate checkOut, Apartment apartment) {
        long days = ChronoUnit.DAYS.between(checkIn, checkOut);
        return apartment.getPricePerDay() * days;
    }

    private Optional<Apartment> loadApartment(long id) {
        return apartmentService.get(id);
    }

    private long extractApartmentId(HttpServletRequest request) {
        return Long.parseLong(request.getParameter("apartmentId"));
    }

    private LocalDate extractCheckOutDate(HttpServletRequest request) {
        return LocalDate.parse(request.getParameter("checkOutDate"));
    }

    private LocalDate extractCheckInDate(HttpServletRequest request) {
        return LocalDate.parse(request.getParameter("checkInDate"));
    }

    private void checkDatesRange(LocalDate checkIn, LocalDate checkOut) {
        LocalDate now = LocalDate.now();
        if (checkOut.isAfter(checkIn)
                && (checkIn.isAfter(now) || checkIn.equals(now))) {
            return;
        }
        throw new IllegalArgumentException("Invalid date in request");
    }
}
