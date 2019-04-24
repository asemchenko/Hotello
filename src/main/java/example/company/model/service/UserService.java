package example.company.model.service;

import com.google.common.base.Throwables;
import example.company.model.dao.api.DaoFactory;
import example.company.model.dao.api.concreteDao.UserDao;
import example.company.model.dao.jdbc.JdbcDaoFactory;
import example.company.model.entity.User;
import example.company.model.service.exceptions.EmailAlreadyTakenException;
import example.company.model.service.password.Hasher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.Instant;
import java.util.Arrays;
import java.util.Optional;

public class UserService {
    public static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public void signUp(User user, String password) throws EmailAlreadyTakenException {
        validateUser(user);
        validatePassword(password);
        user.setCreationTime(Instant.now());
        setPassword(user, password);
        try (DaoFactory factory = JdbcDaoFactory.getFactory()) {
            UserDao userDao = factory.getUserDao();
            userDao.create(user);
        } catch (RuntimeException e) {
            Throwable rootCause = Throwables.getRootCause(e);
            if (rootCause instanceof SQLIntegrityConstraintViolationException) {
                throw new EmailAlreadyTakenException(rootCause);
            } else {
                throw e;
            }
        }
    }

    public boolean signIn(String email, String password) {
        try (DaoFactory factory = JdbcDaoFactory.getFactory()) {
            UserDao userDao = factory.getUserDao();
            Optional<User> user = userDao.findByEmail(email);
            return user.isPresent() && verifyPassword(user.get(), password);
        }
    }

    public Optional<User> getUserByEmail(String email) {
        try (DaoFactory factory = JdbcDaoFactory.getFactory()) {
            UserDao userDao = factory.getUserDao();
            return userDao.findByEmail(email);
        }
    }

    public boolean changePassword(User user, String oldPassword, String newPassword) {
        logger.debug("Changing password for user {}", user);
        if (verifyPassword(user, oldPassword)) {
            setPassword(user, newPassword);
            try (DaoFactory daoFactory = JdbcDaoFactory.getFactory()) {
                UserDao userDao = daoFactory.getUserDao();
                userDao.update(user);
            }
            return true;
        } else {
            return false;
        }
    }

    private void setPassword(User user, String password) {
        logger.debug("Setting password for user {}", user);
        Hasher hasher = new Hasher(password);
        user.setPasswordSalt(hasher.getSalt());
        user.setPasswordHash(hasher.getHashedPassword());
    }

    private boolean verifyPassword(User user, String password) {
        Hasher hasher = new Hasher(password);
        hasher.setSalt(user.getPasswordSalt());
        byte[] expectedHash = user.getPasswordHash();
        byte[] actualHash = hasher.getHashedPassword();
        return Arrays.equals(expectedHash, actualHash);
    }

    private void validatePassword(String password) {
        if (password.length() < 4) {
            throw new IllegalArgumentException();
        }
    }

    private void validateUser(User user) {
        if (user.getFirstName().length() > 3
                && user.getLastName().length() > 3
                && user.getEmail().length() > 5) {
            return;
        }
        throw new IllegalArgumentException();
    }
}