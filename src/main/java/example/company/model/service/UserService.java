package example.company.model.service;

import example.company.model.dao.api.DaoFactory;
import example.company.model.dao.api.concreteDao.UserDao;
import example.company.model.dao.jdbc.JdbcDaoFactory;
import example.company.model.entity.User;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Optional;

// TODO сделай интерфейс, который реализует этот класс
public class UserService {
    public void signUp(User user, String password) {
        setPassword(user, password);
        try (DaoFactory factory = JdbcDaoFactory.getFactory()) {
            UserDao userDao = factory.getUserDao();
            userDao.create(user);
        }
    }

    public boolean signIn(String email, String password) {
        try (DaoFactory factory = JdbcDaoFactory.getFactory()) {
            UserDao userDao = factory.getUserDao();
            Optional<User> user = userDao.findByEmail(email);
            if (user.isPresent()) {
                return verifyPassword(user.get(), password);
            } else {
                return false;
            }
        }
    }

    public Optional<User> getUserByEmail(String email) {
        try (DaoFactory factory = JdbcDaoFactory.getFactory()) {
            UserDao userDao = factory.getUserDao();
            return userDao.findByEmail(email);
        }
    }

    public boolean changePassword(User user, String oldPassword, String newPassword) {
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

    // TODO возможно метод setPassword нужно перенести в user
    private void setPassword(User user, String password) {
        byte[] salt = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
        user.setPasswordSalt(salt);
        user.setPasswordHash(hashPassword(password, salt));
    }

    private byte[] hashPassword(String password, byte[] salt) {
        KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return factory.generateSecret(keySpec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean verifyPassword(User user, String password) {
        byte[] usedSalt = user.getPasswordSalt();
        byte[] expectedHash = user.getPasswordHash();
        byte[] actualHash = hashPassword(password, usedSalt);
        return Arrays.equals(expectedHash, actualHash);
    }
}
