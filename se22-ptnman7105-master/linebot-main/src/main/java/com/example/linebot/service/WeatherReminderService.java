package com.example.linebot.service;

import com.example.linebot.replier.*;
import com.example.linebot.value.*;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.linebot.repository.ReminderRepository;
import com.linecorp.bot.model.PushMessage;

import java.security.KeyStore;
import java.util.List;
import com.example.linebot.repository.WeatherRepository;

@Service
public class WeatherReminderService {

    private final WeatherRepository repository;

    @Autowired
    public WeatherReminderService(WeatherRepository weatherRepository) {
        this.repository=weatherRepository;
    }

    public WeatherRemindOn dePeplyOfNewItem(MessageEvent<TextMessageContent> event) {
        String userId = event.getSource().getUserId();
        TextMessageContent tmc = event.getMessage();
        String text = tmc.getText();
        WeatherSlot slot = new WeatherSlot(text);
        WeatherItem item = new WeatherItem(userId,slot);

        repository.insert(item);

        return new WeatherRemindOn(text);
    }

    public WeatherDeleteRemindOn deletePreviousItem(MessageEvent<TextMessageContent> event) {
        TextMessageContent tmc = event.getMessage();
        String text = tmc.getText();
        WeatherDeleteSlot slot = new WeatherDeleteSlot(text);
        WeatherDeleteItem item = new WeatherDeleteItem(slot);

        repository.deletePreviousItems(item);
        return new WeatherDeleteRemindOn(text);
    }

    public WeatherResarchRemindOn daysItem(MessageEvent<TextMessageContent> event) {
        String userId = event.getSource().getUserId();
        TextMessageContent tmc = event.getMessage();
        String text = tmc.getText();
        WeatherResarchSlot slot = new WeatherResarchSlot(text);
        weatherResarchItem item = new weatherResarchItem(userId, slot);
        weatherCsv w = new weatherCsv();
        text = w.weatherReportCsv(item.getSlot().getPushText());
        text = item.getSlot().getPushText() + "の温度は" + text + "度";

        return new WeatherResarchRemindOn(text);
    }

    public WeatherStaticsReminderOn staticsItem(MessageEvent<TextMessageContent> event) {
        String userId = event.getSource().getUserId();
        TextMessageContent tmc = event.getMessage();
        String text = tmc.getText();
        WeatherStaticsSlot slot = new WeatherStaticsSlot(text);
        WeatherStaticsItem item = new WeatherStaticsItem(userId, slot);
        weatherCsv w = new weatherCsv();
        String t = item.getSlot().getPushText();
        String s = item.getSlot().getPushStatics();
        text = w.WeatherStaticCsv(t,s);

        return new WeatherStaticsReminderOn(text);

    }

    public List<PushMessage> doPushReminderItems() {
        List<WeatherTuple> ReminderTuples = repository.findAllItems();

        List<PushMessage> pushMessages = ReminderTuples.stream().map(tuple -> toPushMessage(tuple)).toList();

        return pushMessages;
    }

    private PushMessage toPushMessage(WeatherTuple tuple) {
        String userId = tuple.getUserId();
        String pushText = tuple.getPushText();
        float pushEstimateNumber=tuple.getPushEstimateNumber();
        boolean python= false;
        String body="default";

        if(python=true) {
            body = String.format("今日の%sは%.2fを超えます!!気を付けて過ごしてください。",pushText,pushEstimateNumber);
        }
        else{
            body = String.format("今日の%sは%.2fは超えない予想です。快適な一日をお過ごしください。",pushText,pushEstimateNumber);
        }

        return new PushMessage(userId,new TextMessage(body));
    }
}
