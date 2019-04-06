package example.company.model.dao.implementation;

import example.company.model.dao.GenericDao;
import example.company.model.entity.Entity;

import java.sql.*;

public abstract class JdbcGenericDao<T extends Entity> implements GenericDao<T> {
    private final Connection connection;

    protected JdbcGenericDao(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public void create(T entity) {
        try (PreparedStatement s = connection.prepareStatement(getInsertQuery(), Statement.RETURN_GENERATED_KEYS)) {
            setInsertQueryParams(s, entity);
            s.executeUpdate();
            entity.setId(getInsertionId(s));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(T t) {
        try (PreparedStatement s = connection.prepareStatement(getUpdateQuery())) {
            setUpdateQueryParams(s, t);
            s.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract void setUpdateQueryParams(PreparedStatement s, T entity) throws SQLException;

    protected abstract String getUpdateQuery();

    protected long getInsertionId(Statement s) throws SQLException {
        try (ResultSet generatedKeys = s.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return generatedKeys.getLong(1);
            } else {
                throw new SQLException("Insertion failed, no ID obtained.");
            }
        }
    }

    protected abstract String getInsertQuery();

    protected abstract void setInsertQueryParams(PreparedStatement s, T entity) throws SQLException;
}
