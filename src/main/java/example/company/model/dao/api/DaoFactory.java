package example.company.model.dao.api;

import example.company.model.dao.api.concreteDao.ApartmentDao;
import example.company.model.dao.api.concreteDao.OrderDao;
import example.company.model.dao.api.concreteDao.UserDao;

public interface DaoFactory extends AutoCloseable {
    UserDao getUserDao();

    ApartmentDao getApartmentDao();

    OrderDao getOrderDao();

    void close();
}