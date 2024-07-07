package com.example.loginscreenactivity;

public class Package {
    private int imageResource;
    private String packageName;

    public Package(int imageResource, String packageName) {
        this.imageResource = imageResource;
        this.packageName = packageName;
    }

    public  int getImageResource() {
        return imageResource;
    }

    public  String getPackageName() {
        return packageName;
    }
}
