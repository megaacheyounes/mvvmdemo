package com.megaache.mvvmdemo.network.request;


public class BaseRequest {

    private String deviceType;

    public BaseRequest() {
        deviceType = "harmony-watch";
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }
}
