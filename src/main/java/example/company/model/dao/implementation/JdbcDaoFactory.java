package example.company.model.dao.implementation;

import example.company.model.dao.ApartmentDao;
import example.company.model.dao.DaoFactory;
import example.company.model.dao.UserDao;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcDaoFactory implements DaoFactory {
    private Connection connection;
    private UserDao userDao;
    private ApartmentDao apartmentDao;
 // TODO фабика фабрик?777
    public JdbcDaoFactory(Connection connection) {
        this.connection = connection;
    }

    private static Connection getConnectionFromPool() {
        try {
            DataSource ds = ConnectionPoolHolder.getDataSource();
            return ds.getConnection();
        } catch (SQLException e) {
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
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
