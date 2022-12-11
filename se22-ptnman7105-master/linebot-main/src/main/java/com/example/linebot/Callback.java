package com.example.linebot;

import com.example.linebot.replier.*;
import com.example.linebot.service.CovidGovService;
import com.example.linebot.service.CovidGovServiceMean;
import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.event.message.ImageMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.event.MessageEvent;
import com.example.linebot.service.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.linebot.service.WeatherReminderService;

@LineMessageHandler
public class Callback {
    private static final Logger log = LoggerFactory.getLogger(Callback.class);

    private final ReminderService remainderService;

    private final CovidGovService covidGovService;

    private final CovidGovServiceMean covidGovServiceMean;

    private final WeatherReminderService weatherReminderService;

    @Autowired
    public Callback(ReminderService remainderService,CovidGovService covidService,CovidGovServiceMean covidGovServiceMean,WeatherReminderService weatherReminderService){
        this.remainderService = remainderService;
        this.covidGovService = covidService;
        this.covidGovServiceMean = covidGovServiceMean;
        this.weatherReminderService = weatherReminderService;
    }



    // フォローイベントに対応する
    @EventMapping
    public Message handleFollow(FollowEvent event) {
        // 実際はこのタイミングでフォロワーのユーザIDをデータベースにに格納しておくなど
        Follow follow = new Follow(event);
        return follow.reply();
    }

    // 文章で話しかけられたとき（テキストメッセージのイベント）に対応する
    @EventMapping
    public Message handleMessage(MessageEvent<TextMessageContent> event) {
        TextMessageContent tmc = event.getMessage();
        String text = tmc.getText();
        Intent intent = Intent.whichIntent(text);
        switch (intent) {

            case WEATHER_STATICS:
                WeatherStaticsReminderOn weatherStaticsReminderOn = weatherReminderService.staticsItem(event);
                return weatherStaticsReminderOn.reply();

            case WEATHER_REMINDER:
                WeatherRemindOn weatherRemindOn = weatherReminderService.dePeplyOfNewItem(event);
                return weatherRemindOn.reply();

            case WEATHER_DELETE:
                WeatherDeleteRemindOn weatherDeleteRemindOn = weatherReminderService.deletePreviousItem(event);
                return weatherDeleteRemindOn.reply();

            case WEATHER_DAY:
                WeatherResarchRemindOn weatherResarchRemindOn = weatherReminderService.daysItem(event);
                return weatherResarchRemindOn.reply();

            case REMINDER:
                RemindOn reminderOn = remainderService.deReplyOfNewItem(event);
                return reminderOn.reply();

            case COVID_TOTAL:
                CovidReport covidReport = covidGovService.doReplyWithCovid(event);
                return covidReport.reply();

            case COVID_MEAN:
                CovidMean covidMean = covidGovServiceMean.doReplyWithCovid(event);
                return covidMean.reply();



            case UNKNOWN:
            default:
                Parrot parrot = new Parrot(event);
                return parrot.reply();
        }

    }
}
