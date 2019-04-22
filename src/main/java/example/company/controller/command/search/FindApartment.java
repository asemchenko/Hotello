package example.company.controller.command.search;

import example.company.controller.command.Command;
import example.company.model.entity.Apartment;
import example.company.model.service.ApartmentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class FindApartment implements Command {
    private ApartmentService apartmentService;

    public FindApartment(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LocalDate checkIn = LocalDate.parse(request.getParameter("checkIn"));
        LocalDate checkOut = LocalDate.parse(request.getParameter("checkOut"));
        short starsAmount = Short.parseShort(request.getParameter("starsAmount"));
        short placesAmount = Short.parseShort(request.getParameter("placesAmount"));
        List<Apartment> filteredApartments = apartmentService.filter(checkIn, checkOut, starsAmount, placesAmount);
        request.setAttribute("checkIn", request.getParameter("checkIn"));
        request.setAttribute("checkOut", request.getParameter("checkOut"));
        request.setAttribute("starsAmount", request.getParameter("starsAmount"));
        request.setAttribute("placesAmount", request.getParameter("placesAmount"));
        request.setAttribute("apartments", filteredApartments);
        request.getRequestDispatcher("/WEB-INF/searchResults.jsp").forward(request, response);
    }
}
