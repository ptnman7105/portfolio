package com.example.linebot.value;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import static java.lang.Math.sqrt;

public class weatherCsv {



    public String weatherReportCsv(String text){

        FileInputStream fi = null;
        InputStreamReader is = null;
        BufferedReader br = null;
        String rast="";
        int x=0;
        try{
        fi = new FileInputStream("C:/Users/takumi/soft/se22-ptnman7105/linebot-main/src/main/java/com/example/linebot/weather-report4-java.csv");
        is = new InputStreamReader(fi);
        br = new BufferedReader(is);
        String line;
        String[] arr=new String[7];


        int i=0;
        while((line=br.readLine()) != null){


    if(i == 0){

        arr[0]="date";
        arr[1]="precipitation";
        arr[2]="pressure";
        arr[3]="humidity";
        arr[4]="windspeed";
        arr[5]="temperature";
        arr = line.split(",");
    } else {


        String[] data = line.split(",");

        int colno = 0;
        for (String column : arr) {
            if(text.equals(data[colno])){
                x=1;

            }
            if(x>=1){
                x++;
                if(x==7){
                    rast=data[colno];
                    x=0;
                }
            }


            colno++;
        }
    }
    i++;
        }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                br.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rast;
    }

    public String WeatherStaticCsv(String text,String statics){
        FileInputStream fi = null;
        InputStreamReader is = null;
        BufferedReader br = null;
        float sum=0;
        float sum2=0;
        String tmp;
        float element;
        String rast="";
        int x=0;
        float tmp2=0;
        float tmp3=10000;
        float result=0;
        double result2=0;
        double result3=0;
        String rast2="";
        int i2=0;
        int ix=0;
        int s=0;
        double sumx=0;
        int i3=0;
        float sums[] = new float[36];
        try {
            fi = new FileInputStream("C:/Users/takumi/soft/se22-ptnman7105/linebot-main/src/main/java/com/example/linebot/weather-report4-java.csv");
            is = new InputStreamReader(fi);
            br = new BufferedReader(is);
            String line;
            String[] arr = new String[7];


            int i = 0;
            while ((line = br.readLine()) != null) {


                if (i == 0) {

                    arr[0] = "date";
                    arr[1] = "precipitation";
                    arr[2] = "pressure";
                    arr[3] = "humidity";
                    arr[4] = "windspeed";
                    arr[5] = "temperature";
                    arr = line.split(",");
                } else {


                    String[] data = line.split(",");

                if(text.equals("気温")) {
                    s=5;
                }
                else if(text.equals("湿度")) {
                    s=3;
                }
                else if(text.equals("風速")) {
                    s=4;
                }
                else if(text.equals("風速")) {
                    s=2;
                }
                    if (statics.equals("平均")) {
                        for (String column : arr) {
                            if (column.equals("temperature")) {
                                tmp = data[s];
                                element = Float.parseFloat(tmp);
                                sum = sum + element;
                            }
                        }

                    }

                    if (statics.equals("最大")) {
                        for (String column : arr) {
                            if (column.equals("temperature")) {
                                tmp = data[s];
                                element = Float.parseFloat(tmp);
                                if(tmp2<element){
                                    tmp2=element;
                                }
                            }
                        }

                    }

                    if (statics.equals("最小")) {
                        for (String column : arr) {
                            if (column.equals("temperature")) {
                                tmp = data[s];
                                element = Float.parseFloat(tmp);
                                if(tmp2>element){
                                    tmp2=element;
                                }
                            }
                        }

                    }

                    if (statics.equals("母平均推定")) {
                        if(i>330) {
                            for (String column : arr) {
                                if (column.equals("temperature")) {
                                    tmp = data[s];
                                    element = Float.parseFloat(tmp);
                                    sums[ix]=element;


                                    ix++;
                                }
                            }

                        }

                    }



                }
                i++;
                i2=i;
            }

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                br.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(statics.equals("平均")) {
            result = sum / (i2 + 1);
        }
        else if(statics.equals("最大")){
            result = tmp2;
        }
        else if(statics.equals("母平均推定")){
            for (float suitei : sums){
                sum=sum+suitei;
                i3++;
            }
            System.out.println(i3);
            sum = sum/i3;

            for (float suitei2:sums){
                sum2=sum2+(sum-suitei2)*(sum-suitei2);
            }
            sumx = sqrt(sum2/i3);



            System.out.println(sum);

            result2 = (sum)+sumx*2.045/(sqrt(i2-330));
            result3 = (sum)-sumx*2.045/(sqrt(i2-330));
            rast2=result3 + ("<μ<") +result2;

        }
        if(statics.equals("母平均推定")) {
            rast = text + ("の") + statics + ("は") + rast2;
        }
        else {
            rast = text + ("の") + statics + ("は") + result;
        }
        return rast;
    }

}
