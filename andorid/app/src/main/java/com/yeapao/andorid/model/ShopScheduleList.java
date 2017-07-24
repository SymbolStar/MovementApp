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
    private int bespeak;
    private int scheduleId;
    private String mySchedule;
    private String coach;
    private String isBespeak;

    public String getMySchedule() {
        return mySchedule;
    }

    public void setMySchedule(String mySchedule) {
        this.mySchedule = mySchedule;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public String getIsBespeak() {
        return isBespeak;
    }

    public void setIsBespeak(String isBespeak) {
        this.isBespeak = isBespeak;
    }

    public int getBespeak() {
        return bespeak;
    }

    public void setBespeak(int bespeak) {
        this.bespeak = bespeak;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

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
