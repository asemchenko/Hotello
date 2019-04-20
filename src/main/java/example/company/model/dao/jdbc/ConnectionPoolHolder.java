package example.company.model.dao.jdbc;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.util.ResourceBundle;

public class ConnectionPoolHolder {
    private static ResourceBundle connectionParams = ResourceBundle.getBundle("dbConnectionParams");
    private static volatile DataSource dataSource;

    public static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    BasicDataSource ds = new BasicDataSource();
                    ds.setDriverClassName(connectionParams.getString("driver_class_name"));
                    ds.setUrl(connectionParams.getString("db_url"));
                    ds.setUsername(connectionParams.getString("db_username"));
                    ds.setPassword(connectionParams.getString("db_password"));
                    ds.setMinIdle(Integer.parseInt(connectionParams.getString("min_idle")));
                    ds.setMaxIdle(Integer.parseInt(connectionParams.getString("max_idle")));
                    dataSource = ds;
                }
            }
        }
        return dataSource;

    }
}
