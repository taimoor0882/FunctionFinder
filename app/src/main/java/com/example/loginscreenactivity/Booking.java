package com.example.loginscreenactivity;

public class Booking {
    private String eventType;
    private int capacity;
    private String decorPackage;

    public Booking() {
        // Default constructor required for calls to DataSnapshot.getValue(Booking.class)
    }

    public Booking(String eventType, int capacity, String decorPackage) {
        this.eventType = eventType;
        this.capacity = capacity;
        this.decorPackage = decorPackage;
    }

    // Getters and setters
    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getDecorPackage() {
        return decorPackage;
    }

    public void setDecorPackage(String decorPackage) {
        this.decorPackage = decorPackage;
    }
}
