package com.inrevo.model;

import com.alibaba.fastjson.JSON;
import com.inrevo.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

public class StreamGobblerForError extends Thread {
    private static final Logger logger = LoggerFactory.getLogger(StreamGobblerForError.class);
    private int processId ;
    private InputStream is;
    private List<String> lines = null;

    public StreamGobblerForError(int processId , InputStream is) {
        this.processId = processId ;
        this.lines = new ArrayList<String>();
        this.is = is;
    }

    public void run() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ((line = br.readLine()) != null) {
                lines.add("[" + processId + "]-[" + DateUtils.formatToDateTime(new Date()) + "]:" + line);
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
            System.out.println("==============================================size=================================" + lines.size());
            for (String s : lines) {
                System.out.println(s);
            }
        }
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }
}
