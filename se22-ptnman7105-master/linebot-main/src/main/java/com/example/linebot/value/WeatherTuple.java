package com.example.linebot.value;

public class WeatherTuple {

    private final String userId;
    private final String pushText;
    private final float EstimateNumber;

    public WeatherTuple(String userId,String pushText,float EstimateNumber){
        this.userId = userId;
        this.pushText = pushText;
        this.EstimateNumber = EstimateNumber;
    }

    public String getUserId() {
        return userId;
    }

    public String getPushText() {
        return pushText;
    }

    public float getPushEstimateNumber() {
        return EstimateNumber;
    }
}
