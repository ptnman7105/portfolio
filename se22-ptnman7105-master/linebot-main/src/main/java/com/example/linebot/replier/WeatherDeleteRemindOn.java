package com.example.linebot.replier;


import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;

public class WeatherDeleteRemindOn implements Replire{
    private final String text;

    public WeatherDeleteRemindOn(String text) {
        this.text=text;
    }

    @Override
    public Message reply(){
        return new TextMessage(text + "を削除しました。");
    }
}
