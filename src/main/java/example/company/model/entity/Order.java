package example.company.model.entity;

import org.jetbrains.annotations.Nullable;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class Order extends Entity {
    // FIXME обязательно придумай куда это деть
    private static final String DEFAULT_BANK_ACCOUNT_NUMBER = "1234-1234-1234-1234";
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
    private OrderStatus status;

    public Order() {
        // default status
        status = OrderStatus.CONFIRMATION_EXPECTED;
        creationTime = Instant.now();
    }

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

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setStatus(String status) {
        this.status = OrderStatus.valueOf(status);
    }

    public void confirm() {
        if (getStatus() == OrderStatus.CONFIRMATION_EXPECTED) {
            setStatus(OrderStatus.PAYMENT_EXPECTED);
            setBill(new Bill(totalPrice, DEFAULT_BANK_ACCOUNT_NUMBER));
        } else {
            throw new IllegalStateException(String.format("Can not confirm order in state %s", getStatus()));
        }
    }

    public void disapprove() {
        if (getStatus() == OrderStatus.CONFIRMATION_EXPECTED) {
            setStatus(OrderStatus.DISAPPROVED);
        } else {
            throw new IllegalStateException(String.format("Can not confirm order in state %s", getStatus()));
        }
    }

    public void markAsPaid() {
        if (getStatus() == OrderStatus.PAYMENT_EXPECTED) {
            setStatus(OrderStatus.PAID);
        } else {
            throw new IllegalStateException(String.format("Order in state %s can not be marked as paid", getStatus()));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return pricePerDayAtTheTimeOfOrder == order.pricePerDayAtTheTimeOfOrder &&
                totalPrice == order.totalPrice &&
                Objects.equals(bill, order.bill) &&
                Objects.equals(apartment, order.apartment) &&
                Objects.equals(user, order.user) &&
                Objects.equals(checkInDate, order.checkInDate) &&
                Objects.equals(checkOutDate, order.checkOutDate) &&
                Objects.equals(creationTime, order.creationTime) &&
                status == order.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bill, apartment, user, checkInDate, checkOutDate, pricePerDayAtTheTimeOfOrder, totalPrice, creationTime, status);
    }

    @Override
    public String toString() {
        return "Order{" +
                "bill=" + bill +
                ", apartment=" + apartment +
                ", user=" + user +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", pricePerDayAtTheTimeOfOrder=" + pricePerDayAtTheTimeOfOrder +
                ", totalPrice=" + totalPrice +
                ", creationTime=" + creationTime +
                ", status=" + status +
                ", id=" + id +
                "} ";
    }

    public enum OrderStatus {
        CONFIRMATION_EXPECTED, PAYMENT_EXPECTED, PAID, DISAPPROVED;

        public String toLocalizedString(Locale locale) {
            return ResourceBundle.getBundle("orderStatus", locale).getString(this.name());
        }

        @Override
        public String toString() {
            return this.name();
        }
    }
}
