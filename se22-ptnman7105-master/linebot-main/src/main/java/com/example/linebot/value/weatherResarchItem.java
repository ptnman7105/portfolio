package com.example.linebot.value;

public class weatherResarchItem {

    private final String userId;
    private final WeatherResarchSlot slot;

    public weatherResarchItem(String userId,WeatherResarchSlot slot){
        this.userId = userId;
        this.slot = slot;

    }

    public String getUserId(){
        return userId;
    }

    public WeatherResarchSlot getSlot(){
        return slot;
    }
}
