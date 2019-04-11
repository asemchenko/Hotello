package example.company.model.entity;

import org.jetbrains.annotations.Nullable;

import java.time.Instant;
import java.time.LocalDate;

public class Order extends Entity {
    @Nullable
    private Bill bill;
    private Apartment apartment;
    private User user;
    /**
     * Always in time zone of region where hotel(apartment) placed
     */
    private LocalDate checkInDate;
    /**
     * Always in time zone of region where hotel(apartment) placed
     */
    private LocalDate checkOutDate;
    private long pricePerDayAtTheTimeOfOrder;
    private long totalPrice;
    private Instant creationTime;

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public long getPricePerDayAtTheTimeOfOrder() {
        return pricePerDayAtTheTimeOfOrder;
    }

    public void setPricePerDayAtTheTimeOfOrder(long pricePerDayAtTheTimeOfOrder) {
        this.pricePerDayAtTheTimeOfOrder = pricePerDayAtTheTimeOfOrder;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Instant getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Instant creationTime) {
        this.creationTime = creationTime;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }
}
