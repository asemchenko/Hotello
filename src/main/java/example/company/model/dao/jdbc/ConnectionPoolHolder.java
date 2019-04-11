package example.company.model.dao.jdbc;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

public class ConnectionPoolHolder {
    //TODO load constants to properties
//  FIXME  ALARM SSL disabled!!! DO NOT USE IRL
    private static final String URL = "jdbc:mysql://localhost:3306/hotello?allowPublicKeyRetrieval=true&sslMode=DISABLED&serverTimezone=UTC";
    private static final String USERNAME = "hotello";
    private static final String PASSWORD = "hotello_admin_password";
    private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private static volatile DataSource dataSource;

    public static DataSource getDataSource() {

        if (dataSource == null) {
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    BasicDataSource ds = new BasicDataSource();
                    ds.setDriverClassName(DRIVER_CLASS_NAME);
                    ds.setUrl(URL);
                    ds.setUsername(USERNAME);
                    ds.setPassword(PASSWORD);
                    ds.setMinIdle(5);
                    ds.setMaxIdle(10);
                    ds.setMaxOpenPreparedStatements(100);
                    dataSource = ds;
                }
            }
        }
        return dataSource;

    }
}
