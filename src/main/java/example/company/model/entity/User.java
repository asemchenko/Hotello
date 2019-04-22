package example.company.model.entity;

import java.time.Instant;
import java.util.Arrays;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(email, user.email) &&
                Arrays.equals(passwordHash, user.passwordHash) &&
                Arrays.equals(passwordSalt, user.passwordSalt) &&
                Objects.equals(creationTime, user.creationTime) &&
                status == user.status;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(firstName, lastName, email, creationTime, status);
        result = 31 * result + Arrays.hashCode(passwordHash);
        result = 31 * result + Arrays.hashCode(passwordSalt);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", creationTime=" + creationTime +
                ", status=" + status +
                ", id=" + id +
                "} ";
    }

    public enum UserStatus {
        ADMIN, CLIENT
    }
}
