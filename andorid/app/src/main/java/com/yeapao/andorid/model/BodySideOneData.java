package com.yeapao.andorid.model;

import java.io.File;

/**
 * Created by fujindong on 2017/8/7.
 * 体测数据one 上传数据
 */

public class BodySideOneData {
    private String quietHeartRate;
    private String bloodPressure;
    private String heights;
    private String weight;
    private String inBody;
    private String scheduled;
    private String customerId;
    private String bodySideOne;
    private String blowPressure;
    private String highPressure;
    private File imageFile;


    public File getImageFile() {
        return imageFile;
    }

    public void setImageFile(File imageFile) {
        this.imageFile = imageFile;
    }

    public String getBlowPressure() {
        return blowPressure;
    }

    public void setBlowPressure(String blowPressure) {
        this.blowPressure = blowPressure;
    }

    public String getHighPressure() {
        return highPressure;
    }

    public void setHighPressure(String highPressure) {
        this.highPressure = highPressure;
    }

    public String getQuietHeartRate() {
        return quietHeartRate;
    }

    public void setQuietHeartRate(String quietHeartRate) {
        this.quietHeartRate = quietHeartRate;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public String getHeights() {
        return heights;
    }

    public void setHeights(String heights) {
        this.heights = heights;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getInBody() {
        return inBody;
    }

    public void setInBody(String inBody) {
        this.inBody = inBody;
    }

    public String getScheduled() {
        return scheduled;
    }

    public void setScheduled(String scheduled) {
        this.scheduled = scheduled;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getBodySideOne() {
        return bodySideOne;
    }

    public void setBodySideOne(String bodySideOne) {
        this.bodySideOne = bodySideOne;
    }
}
