package com.inrevo.model;
//* call=play。
//* addr - 客户端 IP 地址。
//* app - application 名。
//* flashVer - 客户端 flash 版本。
//* swfUrl - 客户端 swf url。
//* tcUrl - tcUrl。
//* pageUrl - 客户端页面 url。
//* name - 流名。
public class Operation {
    private String app;
    private String flashver;
    private String swfurl;
    private String tcurl;
    private String pageurl;
    private String addr;
    private String call;

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getFlashver() {
        return flashver;
    }

    public void setFlashver(String flashver) {
        this.flashver = flashver;
    }

    public String getSwfurl() {
        return swfurl;
    }

    public void setSwfurl(String swfurl) {
        this.swfurl = swfurl;
    }

    public String getTcurl() {
        return tcurl;
    }

    public void setTcurl(String tcurl) {
        this.tcurl = tcurl;
    }

    public String getPageurl() {
        return pageurl;
    }

    public void setPageurl(String pageurl) {
        this.pageurl = pageurl;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getCall() {
        return call;
    }

    public void setCall(String call) {
        this.call = call;
    }
}
