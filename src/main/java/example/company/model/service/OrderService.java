package example.company.model.service;

import com.google.common.base.Throwables;
import example.company.model.dao.api.DaoFactory;
import example.company.model.dao.api.concreteDao.OrderDao;
import example.company.model.entity.Order;
import example.company.model.entity.User;
import example.company.model.service.exceptions.ApartmentAlreadyBookedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    private PaymentService paymentService;
    private Supplier<DaoFactory> daoFactorySupplier;

    public OrderService(PaymentService paymentService, Supplier<DaoFactory> daoFactorySupplier) {
        this.paymentService = paymentService;
        this.daoFactorySupplier = daoFactorySupplier;
    }

    public void makeOrder(Order order) throws ApartmentAlreadyBookedException {
        logger.info("Making an order {}", order);
        try (DaoFactory daoFactory = daoFactorySupplier.get()) {
            Connection connection = daoFactory.getCurrentConnection();
            connection.setAutoCommit(false);
            OrderDao orderDao = daoFactory.getOrderDao();
            orderDao.create(order);
            connection.commit();
        } catch (RuntimeException e) {
            Throwable rootCause = Throwables.getRootCause(e);
            if (rootCause instanceof SQLIntegrityConstraintViolationException) {
                throw new ApartmentAlreadyBookedException(rootCause);
            } else {
                throw e;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Order> getOrders(User user) {
        try (DaoFactory daoFactory = daoFactorySupplier.get()) {
            OrderDao orderDao = daoFactory.getOrderDao();
            List<Order> byUser = orderDao.getByUser(user);
            byUser.sort(Comparator.comparing(Order::getCreationTime).reversed());
            return byUser;
        }
    }

    public List<Order> getAllOrdersSortedByDate() {
        try (DaoFactory daoFactory = daoFactorySupplier.get()) {
            OrderDao orderDao = daoFactory.getOrderDao();
            List<Order> all = orderDao.findAll();
            all.sort(Comparator.comparing(Order::getCreationTime).reversed());
            return all;
        }
    }

    private void updateOrder(long orderId, Consumer<Order> updateFunc) {
        try (DaoFactory daoFactory = daoFactorySupplier.get()) {
            OrderDao orderDao = daoFactory.getOrderDao();
            Order order = orderDao.findById(orderId).get();
            updateFunc.accept(order);
            orderDao.update(order);
        }
    }

    public void confirmOrder(long orderId) {
        logger.info("Confirming an order with id {}", orderId);
        updateOrder(orderId, Order::confirm);
    }

    public void disapproveOrder(long orderId) {
        logger.info("Disapproving order with id {}", orderId);
        updateOrder(orderId, Order::disapprove);
    }

    public void payForOrder(long orderId, PaymentCredentials credentials) {
        logger.info("Paying for order id {}", orderId);
        updateOrder(orderId, o -> {
            String transactionId = paymentService.processPayment(o.getBill().getBankAccountNumber(), credentials);
            if (transactionId != null) {
                o.markAsPaid();
                o.getBill().setPaymentTransactionId(transactionId);
            } else {
                throw new RuntimeException("payment failed");
            }
        });
    }

    public Optional<Order> findById(long id) {
        try (DaoFactory daoFactory = daoFactorySupplier.get()) {
            OrderDao orderDao = daoFactory.getOrderDao();
            return orderDao.findById(id);
        }
    }
}