package com.example.linebot.service;

import com.example.linebot.replier.CovidMean;
import com.example.linebot.replier.CovidReport;
import com.example.linebot.repository.CovidGovRepository;
import com.example.linebot.value.CovidItem;
import com.example.linebot.value.CovidSlot;
import com.example.linebot.value.CovidSlot2;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CovidGovServiceMean {
    private final CovidGovRepository covidGovRepository;

    @Autowired
    public CovidGovServiceMean(CovidGovRepository covidGovRepository){
        this.covidGovRepository = covidGovRepository;
    }

    public CovidMean doReplyWithCovid(
            MessageEvent<TextMessageContent> event) {
        TextMessageContent tmc = event.getMessage();
        String text = tmc.getText();
        CovidSlot2 covidSlot = new CovidSlot2(text);
        String region = covidSlot.getRegion();
        CovidItem covidItem =
                covidGovRepository.findCovidGovAPI(region);
        return new CovidMean(covidItem);
    }
}
