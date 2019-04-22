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
        int pageNumber = getPageNumber(request);
        short starsAmount = Short.parseShort(request.getParameter("starsAmount"));
        short placesAmount = Short.parseShort(request.getParameter("placesAmount"));
        List<Apartment> filteredApartments = apartmentService.filter(checkIn, checkOut, starsAmount, placesAmount, pageNumber);
        request.setAttribute("checkIn", request.getParameter("checkIn"));
        request.setAttribute("checkOut", request.getParameter("checkOut"));
        request.setAttribute("starsAmount", request.getParameter("starsAmount"));
        request.setAttribute("placesAmount", request.getParameter("placesAmount"));
        request.setAttribute("apartments", filteredApartments);
        request.setAttribute("pageNumber", pageNumber);
        request.setAttribute("lastPage", filteredApartments.size() != apartmentService.getPageSize());
        request.getRequestDispatcher("/WEB-INF/searchResults.jsp").forward(request, response);
    }

    private int getPageNumber(HttpServletRequest request) {
        String pageNumber = request.getParameter("pageNumber");
        if (pageNumber == null) {
            return 1;
        }
        return Integer.parseInt(pageNumber);
    }

    private ApartmentService.FilterFunc getFunc(HttpServletRequest request) {
        short starsAmount = Short.parseShort(request.getParameter("starsAmount"));
        short placesAmount = Short.parseShort(request.getParameter("placesAmount"));
        return new ApartmentService.FilterFunc.Builder()
                .setStarsAmount(starsAmount)
                .setPlacesAmount(placesAmount)
                .build();
    }
}