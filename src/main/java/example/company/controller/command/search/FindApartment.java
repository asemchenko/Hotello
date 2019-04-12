package example.company.controller.command.search;

import example.company.controller.command.Command;
import example.company.model.entity.Apartment;
import example.company.model.service.ApartmentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class FindApartment implements Command {
    private ApartmentService apartmentService;

    public FindApartment(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Apartment> filteredApartments = apartmentService.filter();
        request.setAttribute("apartments", filteredApartments);
        request.getRequestDispatcher("/WEB-INF/searchResults.jsp").forward(request, response);
    }
}
