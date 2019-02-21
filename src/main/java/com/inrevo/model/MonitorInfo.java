package com.inrevo.model;

import java.util.List;

public class MonitorInfo {
    //線別區域描述,此為現場區域劃分(鏡頭覆蓋)最小單位
    String lineAreaDescription;
    //所屬樓棟
    String lineAreaBuild;
    //所屬線別/模組
    String lineAreaLine;
    //所屬製程
    String lineAreaProcess;
    //線別區域id，一般情況無用
    String lineAreaId;
    //所屬線別/模組下的區域劃分,也就是一個鏡頭的覆蓋範圍
    String lineAreaName;
    //所属楼层
    String lineAreaF;
    //区域覆盖下监控设备信息集合
    List<Info> listInfo;

    public String getLineAreaDescription() {
        return lineAreaDescription;
    }

    public void setLineAreaDescription(String lineAreaDescription) {
        this.lineAreaDescription = lineAreaDescription;
    }

    public String getLineAreaBuild() {
        return lineAreaBuild;
    }

    public void setLineAreaBuild(String lineAreaBuild) {
        this.lineAreaBuild = lineAreaBuild;
    }

    public String getLineAreaLine() {
        return lineAreaLine;
    }

    public void setLineAreaLine(String lineAreaLine) {
        this.lineAreaLine = lineAreaLine;
    }

    public String getLineAreaProcess() {
        return lineAreaProcess;
    }

    public void setLineAreaProcess(String lineAreaProcess) {
        this.lineAreaProcess = lineAreaProcess;
    }

    public String getLineAreaId() {
        return lineAreaId;
    }

    public void setLineAreaId(String lineAreaId) {
        this.lineAreaId = lineAreaId;
    }

    public String getLineAreaName() {
        return lineAreaName;
    }

    public void setLineAreaName(String lineAreaName) {
        this.lineAreaName = lineAreaName;
    }

    public String getLineAreaF() {
        return lineAreaF;
    }

    public void setLineAreaF(String lineAreaF) {
        this.lineAreaF = lineAreaF;
    }

    public List<Info> getListInfo() {
        return listInfo;
    }

    public void setListInfo(List<Info> listInfo) {
        this.listInfo = listInfo;
    }
}
