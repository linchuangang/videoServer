package com.inrevo;

import com.alibaba.fastjson.JSON;
import com.inrevo.model.CheckUp;
import com.inrevo.model.CyclePlay;
import com.inrevo.model.StreamGobbler;
import com.inrevo.model.StreamGobblerForError;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class processTest {
    public static void main(String[] args) {
        String address = "rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov";
//        address = "rtsp://192.168.0.107:8554/2";
//        address="rtsp://120.205.96.36:554/live/ch16022919053041099962.sdp?playtype=1&boid=001&backupagent=120.205.32.36:554&clienttype=1&time=20161205230814+08&life=172800&ifpricereqsnd=1&vcdnid=001&userid=&mediaid=ch16022919053041099962&ctype=2&TSTVTimeLife=60&contname=&authid=0&terminalflag=1&UserLiveType=0&stbid=&nodelevel=3";
        run(address);

    }

    public static void run(String address) {
        try {
            String cmd = "D:/ffmpeg/ffmpeg.exe -i   " + address + " -vcodec copy -s 300*200 -f flv rtmp://localhost:1901/hls/monitor1";
            System.out.println(cmd);
            Process process = Runtime.getRuntime().exec(cmd);
            System.out.println(JSON.toJSONString(process));
            StreamGobblerForError errorGobbler = new StreamGobblerForError(CyclePlay.getProcessId(process),process.getErrorStream());
            StreamGobbler outputGobbler = new StreamGobbler(process.getInputStream());
            errorGobbler.start();
            outputGobbler.start();
            CheckUp checkUp = new CheckUp(process, errorGobbler);
            checkUp.start();
            process.waitFor(60 * 1000, TimeUnit.MILLISECONDS);
            process.destroy();
            System.out.println("---");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}



















