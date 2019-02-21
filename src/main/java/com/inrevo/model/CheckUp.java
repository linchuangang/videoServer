package com.inrevo.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

public class CheckUp extends Thread {

    Process process;
    StreamGobblerForError streamGobblerForError;

    public CheckUp(Process process, StreamGobblerForError streamGobblerForError) {
        this.process = process;
        this.streamGobblerForError = streamGobblerForError;
    }

    public void run() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                List<String> lines = streamGobblerForError.getLines();
                lines = lines.stream().filter(s -> s.contains("bitrate=")).collect(Collectors.toList());
                List<String> size = new ArrayList<>();
                lines.forEach(s -> {
                    String[] infos = s.split("bitrate=");
                    String info = infos[1].split("kbits/s")[0];
                    Double number = Double.parseDouble(info.trim());
                    if (number < 1000) {
                        size.add(info);
                    }
                });
                System.out.println("check up===" + size.size());
                if (size.size() > 10) {
                    process.destroyForcibly();
                    timer.cancel();
                    System.out.println("---视频转码过低，强行杀死---");
                }
                if (!process.isAlive()) {
                    timer.cancel();
                }
            }
        }, 0, 2000);
    }

}
