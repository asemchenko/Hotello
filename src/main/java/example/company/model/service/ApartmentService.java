package example.company.model.service;

import example.company.model.dao.api.DaoFactory;
import example.company.model.dao.api.concreteDao.ApartmentDao;
import example.company.model.dao.jdbc.JdbcDaoFactory;
import example.company.model.entity.Apartment;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ApartmentService {
    public Optional<Apartment> get(long id) {
        try (DaoFactory daoFactory = JdbcDaoFactory.getFactory()) {
            ApartmentDao apartmentDao = daoFactory.getApartmentDao();
            return apartmentDao.findById(id);
        }
    }

    private List<Apartment> getNonBooked(LocalDate checkIn, LocalDate checkOut) {
        try (DaoFactory daoFactory = JdbcDaoFactory.getFactory()) {
            ApartmentDao apartmentDao = daoFactory.getApartmentDao();
            return apartmentDao.findNotBooked(checkIn, checkOut);
        }
    }

    public List<Apartment> filter(LocalDate checkIn, LocalDate checkOut) {
        return getNonBooked(checkIn, checkOut);
    }


}
