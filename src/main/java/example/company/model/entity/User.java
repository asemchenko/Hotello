package example.company.model.entity;

import java.util.NoSuchElementException;

public class User extends Entity {
    private String firstName;
    private String lastName;
    private String email;
    private byte[] passwordHash;
    private byte[] passwordSalt;
    private UserStatus status;
    // TODO добавь поле creation_time как в БД и обнови код DAO

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(byte[] passwordHash) {
        this.passwordHash = passwordHash;
    }

    public byte[] getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(byte[] passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public enum UserStatus {
        ADMIN(1), CLIENT(2);
        private final long id;

        UserStatus(int id) {
            this.id = id;
        }

        public static UserStatus getById(int id) {
            switch (id) {
                case 1:
                    return ADMIN;
                case 2:
                    return CLIENT;
                default:
                    throw new NoSuchElementException("No user statuses with id " + id);
            }
        }

        public long getId() {
            return id;
        }
    }
}
