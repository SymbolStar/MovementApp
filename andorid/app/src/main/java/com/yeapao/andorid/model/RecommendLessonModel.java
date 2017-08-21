package com.yeapao.andorid.model;

import java.util.List;

/**
 * Created by fujindong on 2017/8/19.
 */

public class RecommendLessonModel {


    /**
     * errcode : 0
     * errmsg : ok
     * data : [{"curriculumId":18,"curriculumName":"减脂课","totalNumber":20,"bookNumber":5,"startTime":"16:00","endTime":"17:00","address":"苏州高新区运河路47号","backgroundImage":"/image/c9/e2/c9e2d4dd9e2a4bb7a17ee60ddaac02c4.jpg","bespeak":0,"scheduleId":65,"mySchedule":"0","coach":"25","isBespeak":null}]
     */

    private int errcode;
    private String errmsg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * curriculumId : 18
         * curriculumName : 减脂课
         * totalNumber : 20
         * bookNumber : 5
         * startTime : 16:00
         * endTime : 17:00
         * address : 苏州高新区运河路47号
         * backgroundImage : /image/c9/e2/c9e2d4dd9e2a4bb7a17ee60ddaac02c4.jpg
         * bespeak : 0
         * scheduleId : 65
         * mySchedule : 0
         * coach : 25
         * isBespeak : null
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
        private String mySchedule;
        private String coach;
        private Object isBespeak;

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

        public Object getIsBespeak() {
            return isBespeak;
        }

        public void setIsBespeak(Object isBespeak) {
            this.isBespeak = isBespeak;
        }
    }
}
