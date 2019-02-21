package com.inrevo;

import com.inrevo.model.CyclePlay;
import com.inrevo.util.DateUtils;
import com.inrevo.util.ThreadUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class processTest4 {
    public static void main(String[] args) throws Exception {
        String cmdFmt = "D:/ffmpeg/ffmpeg.exe -i rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov -vcodec copy -s 300*200 -f flv rtmp://localhost:1901/hls/monitor1";
        for (int i = 0; i < 3; i++) {
            String cmd = String.format(cmdFmt, i);
            System.out.println(" ----------------------------------------------------- " + cmd);
            Process proc = Runtime.getRuntime().exec(cmd);

            new Thread(new StreamWrapper(proc.getInputStream(), "STDOUT")).start();
            new Thread(new StreamWrapper(proc.getErrorStream(), "ERROR")).start();

            // 获得返回值
            boolean ret = proc.waitFor(30, TimeUnit.SECONDS);
//            System.out.println(proc.isAlive() + "wait..." + ret);
//            if (!ret && proc.isAlive()) {
            proc.destroyForcibly();

            // 如果返回正确
//                System.out.println(" terminal = " + proc.exitValue());
//            } else {
//                System.out.println(" exit = " + proc.exitValue());
//            }
        }

    }

    public static class StreamWrapper implements Runnable {
        private InputStream is = null;

        private String streamFlag = null;

        private List<String> lines = null;

        public StreamWrapper(InputStream is, String streamFlag) {
            this.is = is;
            this.lines = new ArrayList<String>();
            this.streamFlag = streamFlag;
        }

        public void run() {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new InputStreamReader(is));
                String line = null;
                while ((line = br.readLine()) != null) {
                    lines.add(DateUtils.formatToDateTime(new Date()) + ":::::" + line);
                }
            } catch (Exception ioe) {
                ioe.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("[" + streamFlag + "] size = " + lines.size());
                for (String s : lines) {
                    System.out.println(s);
                }
            }
        }

        public List<String> getLines() {
            return lines;
        }
    }

}



















