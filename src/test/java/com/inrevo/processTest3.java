package com.inrevo;

import com.alibaba.fastjson.JSON;
import com.inrevo.controller.VideoController;
import com.inrevo.model.CyclePlay;
import com.inrevo.model.Job;
import com.inrevo.model.StreamGobbler;
import com.inrevo.model.StreamGobblerForError;
import com.inrevo.util.ThreadUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class processTest3 {
    public static void main(String[] args) {
        List<String> videoList = new ArrayList<>();
        videoList.add("rtsp://120.205.96.36:554/live/ch16022919053041099962.sdp?playtype=1&boid=001&backupagent=120.205.32.36:554&clienttype=1&time=20161205230814+08&life=172800&ifpricereqsnd=1&vcdnid=001&userid=&mediaid=ch16022919053041099962&ctype=2&TSTVTimeLife=60&contname=&authid=0&terminalflag=1&UserLiveType=0&stbid=&nodelevel=3");
        videoList.add("rtsp://184.72.239.149/vod/mp4:BigBuckBunny_175k.mov");
        videoList.add("rtsp://120.205.96.36:554/live/ch16022919053041099962.sdp?playtype=1&boid=001&backupagent=120.205.32.36:554&clienttype=1&time=20161205230814+08&life=172800&ifpricereqsnd=1&vcdnid=001&userid=&mediaid=ch16022919053041099962&ctype=2&TSTVTimeLife=60&contname=&authid=0&terminalflag=1&UserLiveType=0&stbid=&nodelevel=3");
        videoList.add("rtsp://184.72.239.149/vod/mp4:BigBuckBunny_175k.mov");
//        videoList.add("rtsp://120.205.96.36:554/live/ch16022919053041099962.sdp?playtype=1&boid=001&backupagent=120.205.32.36:554&clienttype=1&time=20161205230814+08&life=172800&ifpricereqsnd=1&vcdnid=001&userid=&mediaid=ch16022919053041099962&ctype=2&TSTVTimeLife=60&contname=&authid=0&terminalflag=1&UserLiveType=0&stbid=&nodelevel=3");
//        videoList.add("rtsp://184.72.239.149/vod/mp4:BigBuckBunny_175k.mov");
        run(videoList);
    }

    public static void run(List<String> list) {
        for (String s : list) {
            String cmd = "D:/ffmpeg/ffmpeg.exe -i  " + s + " -vcodec copy -s 300*200 -f flv rtmp://localhost:1901/hls/monitor1";
            System.out.println(cmd);
            Process process = null;
            try {
                process = Runtime.getRuntime().exec(cmd);
                //接受流不然服务会被挂起
                StreamGobblerForError errorGobbler = new StreamGobblerForError(CyclePlay.getProcessId(process),process.getErrorStream());
                errorGobbler.start();
                process.waitFor(30000, TimeUnit.MILLISECONDS);
                process.destroy();
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
//        Vector<String> vector = CyclePlay.getVector();
//        for (String s1 : vector) {
//            System.out.println(s1);
//        }
//        System.out.println(vector.size());
    }

}



















