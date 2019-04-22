package example.company.model.service.password;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import static java.util.Objects.isNull;

public class Hasher {
    private byte[] salt;
    private byte[] hashedPassword;
    private String password;

    public Hasher(String password) {
        this.password = password;
    }


    public byte[] getSalt() {
        if (isNull(salt)) {
            salt = new byte[16];
            SecureRandom random = new SecureRandom();
            random.nextBytes(salt);
        }
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public byte[] getHashedPassword() {
        if (isNull(hashedPassword)) {
            KeySpec keySpec = new PBEKeySpec(password.toCharArray(), getSalt(), 65536, 128);
            try {
                SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
                hashedPassword = factory.generateSecret(keySpec).getEncoded();
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                throw new RuntimeException(e);
            }
        }
        return hashedPassword;
    }
}
