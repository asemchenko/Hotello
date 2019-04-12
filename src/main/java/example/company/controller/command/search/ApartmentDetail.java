package example.company.controller.command.search;

import example.company.controller.command.Command;
import example.company.model.entity.Apartment;
import example.company.model.service.ApartmentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class ApartmentDetail implements Command {
    private ApartmentService apartmentService;

    public ApartmentDetail(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // TODO error handling
        long apartment_id = Long.parseLong(request.getParameter("apartmentId"));
        Optional<Apartment> apartmentOptional = apartmentService.get(apartment_id);
        if (apartmentOptional.isPresent()) {
            request.setAttribute("apartment", apartmentOptional.get());
            request.getRequestDispatcher("/WEB-INF/apartmentDetail.jsp").forward(request, response);
        } else {
            // FIXME возможно optional тут как возу пятое колесо?
            throw new RuntimeException();
        }
    }
}
