package com.example.linebot.replier;


import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.client.MessageContentResponse;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.ImageMessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.concurrent.ExecutionException;

public class saveImages implements Replire{
    private MessageEvent<ImageMessageContent> event;
    public saveImages(MessageEvent< ImageMessageContent > event) {
        this.event = event;
    }

    @Override
    public Message reply(){



        ImageMessageContent tmc = this.event.getMessage();






        System.out.println(tmc.getId());
        System.out.println(tmc);
        String text = "test";
        return new TextMessage(text);

    }
}
