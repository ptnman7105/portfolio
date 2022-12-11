package com.example.linebot.value;

import com.example.linebot.replier.Intent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeatherStaticsSlot {
    private final String pushText;
    private final String pushStatics;

    public WeatherStaticsSlot(String text){
        String regexp = Intent.WEATHER_STATICS.getRegexp();
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(text);

        if(matcher.matches()) {
            this.pushText = matcher.group(1);
            this.pushStatics = matcher.group(2);

        }
        else{
            throw new IllegalArgumentException("textをスロットに分けられません");
        }
    }

    public String getPushText(){
        return pushText;
    }

    public String getPushStatics(){
        return pushStatics;
    }
}
