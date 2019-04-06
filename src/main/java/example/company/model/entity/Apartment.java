package example.company.model.entity;

public class Apartment extends Entity {
    private String title;
    private long placesAmount;
    private long roomsAmount;
    private String description;
    private long pricePerDay;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getPlacesAmount() {
        return placesAmount;
    }

    public void setPlacesAmount(long placesAmount) {
        this.placesAmount = placesAmount;
    }

    public long getRoomsAmount() {
        return roomsAmount;
    }

    public void setRoomsAmount(long roomsAmount) {
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
}
