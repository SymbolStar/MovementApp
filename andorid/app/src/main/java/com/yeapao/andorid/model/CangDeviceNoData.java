package com.yeapao.andorid.model;

import java.io.Serializable;

/**
 * Created by fujindong on 2017/9/29.
 */

public class CangDeviceNoData implements Serializable {
    private String deviceNo;
    private String id;
    private String startTime;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }
}
