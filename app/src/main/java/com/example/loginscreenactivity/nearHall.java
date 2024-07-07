package com.example.loginscreenactivity;



public class nearHall {
    private String hallName;
    private String details;
    private String location;
    private String guests;
    private String rating;
    private int imageResId;
    private int locationIconResId;
    private int guestsIconResId;
    private int feedbackIconResId;
    private int forwardIconResId;

    public nearHall(String hallName, String details, String location, String guests, String rating,
                int imageResId, int locationIconResId, int guestsIconResId, int feedbackIconResId, int forwardIconResId) {
        this.hallName = hallName;
        this.details = details;
        this.location = location;
        this.guests = guests;
        this.rating = rating;
        this.imageResId = imageResId;
        this.locationIconResId = locationIconResId;
        this.guestsIconResId = guestsIconResId;
        this.feedbackIconResId = feedbackIconResId;
        this.forwardIconResId = forwardIconResId;
    }

    // Getters and Setters
    public String getHallName() { return hallName; }
    public void setHallName(String hallName) { this.hallName = hallName; }

    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getGuests() { return guests; }
    public void setGuests(String guests) { this.guests = guests; }

    public String getRating() { return rating; }
    public void setRating(String rating) { this.rating = rating; }

    public int getImageResId() { return imageResId; }
    public void setImageResId(int imageResId) { this.imageResId = imageResId; }

    public int getLocationIconResId() { return locationIconResId; }
    public void setLocationIconResId(int locationIconResId) { this.locationIconResId = locationIconResId; }

    public int getGuestsIconResId() { return guestsIconResId; }
    public void setGuestsIconResId(int guestsIconResId) { this.guestsIconResId = guestsIconResId; }

    public int getFeedbackIconResId() { return feedbackIconResId; }
    public void setFeedbackIconResId(int feedbackIconResId) { this.feedbackIconResId = feedbackIconResId; }

    public int getForwardIconResId() { return forwardIconResId; }
    public void setForwardIconResId(int forwardIconResId) { this.forwardIconResId = forwardIconResId; }
}
