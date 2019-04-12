package example.company.model.entity;

import java.time.Instant;

public class User extends Entity {
    private String firstName;
    private String lastName;
    private String email;
    private byte[] passwordHash;
    private byte[] passwordSalt;
    private Instant creationTime;
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

    public Instant getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Instant creationTime) {
        this.creationTime = creationTime;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public void setStatus(String role) {
        this.status = UserStatus.valueOf(role);
    }
    // TODO rename to UserRole
    public enum UserStatus {
        ADMIN, CLIENT
    }
}
