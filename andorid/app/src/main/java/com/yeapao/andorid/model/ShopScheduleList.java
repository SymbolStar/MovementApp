package com.yeapao.andorid.model;

/**
 * Created by fujindong on 2017/7/18.
 *
 *  "curriculumId": 1,
 *"curriculumName": "单车",
 "totalNumber": 20,
 "bookNumber": 10,
 "startTime": "2017-07-18",
 "endTime": "2017-07-19",
 "address": "高新区运河路47号",
 "backgroundImage": "/image/12.jpg"
 *
 *
 *
 *
 */

public class ShopScheduleList {
    private int curriculumId;
    private String curriculumName;
    private long totalNumber;
    private long bookNumber;
    private String startTime;
    private String endTime;
    private String address;
    private String backgroundImage;

    public int getCurriculumId() {
        return curriculumId;
    }

    public void setCurriculumId(int curriculumId) {
        this.curriculumId = curriculumId;
    }

    public String getCurriculumName() {
        return curriculumName;
    }

    public void setCurriculumName(String curriculumName) {
        this.curriculumName = curriculumName;
    }

    public long getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(long totalNumber) {
        this.totalNumber = totalNumber;
    }

    public long getBookNumber() {
        return bookNumber;
    }

    public void setBookNumber(long bookNumber) {
        this.bookNumber = bookNumber;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }
}
