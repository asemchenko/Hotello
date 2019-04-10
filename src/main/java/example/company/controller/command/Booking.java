package example.company.controller.command;

import example.company.model.entity.Apartment;
import example.company.model.service.ApartmentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

import static java.util.Objects.nonNull;

public class Booking implements Command {
    private ApartmentService apartmentService;

    public Booking(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        long apartment_id = getApartmentId(request);
        Optional<Apartment> apartment = apartmentService.get(apartment_id);
        request.setAttribute("apartment", apartment.get());
        request.getRequestDispatcher("/WEB-INF/checkout.jsp").forward(request, response);
    }

    private long getApartmentId(HttpServletRequest request) {
        return Long.parseLong(request.getParameter("apartment_id"));
    }
}
