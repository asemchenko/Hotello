package example.company.model.entity;

import java.time.Instant;

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
}
