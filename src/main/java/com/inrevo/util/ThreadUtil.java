package com.inrevo.util;

import com.inrevo.model.CyclePlay;
import com.inrevo.model.StreamGobbler;
import com.inrevo.model.StreamGobblerForError;

public class ThreadUtil {

    public static void read(Process process) {
        StreamGobblerForError errorGobbler = new StreamGobblerForError(CyclePlay.getProcessId(process),process.getErrorStream());
        StreamGobbler outputGobbler = new StreamGobbler(process.getInputStream());
        errorGobbler.start();
        outputGobbler.start();
    }
}
