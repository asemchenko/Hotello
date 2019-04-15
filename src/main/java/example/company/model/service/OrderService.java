package example.company.model.service;

import example.company.model.dao.api.DaoFactory;
import example.company.model.dao.api.concreteDao.OrderDao;
import example.company.model.dao.jdbc.JdbcDaoFactory;
import example.company.model.entity.Order;
import example.company.model.entity.User;

import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

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

    public List<Order> getAllOrdersSortedByDate() {
        try (DaoFactory daoFactory = JdbcDaoFactory.getFactory()) {
            OrderDao orderDao = daoFactory.getOrderDao();
            List<Order> all = orderDao.findAll();
            all.sort(Comparator.comparing(Order::getCreationTime).reversed());
            return all;
        }
    }


    private void updateOrder(long orderId, Consumer<Order> updateFunc) {
        try (DaoFactory daoFactory = JdbcDaoFactory.getFactory()) {
            OrderDao orderDao = daoFactory.getOrderDao();
            Order order = orderDao.findById(orderId).get();
            updateFunc.accept(order);
            orderDao.update(order);
        }
    }

    public void confirmOrder(long orderId) {
        updateOrder(orderId, Order::confirm);
    }

    public void disapproveOrder(long orderId) {
        updateOrder(orderId, Order::disapprove);
    }
}
