package com.example.linebot.value;

public class WeatherStaticsItem {
    private final String userId;
    private final WeatherStaticsSlot slot;

    public WeatherStaticsItem(String userId,WeatherStaticsSlot slot){
        this.userId = userId;
        this.slot = slot;

    }

    public String getUserId(){
        return userId;
    }

    public WeatherStaticsSlot getSlot(){
        return slot;
    }
}
