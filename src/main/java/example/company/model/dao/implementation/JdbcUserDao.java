package example.company.model.dao.implementation;

import example.company.model.dao.UserDao;
import example.company.model.entity.User;

import java.sql.*;
import java.util.List;
import java.util.Optional;

// TODO implement this
public class JdbcUserDao implements UserDao {
    private static final String INSERT_QUERY = "INSERT INTO users " +
            "(first_name, last_name, email, password_hash, salt, user_status_id)" +
            " VALUES (?, ?, ?, ?, ?, ?)";
    private static final String FIND_BY_EMAIL_QUERY = "SELECT user_id, first_name, last_name, email, password_hash, salt, user_status_id from users where email=?";
    private static final String UPDATE_QUERY = "UPDATE users SET first_name=?, last_name=?, email=?, password_hash=?, salt=?, user_status_id=? WHERE user_id=?";
    private Connection connection;

    public JdbcUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User user) {
        try (PreparedStatement s = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            setUserParams(s, user, 0);
            s.executeUpdate();
            user.setId(getInsertionId(s));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

    private long getInsertionId(Statement statement) throws SQLException {
        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return generatedKeys.getLong("user_id");
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }
    }

    @Override
    public Optional<User> findById(int id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void update(User user) {
        try (PreparedStatement s = connection.prepareStatement(UPDATE_QUERY)) {
            int nextParamIndex = setUserParams(s, user, 0);
            s.setLong(nextParamIndex, user.getId());
            s.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (PreparedStatement statement = connection.prepareStatement(FIND_BY_EMAIL_QUERY)) {
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
        return user;
    }
}
