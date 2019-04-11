package example.company.model.dao.jdbc;

import example.company.model.dao.api.GenericDao;
import example.company.model.entity.Entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public abstract class JdbcGenericDao<T extends Entity> implements GenericDao<T> {
    private final Connection connection;

    protected JdbcGenericDao(Connection connection) {
        this.connection = connection;
    }

    public final Connection getConnection() {
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

    @Override
    public Optional<T> findById(long id) {
        try (PreparedStatement s = connection.prepareStatement(getFindByIdQuery())) {
            setFindByIdQueryParams(s, id);
            ResultSet resultSet = s.executeQuery();
            if (resultSet.next()) {
                return Optional.ofNullable(getFromResultSet(resultSet));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract void setFindByIdQueryParams(PreparedStatement s, long id) throws SQLException;

    protected abstract String getFindByIdQuery();

    @Override
    public List<T> findAll() {
        return findList(getFindAllQuery(), (s) -> {
        });
        // TODO удали если findAll нормально работает
//        try (PreparedStatement s = connection.prepareStatement(getFindAllQuery())) {
//            ResultSet resultSet = s.executeQuery();
//            ArrayList<T> entities = new ArrayList<>();
//            while (resultSet.next()) {
//                entities.add(getFromResultSet(resultSet));
//            }
//            return entities;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }

    protected final List<T> findList(String query, Consumer<PreparedStatement> prepareFunc) {
        try (PreparedStatement s = connection.prepareStatement(query)) {
            prepareFunc.accept(s);
            ResultSet resultSet = s.executeQuery();
            ArrayList<T> entities = new ArrayList<>();
            while (resultSet.next()) {
                entities.add(getFromResultSet(resultSet));
            }
            return entities;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract T getFromResultSet(ResultSet resultSet) throws SQLException;

    protected abstract String getFindAllQuery();

    @Override
    public void delete(long id) {
        throw new UnsupportedOperationException("This feature is not supported yet");
    }
}
