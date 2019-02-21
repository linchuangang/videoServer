package com.inrevo.model;

import com.alibaba.fastjson.JSON;
import com.inrevo.controller.VideoController;
import com.inrevo.util.ReadUtil;
import com.sun.jna.Platform;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinNT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class CyclePlay extends Thread {
    private static final Logger logger = LoggerFactory.getLogger(CyclePlay.class);
    private static AtomicInteger monitorNumber = new AtomicInteger(0);
    private static Map<String, Process> processMap = new ConcurrentHashMap<>();

    String ffmpegAddress = ReadUtil.get("ffmpegAddress");
    int runTime = Integer.parseInt(ReadUtil.get("runTime"));

    List<String> list;
    String lineAreaProcess;
    String targetUrl;

    public CyclePlay(List<String> list, String lineAreaProcess, String targetUrl) {
        this.list = list;
        this.lineAreaProcess = lineAreaProcess;
        this.targetUrl = targetUrl;
    }

    public void run() {
        for (String s : list) {
            //任务记录
            Job job = new Job();
            job.setId(addNumber());
            job.setStartTime(new Date());
            job.setSourceUrl(s);
            job.setTargetUrl(targetUrl);
            job.setVideoName(lineAreaProcess);
            VideoController.addJob(job);
            String cmd = ffmpegAddress + " -i " + s + " -vcodec copy -f flv " + targetUrl;
            Process process = null;
            try {
                logger.info(lineAreaProcess + "---start---" + s);
                process = Runtime.getRuntime().exec(cmd);
                //接受流不然服务会被挂起
                StreamGobblerForError errorGobbler = new StreamGobblerForError(getProcessId(process), process.getErrorStream());
                StreamGobbler outputGobbler = new StreamGobbler(process.getInputStream());
                errorGobbler.start();
                outputGobbler.start();
//                CheckUp checkUp = new CheckUp(process, errorGobbler);
//                checkUp.start();
                processMap.put(lineAreaProcess, process);
                process.waitFor(runTime, TimeUnit.MILLISECONDS);
                process.destroyForcibly();
                job.setEndTime(new Date());
                VideoController.addJobhistory(job);
                //判断是否还有打开的窗口
                if (!VideoController.getVideoViewNumberMap().containsKey(lineAreaProcess)) {
                    logger.warn(lineAreaProcess + "----------窗口都关闭，播放结束啦----------");
                    break;
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
        Boolean result = true;
        if (!VideoController.getVideoViewNumberMap().containsKey(lineAreaProcess)) {
            result = false;
            //移除对应关系
            VideoController.removeTransforRelationMap(lineAreaProcess);
        }
        System.out.println(lineAreaProcess + "---------一次循环结束---------" + JSON.toJSONString(VideoController.getVideoViewNumberMap()));
        if (result) {
            run();
        }
    }

    public Integer addNumber() {
        if (monitorNumber.equals(10000)) {
            monitorNumber = new AtomicInteger(0);
        }
        return monitorNumber.addAndGet(1);
    }

    public void destroyProcess(String lineAreaProcess) {
        System.out.println(lineAreaProcess + "---over");
        Process process = processMap.get(lineAreaProcess);
        if (process != null) {
            process.destroy();
        }
    }


    public static int getProcessId(Process process) {
        Field field = null;
        int pId = 0;
        try {
            if (Platform.isWindows()) {
                field = process.getClass().getDeclaredField("handle");
                field.setAccessible(true);
                long handl = field.getLong(process);
                Kernel32 kernel = Kernel32.INSTANCE;
                WinNT.HANDLE handle = new WinNT.HANDLE();
                handle.setPointer(Pointer.createConstant(handl));
                pId = kernel.GetProcessId(handle);
            } else if (Platform.isLinux() || Platform.isAIX()) {
                Class<?> clazz = Class.forName("java.lang.UNIXProcess");
                field = clazz.getDeclaredField("pid");
                field.setAccessible(true);
                pId = (Integer) field.get(process);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return pId;
    }

}
