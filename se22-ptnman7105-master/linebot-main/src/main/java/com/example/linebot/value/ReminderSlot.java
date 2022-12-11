package com.example.linebot.value;
import com.example.linebot.replier.Intent;

import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ReminderSlot {

    private final LocalTime pushAt;
    private final String pushText;

    public ReminderSlot(String text) {
        String regexp = Intent.REMINDER.getRegexp();
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(text);
        if (matcher.matches()){

            int hours = Integer.parseInt(matcher.group(1));
            int minutes = Integer.parseInt(matcher.group(2));
            this.pushAt = LocalTime.of(hours,minutes);
            this.pushText = matcher.group(3);

        }
        else {
            throw new IllegalArgumentException("test をスロットに分けられません");
        }
    }

    public LocalTime getPushAt() {
        return pushAt;
    }

    public String getPushText() {
        return pushText;
    }
}