package com.inrevo.model;

import com.inrevo.controller.VideoController;
import org.apache.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamGobbler extends Thread {
    private static final Logger logger = LoggerFactory.getLogger(StreamGobbler.class);
    InputStream is;

    public StreamGobbler(InputStream is) {
        this.is = is;

    }

    public void run() {
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            isr = new InputStreamReader(is);
            if (isr != null) {
                br = new BufferedReader(isr);
                if (br != null && !br.equals("")) {
                    String line = null;
                    while ((line = br.readLine()) != null) {
//                        logger.warn(line);
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                br.close();
                isr.close();
            } catch (Exception e) {

            }
        }
    }
}
