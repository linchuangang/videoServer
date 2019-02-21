package com.inrevo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.inrevo.model.*;
import com.inrevo.util.HttpUtils;
import com.inrevo.util.JsonMessageUtils;
import com.inrevo.util.ReadUtil;
import com.inrevo.util.ThreadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

//https://www.cnblogs.com/yisionwa/p/5882585.html
@Controller
@RequestMapping("/video")
public class VideoController {
    public static final Logger logger = LoggerFactory.getLogger(VideoController.class);
    //存放视频在看的人数
    public static Map<String, List<String>> videoViewNumberMap = new ConcurrentHashMap<>();
    //存放视频转码前后对应关系
    public static Map<String, String> transforRelationMap = new ConcurrentHashMap<>();
    //存放制程和轮播的关系
    public static Map<String, CyclePlay> cyclePlayMap = new ConcurrentHashMap<>();

    public static List<Job> currentJobs = new ArrayList<>();

    public static List<Job> jobhistory = new ArrayList<>();

    String getMonitorInfo = ReadUtil.get("getMonitorInfo");

    String targetUrlBerfor = ReadUtil.get("targetUrlBerfor");

    String ffmpegAddress = ReadUtil.get("ffmpegAddress");


    @RequestMapping(value = "/getInfo", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public void getInfo() {
        System.out.println(JSON.toJSONString(videoViewNumberMap));
        System.out.println(JSON.toJSONString(transforRelationMap));
        System.out.println(JSON.toJSONString(cyclePlayMap));
    }


    @RequestMapping(value = "/getCurrentJobInfo", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String getCurrentJobInfo() {
        System.out.println("getCurrentJobInfo------------");
        List<Job> jobs = null;
        try {
            jobs = deepCopy(currentJobs);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        for (Job job : jobs) {
            job.setIps(videoViewNumberMap.get(job.getVideoName()));
        }
        return JsonMessageUtils.getListJson(jobs);
    }

    @RequestMapping(value = "/getJobHistoryInfo", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String getJobHistoryInfo() {
        return JsonMessageUtils.getListJson(jobhistory);
    }

    @RequestMapping(value = "/singlePlay", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String singlePlay(String lineAreaProcess) {
        Map<String, List<MonitorInfo>> monitorInfoMap = getMonitorInfoMap();
        List<MonitorInfo> monitorInfoList = monitorInfoMap.get(lineAreaProcess);
        logger.info(lineAreaProcess + "对应monitor：" + JSON.toJSONString(monitorInfoList));
        List<Info> infoList = new ArrayList<>();
        monitorInfoList.forEach(monitorInfo -> infoList.addAll(monitorInfo.getListInfo()));
        List<String> result = infoList.stream().map(Info::getMonitorPlay).collect(Collectors.toList());
        logger.info(lineAreaProcess + "：下的摄像头地址" + JSON.toJSONString(result));

        String targetUrl = targetUrlBerfor + lineAreaProcess;
        String cmd = ffmpegAddress + " -i " + result.get(0) + " -vcodec copy -s 300*200 -f flv " + targetUrl;
        Process process = null;
        try {
            logger.info(lineAreaProcess + "---start---");
            process = Runtime.getRuntime().exec(cmd);
            //接受流不然服务会被挂起
            ThreadUtil.read(process);
            process.waitFor(60 * 1000 * 3, TimeUnit.MILLISECONDS);
            process.destroy();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return JsonMessageUtils.getListJson(jobhistory);
    }

    @RequestMapping(value = "/cyclePlay", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String cyclePlay(String lineAreaProcess) {
        if (lineAreaProcess == null || lineAreaProcess.isEmpty()) {
            return JsonMessageUtils.getErrorJson("lineAreaProcess 不能为空");
        }
        logger.info("制程转码前后对应表" + JSON.toJSONString(transforRelationMap));
        if (transforRelationMap.containsKey(lineAreaProcess)) {
            return JsonMessageUtils.getSuccessJson(transforRelationMap.get(lineAreaProcess));
        }
        Map<String, List<MonitorInfo>> monitorInfoMap = getMonitorInfoMap();
        List<MonitorInfo> monitorInfoList = monitorInfoMap.get(lineAreaProcess);
        logger.info(lineAreaProcess + "对应monitor：" + JSON.toJSONString(monitorInfoList));
        List<Info> infoList = new ArrayList<>();
        monitorInfoList.forEach(monitorInfo -> infoList.addAll(monitorInfo.getListInfo()));
        List<String> result = infoList.stream().map(Info::getMonitorPlay).collect(Collectors.toList());
        logger.info(lineAreaProcess + "：下的摄像头地址" + JSON.toJSONString(result));
        String targetUrl = targetUrlBerfor + lineAreaProcess;


//        List<String> videoList = new ArrayList<>();
//        videoList.add("rtsp://120.205.96.36:554/live/ch16022919053041099962.sdp?playtype=1&boid=001&backupagent=120.205.32.36:554&clienttype=1&time=20161205230814+08&life=172800&ifpricereqsnd=1&vcdnid=001&userid=&mediaid=ch16022919053041099962&ctype=2&TSTVTimeLife=60&contname=&authid=0&terminalflag=1&UserLiveType=0&stbid=&nodelevel=3");
//        videoList.add("rtsp://184.72.239.149/vod/mp4:BigBuckBunny_175k.mov");
//        videoList.add("rtsp://120.205.96.36:554/live/ch16022919053041099962.sdp?playtype=1&boid=001&backupagent=120.205.32.36:554&clienttype=1&time=20161205230814+08&life=172800&ifpricereqsnd=1&vcdnid=001&userid=&mediaid=ch16022919053041099962&ctype=2&TSTVTimeLife=60&contname=&authid=0&terminalflag=1&UserLiveType=0&stbid=&nodelevel=3");
//        videoList.add("rtsp://184.72.239.149/vod/mp4:BigBuckBunny_175k.mov");
//        videoList.add("rtsp://120.205.96.36:554/live/ch16022919053041099962.sdp?playtype=1&boid=001&backupagent=120.205.32.36:554&clienttype=1&time=20161205230814+08&life=172800&ifpricereqsnd=1&vcdnid=001&userid=&mediaid=ch16022919053041099962&ctype=2&TSTVTimeLife=60&contname=&authid=0&terminalflag=1&UserLiveType=0&stbid=&nodelevel=3");
//        videoList.add("rtsp://184.72.239.149/vod/mp4:BigBuckBunny_175k.mov");

//        videoList.add("rtsp://184.72.239.149/vod/mp4:BigBuckBunny_175k.mov");
//        videoList.add("rtsp://192.168.0.107:8554/2");
//        videoList.add("rtsp://184.72.239.149/vod/mp4:BigBuckBunny_175k.mov");
//        videoList.add("rtsp://192.168.0.107:8554/2");
//        videoList.add("rtsp://184.72.239.149/vod/mp4:BigBuckBunny_175k.mov");
//        videoList.add("rtsp://192.168.0.107:8554/2");

//        String targetUrl = "rtmp://localhost:1901/hls/monitor1";
//        CyclePlay cyclePlay = new CyclePlay(videoList, lineAreaProcess, targetUrl);


        //存储转换后对应关系
        transforRelationMap.put(lineAreaProcess, targetUrl);
        //异步循环转换视频流
        CyclePlay cyclePlay = new CyclePlay(result, lineAreaProcess, targetUrl);
        cyclePlay.start();
        cyclePlayMap.put(lineAreaProcess, cyclePlay);

        return JsonMessageUtils.getSuccessJson(targetUrl);
    }


    //ffmpeg -i "rtsp://admin:one752*cdzx@10.249.25.17/H.264/ch14/main/av_stream" -vcodec copy -f flv "rtmp://localhost:1901/hls/monitor1"
    //ffmpeg -i "rtsp://184.72.239.149/vod/mp4:BigBuckBunny_175k.mov" -vcodec copy -f flv "rtmp://localhost:1901/hls/monitor1"
    @RequestMapping(value = "/onPlay", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public void onPlay(HttpServletRequest request, OnPlay onPlay) {
        logger.info("onPlay===" + JSON.toJSONString(onPlay));
        logger.info("视频观看人数" + JSON.toJSONString(videoViewNumberMap));
        //记录视频的访问人数
        if (videoViewNumberMap.containsKey(onPlay.getName())) {
            List<String> ips = videoViewNumberMap.get(onPlay.getName());
            ips.add(onPlay.getAddr());
            videoViewNumberMap.put(onPlay.getName(), ips);
        } else {
            List<String> ips = new ArrayList<>();
            ips.add(onPlay.getAddr());
            videoViewNumberMap.put(onPlay.getName(), ips);
        }
    }

    @RequestMapping(value = "/onPlayDone", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public void onPlayDone(HttpServletRequest request, OnPlayDone onPlayDone) {
        logger.info("onPlayDone===" + JSON.toJSONString(onPlayDone));
        logger.info("视频观看人数" + JSON.toJSONString(videoViewNumberMap));
        //减少视频的访问人数
        if (videoViewNumberMap.containsKey(onPlayDone.getName())) {
            List<String> ips = videoViewNumberMap.get(onPlayDone.getName());
            //最后一个人访问了把视频关掉
            if (ips.size() == 1) {
                //移除视频
                videoViewNumberMap.remove(onPlayDone.getName());
                //移除对应关系
                transforRelationMap.remove(onPlayDone.getName());
                //强行杀掉轮播线程
                CyclePlay cyclePlay = cyclePlayMap.get(onPlayDone.getName());
                logger.info("杀死轮播线程" + JSON.toJSONString(cyclePlay));
                if (cyclePlay != null) {
                    cyclePlay.destroyProcess(onPlayDone.getName());
                }
                logger.info(onPlayDone.getName() + "----对应窗口全部关闭----");
            } else {
                ips.remove(onPlayDone.getAddr());
                videoViewNumberMap.put(onPlayDone.getName(), ips);
            }
            System.out.println("onPlayDone over");
        }
    }

    @RequestMapping(value = "/onDone", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public void onDone(HttpServletRequest request, OnDone onDone) {
//        logger.info("onDone===" + JSON.toJSONString(onDone));
//        logger.info("视频观看人数" + JSON.toJSONString(videoViewNumberMap));

    }


    @RequestMapping(value = "/onConnect", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public void onConnect(HttpServletRequest request, OnConnect onConnect) {
//        logger.info("onConnect===" + JSON.toJSONString(onConnect));
    }


    @RequestMapping(value = "/onPublish", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public void onPublish(HttpServletRequest request, OnPublish onPublish) {
//        logger.info("onPublish===" + JSON.toJSONString(onPublish));
    }


    @RequestMapping(value = "/onPublishDone", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public void onPublishDone(HttpServletRequest request, OnPublishDone onPublishDone) {
//        logger.info("onPublishDone===" + JSON.toJSONString(onPublishDone));
    }

    @RequestMapping(value = "/onRecordDone", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public void onRecordDone(HttpServletRequest request) {
        Enumeration enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String key = (String) enu.nextElement();
            String value = request.getParameter(key);
//            logger.info("onRecordDone---key====" + key + "---value===" + value);
        }
        ;
    }

    @RequestMapping(value = "/onUpdate", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public void onUpdate(HttpServletRequest request, OnUpdate onUpdate) {
        Enumeration enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String key = (String) enu.nextElement();
            String value = request.getParameter(key);
//            logger.info("onUpdate---key====" + key + "---value===" + value);
        }
//        logger.info("onUpdate===" + JSON.toJSONString(onUpdate));
    }

    //    //ffmpeg -i "rtsp://admin:one752*cdzx@10.249.25.17/H.264/ch14/main/av_stream" -vcodec copy -f flv "rtmp://localhost:1901/hls/monitor1"
//    @RequestMapping(value = "/openVideo", method = {RequestMethod.POST, RequestMethod.GET})
//    @ResponseBody
//    public String openVideo() {
//        String source = "rtsp://184.72.239.149/vod/mp4:BigBuckBunny_175k.mov";
//        logger.info("访问视频=" + source);
//        logger.info("视频在看人数：" + JSON.toJSONString(videoViewNumberMap));
//        logger.info(JSON.toJSONString(monitorProcessMap));
//        logger.info("视频转码对应关系" + JSON.toJSONString(transforRelationMap));
//        if (transforRelationMap.containsKey(source)) {
//            return JsonMessageUtils.getSuccessJson(transforRelationMap.get(source));
//        } else {
////            String monitor = "monitor" + monitorNumber.addAndGet(1);
//            String monitor = "monitor1";
//            String target = "rtmp://localhost:1901/hls/monitor1";
//            String cmd = "D:/ffmpeg/ffmpeg.exe -i rtsp://184.72.239.149/vod/mp4:BigBuckBunny_175k.mov -vcodec copy -f flv rtmp://localhost:1901/hls/monitor1";
//            cmd = ffmpegAddress + " -i " + source + " -vcodec copy -f flv " + target;
//            try {
//                Process process = Runtime.getRuntime().exec(cmd);
//                System.out.println(JSON.toJSONString(process));
//                //接收流不然进程会被挂起
//                ThreadUtil.read(process);
//                monitorProcessMap.put(monitor, process);
//                transforRelationMap.put(source, target);
//            } catch (Exception e) {
//                logger.error(e.getMessage(), e);
//            }
//            return JsonMessageUtils.getSuccessJson(target);
//        }
//    }


    public Map<String, List<MonitorInfo>> getMonitorInfoMap() {
        String infos = HttpUtils.sendHttpGet(getMonitorInfo);
        List<MonitorInfo> monitorInfos = JSONArray.parseArray(infos, MonitorInfo.class);
        Map<String, List<MonitorInfo>> monitorInfoMap = monitorInfos.stream().collect(Collectors.groupingBy(MonitorInfo::getLineAreaProcess));
        return monitorInfoMap;
    }

    public static synchronized void addJob(Job job) {
        currentJobs.add(job);
    }

    public static synchronized void addJobhistory(Job job) {
        List<Job> removeJobs = currentJobs.stream().filter(j -> j.getId() == job.getId()).collect(Collectors.toList());
        if (!removeJobs.isEmpty()) {
            currentJobs.remove(removeJobs.get(0));
        }
        //最多存放100条数据
        if (jobhistory.size() == 100) {
            for (int i = 0; i < 10; i++) {
                jobhistory.remove(0);
            }
        }
        jobhistory.add(job);
    }

    public static synchronized void removeTransforRelationMap(String lineAreaProcess) {
        transforRelationMap.remove(lineAreaProcess);
    }

    public static Map<String, List<String>> getVideoViewNumberMap() {
        return videoViewNumberMap;
    }


    public static <T> List<T> deepCopy(List<T> src) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(src);
        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        @SuppressWarnings("unchecked")
        List<T> dest = (List<T>) in.readObject();
        return dest;
    }

}
