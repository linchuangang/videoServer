package com.inrevo;

import com.alibaba.fastjson.JSON;
import com.inrevo.model.CyclePlay;
import com.inrevo.model.StreamGobbler;
import com.inrevo.model.StreamGobblerForError;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class processTest2 {
    public static void main(String[] args) {
        String address = "rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov";
        run(address);
    }

    public static void run(String address) {
        try {
            String cmd = "D:/ffmpeg/ffmpeg.exe -i   " + address + " -vcodec copy -f flv rtmp://localhost:1901/hls/monitor1";
            Process process = Runtime.getRuntime().exec(cmd);
            System.out.println(JSON.toJSONString(process));
            StreamGobblerForError errorGobbler = new StreamGobblerForError(CyclePlay.getProcessId(process),process.getErrorStream());
            StreamGobbler outputGobbler = new StreamGobbler(process.getInputStream());
            errorGobbler.start();
            outputGobbler.start();
            process.waitFor(60000, TimeUnit.MILLISECONDS);
            process.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void tests() {
        try {
            String cmd = "D:/ffmpeg/ffmpeg.exe -i rtsp://184.72.239.149/vod/mp4:BigBuckBunny_175k.mov -vcodec copy -f flv rtmp://localhost:1901/hls/monitor1";
            Process process = Runtime.getRuntime().exec(cmd);
            System.out.println(JSON.toJSONString(process));
            Thread.sleep(10000);
            System.out.println(11111);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1() throws IOException {
        try {
            Process process = Runtime.getRuntime().exec("javac");
            System.out.println(JSON.toJSONString(process));
            process.destroy();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void testOpen() {
        try {
            String cmd = "D:/ffmpeg/ffplay.exe -i D:/ffmpeg/2.mp4";
            Process process = Runtime.getRuntime().exec(cmd);
            System.out.println(JSON.toJSONString(process));
            System.out.println(3);
            Thread.sleep(10000);
//            process.waitFor();
            System.out.println(2);
            process.destroy();
            System.out.println(1);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void testOpen2() {
        try {
            String cmd = "D:/ffmpeg/ffplay.exe -i D:/ffmpeg/1.mp4";
            Process process = Runtime.getRuntime().exec(cmd);
            System.out.println(JSON.toJSONString(process));
            System.out.println(3);
            process.waitFor();
            System.out.println(2);
            process.destroy();
            System.out.println(1);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void testTransfor() throws IOException {
        try {
            //ffmpeg -i "rtsp://admin:one752*cdzx@10.249.25.17/H.264/ch14/main/av_stream" -vcodec copy -f flv "rtmp://localhost:1901/hls/monitor1"
            String cmd = "D:/ffmpeg/ffmpeg.exe -i \"rtsp://184.72.239.149/vod/mp4:BigBuckBunny_175k.mov\" -vcodec copy -f flv \"rtmp://localhost:1901/hls/monitor1\"";
            Process process = Runtime.getRuntime().exec(cmd);
            System.out.println(JSON.toJSONString(process));
            String line = null;
            BufferedReader stdout = new BufferedReader(new InputStreamReader(process
                    .getInputStream()));
            while ((line = stdout.readLine()) != null) {
                System.out.println("---" + line);
            }
            System.out.println(3);
            Thread.sleep(10000);
            System.out.println(2);
            process.destroy();
            System.out.println(1);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    @Test
    public void testInfo() {
        Runtime rt = Runtime.getRuntime();
        System.out.println("处理器数量：" + rt.availableProcessors());
        System.out.println("空闲内存数：" + rt.freeMemory());
        System.out.println("总内存数：" + rt.totalMemory());
        System.out.println("可用最大内存数：" + rt.maxMemory());
    }
}



















