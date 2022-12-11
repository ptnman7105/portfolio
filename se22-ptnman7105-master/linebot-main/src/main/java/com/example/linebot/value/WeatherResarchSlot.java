package com.example.linebot.value;

import com.example.linebot.replier.Intent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeatherResarchSlot {
    private final String pushText;

    public WeatherResarchSlot(String text){
        String regexp = Intent.WEATHER_DAY.getRegexp();
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(text);

        if(matcher.matches()) {
            this.pushText = matcher.group(1);

        }
        else{
            throw new IllegalArgumentException("textをスロットに分けられません");
        }
    }

    public String getPushText(){
        return pushText;
    }
}
