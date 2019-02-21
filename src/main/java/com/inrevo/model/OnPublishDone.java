package com.inrevo.model;

public class OnPublishDone extends Operation{
    private String clientid;
    private String name;

    public String getClientid() {
        return clientid;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
