package com.example.linebot.value;

public class WeatherItem {

    private final String userId;
    private final WeatherSlot slot;

    public WeatherItem(String userId, WeatherSlot slot) {
        this.userId = userId;
        this.slot = slot;
    }

    public String getUserId() {
        return userId;
    }

    public WeatherSlot getSlot() {
        return slot;
    }
}


