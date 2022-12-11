package com.example.linebot.value;

public class WeatherDeleteItem {
    private final WeatherDeleteSlot slot;

    public WeatherDeleteItem( WeatherDeleteSlot slot) {
        this.slot = slot;
    }

    public WeatherDeleteSlot getSlot() {
        return slot;
    }
}
