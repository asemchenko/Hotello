package example.company.model.entity;

import java.util.Objects;

public class Apartment extends Entity {
    private String title;
    private short placesAmount;
    private short roomsAmount;
    private String description;
    private long pricePerDay;
    private short starsAmount;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public short getPlacesAmount() {
        return placesAmount;
    }

    public void setPlacesAmount(short placesAmount) {
        this.placesAmount = placesAmount;
    }

    public short getRoomsAmount() {
        return roomsAmount;
    }

    public void setRoomsAmount(short roomsAmount) {
        this.roomsAmount = roomsAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(long pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public short getStarsAmount() {
        return starsAmount;
    }

    public void setStarsAmount(short starsAmount) {
        this.starsAmount = starsAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apartment apartment = (Apartment) o;
        return placesAmount == apartment.placesAmount &&
                roomsAmount == apartment.roomsAmount &&
                pricePerDay == apartment.pricePerDay &&
                starsAmount == apartment.starsAmount &&
                Objects.equals(title, apartment.title) &&
                Objects.equals(description, apartment.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, placesAmount, roomsAmount, description, pricePerDay, starsAmount);
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "title='" + title + '\'' +
                ", placesAmount=" + placesAmount +
                ", roomsAmount=" + roomsAmount +
                ", description='" + description + '\'' +
                ", pricePerDay=" + pricePerDay +
                ", starsAmount=" + starsAmount +
                ", id=" + id +
                "} ";
    }
}
