package example.company.controller.command.order;

import example.company.controller.command.Command;
import example.company.model.entity.Apartment;
import example.company.model.entity.User;
import example.company.model.service.ApartmentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class Booking implements Command {
    private ApartmentService apartmentService;

    public Booking(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        long apartment_id = getApartmentId(request);
        Optional<Apartment> apartment = apartmentService.get(apartment_id);
        request.setAttribute("user", getUser(request));
        request.setAttribute("apartment", apartment.get());
        request.getRequestDispatcher("/WEB-INF/checkout.jsp").forward(request, response);
    }

    private long getApartmentId(HttpServletRequest request) {
        return Long.parseLong(request.getParameter("apartmentId"));
    }

    private User getUser(HttpServletRequest request) {
        return (User) request.getSession(false).getAttribute("user");
    }
}
