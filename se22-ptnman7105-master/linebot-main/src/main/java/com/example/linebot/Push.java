package com.example.linebot;

import com.example.linebot.service.WeatherReminderService;
import com.linecorp.bot.model.response.BotApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;
import com.example.linebot.service.ReminderService;
import java.util.List;

@RestController
public class Push {

    private static final Logger log = LoggerFactory.getLogger(Push.class);
    private final ReminderService reminderService;
    private final WeatherReminderService weatherReminderService;

    private String userId = "U9de42e150523af381e418340fac5e308";

    private final LineMessagingClient messagingClient;

    @Autowired
    public Push(LineMessagingClient lineMessagingClient,ReminderService reminderService,WeatherReminderService weatherReminderService) {
        this.messagingClient = lineMessagingClient;
        this.reminderService = reminderService;
        this.weatherReminderService = weatherReminderService;

    }

    // テスト
    @GetMapping("test")
    public String hello(HttpServletRequest request) {
        return "Get from " + request.getRequestURL();
    }

    @GetMapping("timetone")
    //@Scheduled(cron = "0*/1****",zone = "Asia/Tokyo")
    public String pushTimeTone() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("a K:mm");
        String text = dtf.format(LocalDateTime.now());
        try {
            PushMessage pMsg = new PushMessage(userId, new TextMessage(text));
            BotApiResponse resp = messagingClient.pushMessage(pMsg).get();
            log.info("Sent messages; {}",resp);
        }
        catch (InterruptedException | ExecutionException e){
            throw new RuntimeException(e);
        }
        return text;
    }


    //@Scheduled(cron = "0 */0 1 * * *",zone = "Asia/Tokyo")
    @Scheduled(cron = "0 10 * * * *",zone = "Asia/Tokyo")
    public void pushReminder() {
        try {
            List<PushMessage> messages = weatherReminderService.doPushReminderItems();

            for (PushMessage message : messages) {
                BotApiResponse resp = messagingClient.pushMessage(message).get();
                log.info("Sent messages: {}",resp);
            }
        }
        catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

}