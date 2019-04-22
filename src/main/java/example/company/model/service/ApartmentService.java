package example.company.model.service;

import example.company.model.dao.api.DaoFactory;
import example.company.model.dao.api.concreteDao.ApartmentDao;
import example.company.model.dao.jdbc.JdbcDaoFactory;
import example.company.model.entity.Apartment;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ApartmentService {
    private static final int PAGE_SIZE = 3;

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

    public int getPageSize() {
        return PAGE_SIZE;
    }

    public List<Apartment> filter(LocalDate checkIn, LocalDate checkOut, short starsAmount, short placesAmount, int pageNumber) {
        try (DaoFactory daoFactory = JdbcDaoFactory.getFactory()) {
            ApartmentDao apartmentDao = daoFactory.getApartmentDao();
            return apartmentDao.findNotBooked(checkIn, checkOut, placesAmount, starsAmount, pageNumber, PAGE_SIZE);
        }
    }

    public List<Apartment> filter(LocalDate checkIn, LocalDate checkOut) {
        return getNonBooked(checkIn, checkOut);
    }

    public List<Apartment> filter(LocalDate checkIn, LocalDate checkOut, FilterFunc f) {
        return filter(checkIn, checkOut)
                .stream()
                .filter(f::accept)
                .collect(Collectors.toList());
    }

    public static class FilterFunc {
        private short starsAmount = 1;
        private short placesAmount = 1;

        private FilterFunc() {
        }

        public boolean accept(Apartment apartment) {
            return apartment.getPlacesAmount() == placesAmount && apartment.getStarsAmount() == starsAmount;
        }

        public static class Builder {
            private FilterFunc func;

            public Builder() {
                func = new FilterFunc();
            }

            public Builder setStarsAmount(short amount) {
                func.starsAmount = amount;
                return this;
            }

            public Builder setPlacesAmount(short placesAmount) {
                func.placesAmount = placesAmount;
                return this;
            }

            public FilterFunc build() {
                return func;
            }
        }
    }
}
