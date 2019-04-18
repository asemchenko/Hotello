package example.company.model.service;

import example.company.model.dao.api.DaoFactory;
import example.company.model.dao.api.concreteDao.OrderDao;
import example.company.model.dao.jdbc.JdbcDaoFactory;
import example.company.model.entity.Order;
import example.company.model.entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class OrderService {
    private PaymentService paymentService = new PaymentService();
    public void makeOrder(Order order) {
        try (DaoFactory daoFactory = JdbcDaoFactory.getFactory()) {
            Connection connection = daoFactory.getCurrentConnection();
            connection.setAutoCommit(false);
            OrderDao orderDao = daoFactory.getOrderDao();
            orderDao.create(order);
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
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

    public void payForOrder(long orderId, PaymentCredentials credentials) {
        // processing payment required
        updateOrder(orderId, o -> {
            String transactionId = paymentService.processPayment(o.getBill().getBankAccountNumber(), credentials);
            if (transactionId != null) {
                o.markAsPaid();
                o.getBill().setPaymentTransactionId(transactionId);
            } else {
                // FIXME замени на свой exception
                throw new RuntimeException("payment failed");
            }
        });
    }

    public Optional<Order> findById(long id) {
        try (DaoFactory daoFactory = JdbcDaoFactory.getFactory()) {
            OrderDao orderDao = daoFactory.getOrderDao();
            return orderDao.findById(id);
        }
    }
}