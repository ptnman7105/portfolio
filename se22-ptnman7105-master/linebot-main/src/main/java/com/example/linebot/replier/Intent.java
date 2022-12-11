package com.example.linebot.replier;

import java.util.EnumSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Intent {

    REMINDER("^(\\d{1,2}):(\\d{1,2})に(.{1,32})$"),
    WEATHER_REMINDER("([+-]?(?:\\d+\\.?\\d*|\\.\\d+))を基準に(.{1,2})の推定"),
    WEATHER_DELETE("([+-]?(?:\\d+\\.?\\d*|\\.\\d+))の(.{1,2})を削除"),
    COVID_TOTAL("^(.*)の感染者数$"),
    COVID_MEAN("^(.*)の平均比較$"),
    WEATHER_STATICS("(.{1,2})の(.{1,5})を計算"),
    WEATHER_DAY("(\\d{4}/\\d{1,3}/\\d{1,3})の気温"),
    UNKNOWN(".+");

    private final String regexp;

    private Intent(String regexp) {
        this.regexp = regexp;
    }

    public static Intent whichIntent(String text) {
        EnumSet<Intent> set = EnumSet.allOf(Intent.class);

        for (Intent intent : set) {
            if(Pattern.matches(intent.regexp,text)){
                return intent;
            }
        }
        return UNKNOWN;
    }
    public String getRegexp(){
        return regexp;
    }
}