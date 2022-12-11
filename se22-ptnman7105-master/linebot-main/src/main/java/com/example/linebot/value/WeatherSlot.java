package com.example.linebot.value;

import com.example.linebot.replier.Intent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeatherSlot {

    private final String pushText;
    private final float pushEstimateNumber;

    public WeatherSlot(String text){
        String regexp = Intent.WEATHER_REMINDER.getRegexp();
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(text);

        if(matcher.matches()) {
            this.pushEstimateNumber = Float.parseFloat(matcher.group(1));
            this.pushText = matcher.group(2);

        }
        else{
            throw new IllegalArgumentException("textをスロットに分けられません");
        }
    }

    public String getPushText(){
        return pushText;
    }

    public float getPushEstimateNumber(){
        return pushEstimateNumber;
    }


}
