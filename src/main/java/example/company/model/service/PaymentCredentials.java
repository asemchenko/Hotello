package example.company.model.service;

import java.time.YearMonth;

public class PaymentCredentials {
    private String ownerName;
    private String cardNumber;
    private YearMonth expiration;
    private short cvvCode;

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public YearMonth getExpiration() {
        return expiration;
    }

    public void setExpiration(YearMonth expiration) {
        this.expiration = expiration;
    }

    public short getCvvCode() {
        return cvvCode;
    }

    public void setCvvCode(short cvvCode) {
        this.cvvCode = cvvCode;
    }
}
