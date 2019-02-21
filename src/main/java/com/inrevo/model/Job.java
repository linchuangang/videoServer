package com.inrevo.model;

import javafx.scene.effect.SepiaTone;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class Job implements Serializable{
    Integer id;
    String sourceUrl;
    String targetUrl;
    Date startTime;
    Date endTime;
    List<String> ips;
    String videoName;

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public void setIps(List<String> ips) {
        this.ips = ips;
    }

    public List<String> getIps() {
        return ips;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
