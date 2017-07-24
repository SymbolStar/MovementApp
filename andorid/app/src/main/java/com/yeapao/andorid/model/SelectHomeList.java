package com.yeapao.andorid.model;

import java.util.List;

/**
 * Created by fujindong on 2017/7/20.
 */

public class SelectHomeList {

    /**
     * errcode : 0
     * errmsg : ok
     * data : [{"curriculumId":1,"curriculumName":"单车","totalNumber":20,"bookNumber":10,"startTime":"2017-07-19","endTime":"2017-07-21","address":"高新区运河路47号","backgroundImage":"/image/12.jpg","bespeak":5,"scheduleId":1}]
     */

    private int errcode;
    private String errmsg;
    private List<ShopScheduleList> data;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public List<ShopScheduleList> getData() {
        return data;
    }

    public void setData(List<ShopScheduleList> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * curriculumId : 1
         * curriculumName : 单车
         * totalNumber : 20
         * bookNumber : 10
         * startTime : 2017-07-19
         * endTime : 2017-07-21
         * address : 高新区运河路47号
         * backgroundImage : /image/12.jpg
         * bespeak : 5
         * scheduleId : 1
         */

        private int curriculumId;
        private String curriculumName;
        private int totalNumber;
        private int bookNumber;
        private String startTime;
        private String endTime;
        private String address;
        private String backgroundImage;
        private int bespeak;
        private int scheduleId;

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

        public int getTotalNumber() {
            return totalNumber;
        }

        public void setTotalNumber(int totalNumber) {
            this.totalNumber = totalNumber;
        }

        public int getBookNumber() {
            return bookNumber;
        }

        public void setBookNumber(int bookNumber) {
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
    }
}
