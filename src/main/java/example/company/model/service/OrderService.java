package example.company.model.service;

import example.company.model.dao.api.DaoFactory;
import example.company.model.dao.api.concreteDao.OrderDao;
import example.company.model.dao.jdbc.JdbcDaoFactory;
import example.company.model.entity.Order;
import example.company.model.entity.User;

import java.util.List;

public class OrderService {
    public void makeOrder(Order order) {
        try (DaoFactory daoFactory = JdbcDaoFactory.getFactory()) {
            OrderDao orderDao = daoFactory.getOrderDao();
            orderDao.create(order);
        }
    }

    public List<Order> getOrders(User user) {
        try (DaoFactory daoFactory = JdbcDaoFactory.getFactory()) {
            OrderDao orderDao = daoFactory.getOrderDao();
            return orderDao.getByUser(user);
        }
    }
}
