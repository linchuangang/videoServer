package com.inrevo.controller;

import com.alibaba.fastjson.JSON;
import com.inrevo.model.CyclePlay;
import com.inrevo.model.StreamGobbler;
import com.inrevo.util.DateUtils;
import com.inrevo.util.JsonMessageUtils;
import com.inrevo.util.ReadUtil;
import com.inrevo.util.ThreadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/cyclePlay")
public class CyclePlayController {
    private static final Logger logger = LoggerFactory.getLogger(CyclePlayController.class);
    String ffmpegAddress = ReadUtil.get("ffmpegAddress");

    //http://localhost:8080/videoServer/cyclePlay/play?lineAreaProcess=CNC
    @RequestMapping(value = "/play", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String play(String lineAreaProcess) {
        List<String> videoList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            videoList.add("rtsp://184.72.239.149/vod/mp4:BigBuckBunny_175k.mov");
        }
        String targetUrl = "rtmp://localhost:1901/hls/monitor1";
        CyclePlay cyclePlay = new CyclePlay(videoList, lineAreaProcess, targetUrl);
        cyclePlay.start();
        return JsonMessageUtils.getSuccessJson(targetUrl);
    }
    @RequestMapping(value = "/play2", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String play2() {
        List<String> videoList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            videoList.add("rtsp://184.72.239.149/vod/mp4:BigBuckBunny_175k.mov");
        }
        for (int i = 0; i < 10; i++) {
            String cmd = ffmpegAddress + " -i " + videoList.get(i) + " -vcodec copy -f flv rtmp://localhost:1901/hls/monitor1";
            Process process = null;
            try {
                process = Runtime.getRuntime().exec(cmd);
                ThreadUtil.read(process);
                process.waitFor(30 * 1000, TimeUnit.MILLISECONDS);
                logger.info(i + "--------------------------------------------------over");
                process.destroy();
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
            System.out.println(JSON.toJSONString(process));
        }
        return null;
    }

    @RequestMapping(value = "/play3", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String play3() {
        List<String> videoList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            videoList.add("rtsp://184.72.239.149/vod/mp4:BigBuckBunny_175k.mov");
        }
//        for (int i = 0; i < 10; i++) {
            String cmd = ffmpegAddress + " -i " + videoList.get(0) + " -vcodec copy -f flv rtmp://localhost:1901/hls/monitor1";
            Process process = null;
            try {
                process = Runtime.getRuntime().exec(cmd);
                ThreadUtil.read(process);
//                process.waitFor(30 * 1000, TimeUnit.MILLISECONDS);
//                logger.info(i + "--------------------------------------------------over");
//                process.destroy();
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
            System.out.println(JSON.toJSONString(process));
//        }
        return null;
    }
}
