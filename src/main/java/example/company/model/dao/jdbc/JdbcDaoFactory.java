package example.company.model.dao.jdbc;

import example.company.model.dao.api.DaoFactory;
import example.company.model.dao.api.concreteDao.ApartmentDao;
import example.company.model.dao.api.concreteDao.OrderDao;
import example.company.model.dao.api.concreteDao.UserDao;
import example.company.model.dao.jdbc.concreteDao.JdbcApartmentDao;
import example.company.model.dao.jdbc.concreteDao.JdbcOrderDao;
import example.company.model.dao.jdbc.concreteDao.JdbcUserDao;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcDaoFactory implements DaoFactory {
    private Connection connection;
    private UserDao userDao;
    private ApartmentDao apartmentDao;
    private OrderDao orderDao;

    // TODO фабика фабрик?777
    public JdbcDaoFactory(Connection connection) {
        this.connection = connection;
    }

    private static Connection getConnectionFromPool() {
        try {
            InitialContext initContext = new InitialContext();
            DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/hotello");
            return ds.getConnection();
        } catch (NamingException | SQLException e) {
            // FIXME как то это не оч
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

    private Connection getCurrentConnection() {
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
                    getApartmentDao());
        }
        return orderDao;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
