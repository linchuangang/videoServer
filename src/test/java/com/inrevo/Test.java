package com.inrevo;

import com.inrevo.util.DateUtils;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Test {
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(DateUtils.formatToDateTime(new Date())+"22");
                if(new Date().getTime()> DateUtils.parseByDateTime("2019-02-18 18:22:00").getTime()){
                    System.out.println("over");
                    timer.cancel();
                }
            }
        }, 0, 2000);
    }
}
