package example.company.model.dao.jdbc;

import example.company.model.dao.api.DaoFactory;
import example.company.model.dao.api.concreteDao.ApartmentDao;
import example.company.model.dao.api.concreteDao.BillDao;
import example.company.model.dao.api.concreteDao.OrderDao;
import example.company.model.dao.api.concreteDao.UserDao;
import example.company.model.dao.jdbc.concreteDao.JdbcApartmentDao;
import example.company.model.dao.jdbc.concreteDao.JdbcBillDao;
import example.company.model.dao.jdbc.concreteDao.JdbcOrderDao;
import example.company.model.dao.jdbc.concreteDao.JdbcUserDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcDaoFactory implements DaoFactory {
    private Connection connection;
    private UserDao userDao;
    private ApartmentDao apartmentDao;
    private OrderDao orderDao;
    private BillDao billDao;

    // TODO фабика фабрик?777
    public JdbcDaoFactory(Connection connection) {
        this.connection = connection;
    }

    private static Connection getConnectionFromPool() {
        try {
            DataSource ds = ConnectionPoolHolder.getDataSource();
            return ds.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static DaoFactory getFactory() {
        return new JdbcDaoFactory(getConnectionFromPool());
    }

    @Override
    public UserDao getUserDao() {
        if (userDao == null) {
            userDao = new JdbcUserDao(getCurrentConnection());
        }
        return userDao;
    }

    @Override
    public ApartmentDao getApartmentDao() {
        if (apartmentDao == null) {
            apartmentDao = new JdbcApartmentDao(getCurrentConnection());
        }
        return apartmentDao;
    }

    @Override
    public Connection getCurrentConnection() {
        if (connection == null) {
            connection = getConnectionFromPool();
        }
        return connection;
    }

    @Override
    public OrderDao getOrderDao() {
        if (orderDao == null) {
            orderDao = new JdbcOrderDao(
                    getCurrentConnection(),
                    getUserDao(),
                    getApartmentDao(),
                    getBillDao());
        }
        return orderDao;
    }

    @Override
    public BillDao getBillDao() {
        if (billDao == null) {
            billDao = new JdbcBillDao(getCurrentConnection());
        }
        return billDao;
    }

    @Override
    public void close() {
        try {
            if (!connection.getAutoCommit()) {
                connection.rollback();
                connection.setAutoCommit(true);
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
