package com.example.loginscreenactivity;

public class halls {
    private int imageUrl;
    private String hallName;


    public halls(String hallName, int imageUrl) {
        this.imageUrl = imageUrl;
        this.hallName = hallName;
    }

    public int getImageUrl() {
        return imageUrl;
    }

    public String getHallName() {
        return hallName;
    }
}

