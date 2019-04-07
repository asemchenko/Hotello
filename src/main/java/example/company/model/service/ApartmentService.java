package example.company.model.service;

import example.company.model.dao.ApartmentDao;
import example.company.model.dao.DaoFactory;
import example.company.model.dao.implementation.JdbcDaoFactory;
import example.company.model.entity.Apartment;

import java.util.Optional;

public class ApartmentService {
    public Optional<Apartment> get(long id) {
        try (DaoFactory daoFactory = JdbcDaoFactory.getFactory()) {
            ApartmentDao apartmentDao = daoFactory.getApartmentDao();
            return apartmentDao.findById(id);
        }
    }
}
