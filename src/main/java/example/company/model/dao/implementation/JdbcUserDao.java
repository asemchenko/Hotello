package example.company.model.dao.implementation;

import example.company.model.dao.UserDao;
import example.company.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

// TODO implement this
public class JdbcUserDao implements UserDao {
    private static final String INSERT_QUERY = "INSERT INTO users " +
            "(first_name, last_name, email, password_hash, salt, user_status_id)" +
            " VALUES (?, ?, ?, ?, ?, ?)";
    private static final String FIND_BY_EMAIL_QUERY = "SELECT first_name, last_name, email, password_hash, salt, user_status_id from users where email=?";
    private Connection connection;

    public JdbcUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User user) {
        try (PreparedStatement s = connection.prepareStatement(INSERT_QUERY)) {
            s.setString(1, user.getFirstName());
            s.setString(2, user.getLastName());
            s.setString(3, user.getEmail());
            s.setBytes(4, user.getPasswordHash());
            s.setBytes(5, user.getPasswordSalt());
            s.setInt(6, user.getStatus().getId());
            s.executeUpdate();
            // TODO тут нужно узнать текущий индекс объекта и установить его
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setEmail(resultSet.getString("email"));
        user.setPasswordHash(resultSet.getBytes("password_hash"));
        user.setPasswordSalt(resultSet.getBytes("salt"));
        user.setStatus(User.UserStatus.getById(resultSet.getInt("user_status_id")));
        return user;
    }
}
