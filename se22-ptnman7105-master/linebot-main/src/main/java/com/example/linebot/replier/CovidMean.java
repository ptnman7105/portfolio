package com.example.linebot.replier;

import com.example.linebot.value.CovidItem;
import com.example.linebot.value.CovidItemElement;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class CovidMean implements Replire{
    private static final String MESSAGE_FORMAT =
            "平均は%d人、%sの%s月%s日の感染者数の差は%d人";


    private final CovidItem item;

    public CovidMean(CovidItem item){
        this.item=item;
    }


    @Override
    public Message reply(){
        String body ="データがありません";
        List<CovidItemElement> list = item.getItemList();
        if(list.size() > 0){
            body = getMessageOfLast();
        }
        return new TextMessage(body);
    }

    private String getMessageOfLast(){
        List<CovidItemElement> list = item.getItemList();
        CovidItemElement atLast[] = new CovidItemElement[8];
        int a[]=new int[7];
        int result=0;
        for(int i=0;i<8;i++) {
            atLast[i] = list.get(i);
        }
        for(int i=0;i<7;i++) {
            a[i]=atLast[i].getNpatients()-atLast[i+1].getNpatients();
        }
        for(int i=0;i<7;i++){
        result=a[i]+result;
        }
        result=result/7;
        LocalDate date = atLast[0].getDate();
        int month = date.getMonthValue();
        int dayOfMonth = date.getDayOfMonth();
        String region = atLast[0].getNameJp();
        int npatients;
        npatients=a[0]-result;
        return String.format(
                MESSAGE_FORMAT,result,region,month,dayOfMonth,npatients);
    }
}
