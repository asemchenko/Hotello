package example.company.model.dao.api.concreteDao;

import example.company.model.dao.api.GenericDao;
import example.company.model.entity.Apartment;

import java.time.LocalDate;
import java.util.List;

public interface ApartmentDao extends GenericDao<Apartment> {
    List<Apartment> findNotBooked(LocalDate checkIn, LocalDate checkOut);

    List<Apartment> findNotBooked(LocalDate checkIn, LocalDate checkOut, short placesAmount, short starsAmount, int pageNumber, int pageSize);
}