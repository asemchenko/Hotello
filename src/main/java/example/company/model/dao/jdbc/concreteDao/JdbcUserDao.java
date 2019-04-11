package example.company.model.dao.jdbc.concreteDao;

import example.company.model.dao.api.concreteDao.UserDao;
import example.company.model.dao.jdbc.JdbcGenericDao;
import example.company.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

// TODO implement this
public class JdbcUserDao extends JdbcGenericDao<User> implements UserDao {
    private static final String INSERT_QUERY = "INSERT INTO users " +
            "(first_name, last_name, email, password_hash, salt, user_status_id)" +
            " VALUES (?, ?, ?, ?, ?, ?)";
    private static final String FIND_BY_EMAIL_QUERY = "SELECT user_id, first_name, last_name, email, password_hash, salt, user_status_id from users where email=?";
    private static final String UPDATE_QUERY = "UPDATE users SET first_name=?, last_name=?, email=?, password_hash=?, salt=?, user_status_id=? WHERE user_id=?";
    private static final String FIND_ALL_QUERY = "SELECT user_id, user_status_id, first_name, last_name, email, password_hash, salt, creation_time FROM users";
    private static final String FIND_BY_ID_QUERY = "SELECT user_id, user_status_id, first_name, last_name, email, password_hash, salt, creation_time FROM users WHERE user_id=?";

    public JdbcUserDao(Connection connection) {
        super(connection);
    }

    @Override
    protected void setUpdateQueryParams(PreparedStatement s, User entity) throws SQLException {
        int nextParamIndex = setUserParams(s, entity, 0);
        s.setLong(nextParamIndex, entity.getId());
    }

    @Override
    protected String getUpdateQuery() {
        return UPDATE_QUERY;
    }

    @Override
    protected String getInsertQuery() {
        return INSERT_QUERY;
    }

    @Override
    protected void setInsertQueryParams(PreparedStatement s, User user) throws SQLException {
        setUserParams(s, user, 0);
    }

    private int setUserParams(PreparedStatement s, User user, int offset) throws SQLException {
        s.setString(1 + offset, user.getFirstName());
        s.setString(2 + offset, user.getLastName());
        s.setString(3 + offset, user.getEmail());
        s.setBytes(4 + offset, user.getPasswordHash());
        s.setBytes(5 + offset, user.getPasswordSalt());
        s.setLong(6 + offset, user.getStatus().getId());
        return 6 + 1 + offset;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_EMAIL_QUERY)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(getFromRow(resultSet));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private User getFromRow(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("user_id"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setEmail(resultSet.getString("email"));
        user.setPasswordHash(resultSet.getBytes("password_hash"));
        user.setPasswordSalt(resultSet.getBytes("salt"));
        user.setStatus(User.UserStatus.getById(resultSet.getInt("user_status_id")));
        // TODO добавь чтение creation_time
        return user;
    }

    @Override
    protected User getFromResultSet(ResultSet resultSet) throws SQLException {
        return getFromRow(resultSet);
    }

    @Override
    protected void setFindByIdQueryParams(PreparedStatement s, long id) throws SQLException {
        s.setLong(1, id);
    }

    @Override
    protected String getFindByIdQuery() {
        return FIND_BY_ID_QUERY;
    }

    @Override
    protected String getFindAllQuery() {
        return FIND_ALL_QUERY;
    }
}