package example.company.model.entity;

import java.time.Instant;
import java.util.Objects;

public class Bill extends Entity {
    private long amountDue;
    private String bankAccountNumber;
    private Instant creationTime;
    /**
     * Stores an identifier from payment system
     * If bill is not paid yet - it stores null
     */
    private String paymentTransactionId;

    public Bill(long amountDue, String bankAccountNumber) {
        this.amountDue = amountDue;
        this.bankAccountNumber = bankAccountNumber;
        creationTime = Instant.now();
    }

    public long getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(long amountDue) {
        this.amountDue = amountDue;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public Instant getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Instant creationTime) {
        this.creationTime = creationTime;
    }

    public String getPaymentTransactionId() {
        return paymentTransactionId;
    }

    public void setPaymentTransactionId(String paymentTransactionId) {
        this.paymentTransactionId = paymentTransactionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bill bill = (Bill) o;
        return amountDue == bill.amountDue &&
                Objects.equals(bankAccountNumber, bill.bankAccountNumber) &&
                Objects.equals(creationTime, bill.creationTime) &&
                Objects.equals(paymentTransactionId, bill.paymentTransactionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amountDue, bankAccountNumber, creationTime, paymentTransactionId);
    }

    @Override
    public String toString() {
        return "Bill{" +
                "amountDue=" + amountDue +
                ", bankAccountNumber='" + bankAccountNumber + '\'' +
                ", creationTime=" + creationTime +
                ", paymentTransactionId='" + paymentTransactionId + '\'' +
                ", id=" + id +
                "} ";
    }
}
