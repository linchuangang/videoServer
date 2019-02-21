//package com.inrevo.controller;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.inrevo.model.*;
//import com.inrevo.util.HttpUtils;
//import com.inrevo.util.JsonMessageUtils;
//import com.inrevo.util.ReadUtil;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Enumeration;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.atomic.AtomicInteger;
//import java.util.stream.Collectors;
//
//@Controller
//@RequestMapping("/test")
//public class TestController {
//    //存放转码后视频的monitor和对应的进程的关系
//    private static Map<String, Process> monitorProcessMap = new ConcurrentHashMap<String, Process>();
//    //存放视频在看的人数
//    private static Map<String, Integer> videoViewNumberMap = new ConcurrentHashMap<>();
//    //存放视频转码前后对应关系
//    private static Map<String, String> transforRelationMap = new ConcurrentHashMap<>();
//    //monitor自增长编号
//    private static AtomicInteger monitorNumber = new AtomicInteger(0);
//
//    String ffmpegAddress = ReadUtil.get("ffmpegAddress");
//    String getMonitorInfo = ReadUtil.get("getMonitorInfo");
//
//    private static final Logger logger = LoggerFactory.getLogger(TestController.class);
//
//    @RequestMapping(value = "/", method = {RequestMethod.POST, RequestMethod.GET})
//    @ResponseBody
//    public String findShiftList(String name) {
//        logger.info("----" + name);
//        return "helloWord";
//    }
//
//    //    //ffmpeg -i "rtsp://admin:one752*cdzx@10.249.25.17/H.264/ch14/main/av_stream" -vcodec copy -f flv "rtmp://localhost:1901/hls/monitor1"
////    @RequestMapping(value = "/openVideo", method = {RequestMethod.POST, RequestMethod.GET})
////    @ResponseBody
////    public String openVideo() {
////        String source = "rtsp://184.72.239.149/vod/mp4:BigBuckBunny_175k.mov";
////        logger.info("访问视频=" + source);
////        logger.info("视频在看人数：" + JSON.toJSONString(videoViewNumberMap));
////        logger.info(JSON.toJSONString(monitorProcessMap));
////        logger.info("视频转码对应关系" + JSON.toJSONString(transforRelationMap));
////        if (transforRelationMap.containsKey(source)) {
////            return JsonMessageUtils.getSuccessJson(transforRelationMap.get(source));
////        } else {
//////            String monitor = "monitor" + monitorNumber.addAndGet(1);
////            String monitor = "monitor1";
////            String target = "rtmp://localhost:1901/hls/monitor1";
////            String cmd = "D:/ffmpeg/ffmpeg.exe -i rtsp://184.72.239.149/vod/mp4:BigBuckBunny_175k.mov -vcodec copy -f flv rtmp://localhost:1901/hls/monitor1";
////            cmd = ffmpegAddress + " -i " + source + " -vcodec copy -f flv " + target;
////            try {
////                Process process = Runtime.getRuntime().exec(cmd);
////                System.out.println(JSON.toJSONString(process));
////                //接收流不然进程会被挂起
////                ThreadUtil.read(process);
////                monitorProcessMap.put(monitor, process);
////                transforRelationMap.put(source, target);
////            } catch (Exception e) {
////                logger.error(e.getMessage(), e);
////            }
////            return JsonMessageUtils.getSuccessJson(target);
////        }
////    }
//
//    //ffmpeg -i "rtsp://admin:one752*cdzx@10.249.25.17/H.264/ch14/main/av_stream" -vcodec copy -f flv "rtmp://localhost:1901/hls/monitor1"
//    //ffmpeg -i "rtsp://184.72.239.149/vod/mp4:BigBuckBunny_175k.mov" -vcodec copy -f flv "rtmp://localhost:1901/hls/monitor1"
//    @RequestMapping(value = "/onPlay", method = {RequestMethod.POST, RequestMethod.GET})
//    @ResponseBody
//    public void onPlay(HttpServletRequest request, OnPlay onPlay) {
//        logger.info("onPlay===" + JSON.toJSONString(onPlay));
//        logger.info(JSON.toJSONString(videoViewNumberMap));
//        logger.info(JSON.toJSONString(monitorProcessMap));
//        //记录视频的访问人数
//        if (videoViewNumberMap.containsKey(onPlay.getName())) {
//            Integer number = videoViewNumberMap.get(onPlay.getName());
//            number++;
//            videoViewNumberMap.put(onPlay.getName(), number);
//        } else {
//            videoViewNumberMap.put(onPlay.getName(), 1);
//        }
//    }
//
//
//    @RequestMapping(value = "/onDone", method = {RequestMethod.POST, RequestMethod.GET})
//    @ResponseBody
//    public void onDone(HttpServletRequest request, OnDone onDone) {
//        logger.info("onDone===" + JSON.toJSONString(onDone));
//        logger.info(JSON.toJSONString(videoViewNumberMap));
//        logger.info(JSON.toJSONString(monitorProcessMap));
//        //减少视频的访问人数
//        if (videoViewNumberMap.containsKey(onDone.getName())) {
//            Integer number = videoViewNumberMap.get(onDone.getName());
////            //最后一个人访问了把视频关掉
//            if (number == 1) {
//                //如果是刷新
//                try {
//                    Thread.sleep(5000);
//                } catch (Exception e) {
//                    logger.error(e.getMessage(), e);
//                }
//                number = videoViewNumberMap.get(onDone.getName());
//                if (1 == number) {
//                    Process process = monitorProcessMap.get(onDone.getName());
//                    if (process != null) {
//                        process.destroy();
//                        //移除视频
//                        videoViewNumberMap.remove(onDone.getName());
//                        monitorProcessMap.remove(onDone.getName());
//                        transforRelationMap.remove(onDone.getName());
//                        logger.info("over over over 关闭" + onDone.getName() + "转换流");
//                    } else {
//                        logger.info(onDone.getName() + "没有对应的装换进程");
//                    }
//                } else {
//                    number--;
//                    videoViewNumberMap.put(onDone.getName(), number);
//                }
//            } else {
//                number--;
//                videoViewNumberMap.put(onDone.getName(), number);
//            }
//            System.out.println("onDone over");
//        }
//    }
//
//
//    @RequestMapping(value = "/onConnect", method = {RequestMethod.POST, RequestMethod.GET})
//    @ResponseBody
//    public void onConnect(HttpServletRequest request, OnConnect onConnect) {
//        Enumeration enu = request.getParameterNames();
//        while (enu.hasMoreElements()) {
//            String key = (String) enu.nextElement();
//            String value = request.getParameter(key);
////            logger.info("onConnect---key====" + key + "---value===" + value);
//        }
//        logger.info("onConnect===" + JSON.toJSONString(onConnect));
//    }
//
//    @RequestMapping(value = "/onPlayDone", method = {RequestMethod.POST, RequestMethod.GET})
//    @ResponseBody
//    public void onPlayDone(HttpServletRequest request, OnPlayDone onPlayDone) {
//        Enumeration enu = request.getParameterNames();
//        while (enu.hasMoreElements()) {
//            String key = (String) enu.nextElement();
//            String value = request.getParameter(key);
////            logger.info("onPlayDone---key====" + key + "---value===" + value);
//        }
//        logger.info("onPlayDone===" + JSON.toJSONString(onPlayDone));
//    }
//
//
//    @RequestMapping(value = "/onPublish", method = {RequestMethod.POST, RequestMethod.GET})
//    @ResponseBody
//    public void onPublish(HttpServletRequest request, OnPublish onPublish) {
//        Enumeration enu = request.getParameterNames();
//        while (enu.hasMoreElements()) {
//            String key = (String) enu.nextElement();
//            String value = request.getParameter(key);
////            logger.info("onPublish---key====" + key + "---value===" + value);
//        }
//        logger.info("onPublish===" + JSON.toJSONString(onPublish));
//    }
//
//
//    @RequestMapping(value = "/onPublishDone", method = {RequestMethod.POST, RequestMethod.GET})
//    @ResponseBody
//    public void onPublishDone(HttpServletRequest request, OnPublishDone onPublishDone) {
//        Enumeration enu = request.getParameterNames();
//        while (enu.hasMoreElements()) {
//            String key = (String) enu.nextElement();
//            String value = request.getParameter(key);
////            logger.info("onPublishDone---key====" + key + "---value===" + value);
//        }
//        logger.info("onPublishDone===" + JSON.toJSONString(onPublishDone));
//    }
//
//    @RequestMapping(value = "/onRecordDone", method = {RequestMethod.POST, RequestMethod.GET})
//    @ResponseBody
//    public void onRecordDone(HttpServletRequest request) {
//        Enumeration enu = request.getParameterNames();
//        while (enu.hasMoreElements()) {
//            String key = (String) enu.nextElement();
//            String value = request.getParameter(key);
////            logger.info("onRecordDone---key====" + key + "---value===" + value);
//        }
//    }
//
//    @RequestMapping(value = "/onUpdate", method = {RequestMethod.POST, RequestMethod.GET})
//    @ResponseBody
//    public void onUpdate(HttpServletRequest request, OnUpdate onUpdate) {
//        Enumeration enu = request.getParameterNames();
//        while (enu.hasMoreElements()) {
//            String key = (String) enu.nextElement();
//            String value = request.getParameter(key);
////            logger.info("onUpdate---key====" + key + "---value===" + value);
//        }
//        logger.info("onUpdate===" + JSON.toJSONString(onUpdate));
//    }
//
//    public Map<String, List<MonitorInfo>> getMonitorInfoMap() {
//        String infos = HttpUtils.sendHttpGet(getMonitorInfo);
//        List<MonitorInfo> monitorInfos = JSONArray.parseArray(infos, MonitorInfo.class);
//        Map<String, List<MonitorInfo>> monitorInfoMap = monitorInfos.stream().collect(Collectors.groupingBy(MonitorInfo::getLineAreaProcess));
//        return monitorInfoMap;
//    }
//
//}
