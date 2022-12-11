package com.example.linebot.replier;

import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;


public class WeatherRemindOn implements Replire{
    private final String text;

    public WeatherRemindOn(String text) {
        this.text=text;
    }

    @Override
    public Message reply(){
        return new TextMessage(text + "を登録しました");
    }
}
