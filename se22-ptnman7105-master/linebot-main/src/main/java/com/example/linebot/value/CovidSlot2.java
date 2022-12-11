package com.example.linebot.value;

import com.example.linebot.replier.Intent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CovidSlot2 {
    private final String region;

    public CovidSlot2(String text){
        String regexp= Intent.COVID_MEAN.getRegexp();
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(text);
        if(matcher.matches()){
            region = matcher.group(1);
        }
        else{
            throw new IllegalArgumentException(
                    "textをスロットに分けられません"
            );
        }
    }
    public String getRegion(){
        return region;
    }
}
