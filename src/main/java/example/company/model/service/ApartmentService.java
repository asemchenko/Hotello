package example.company.model.service;

import example.company.model.dao.api.concreteDao.ApartmentDao;
import example.company.model.dao.api.DaoFactory;
import example.company.model.dao.jdbc.JdbcDaoFactory;
import example.company.model.entity.Apartment;

import java.util.List;
import java.util.Optional;

public class ApartmentService {
    public Optional<Apartment> get(long id) {
        try (DaoFactory daoFactory = JdbcDaoFactory.getFactory()) {
            ApartmentDao apartmentDao = daoFactory.getApartmentDao();
            return apartmentDao.findById(id);
        }
    }

    public List<Apartment> filter(/* TODO вставь параметры описывающие условия фильтрации*/) {
        try (DaoFactory daoFactory = JdbcDaoFactory.getFactory()) {
            ApartmentDao apartmentDao = daoFactory.getApartmentDao();
            return apartmentDao.findAll();
        }
    }
}
