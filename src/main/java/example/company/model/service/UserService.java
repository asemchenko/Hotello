package example.company.model.service;

import example.company.model.dao.DaoFactory;
import example.company.model.dao.UserDao;
import example.company.model.dao.implementation.JdbcDaoFactory;
import example.company.model.entity.User;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Optional;

public class UserService {
    public void signUp(User user, String password) {
        hashPassword(user, password);
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

    private void hashPassword(User user, String password) {
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
