package com.yeapao.andorid.model;

import java.util.List;

/**
 * Created by fujindong on 2017/7/26.
 */

public class MyselfLessonModel {

    /**
     * errcode : 0
     * errmsg : ok
     * data : [{"curriculumType":"团体课","totalPrice":"190","time":"-1","surplusNum":9}]
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
         * curriculumType : 团体课
         * totalPrice : 190
         * time : -1
         * surplusNum : 9
         */

        private String curriculumType;
        private String totalPrice;
        private String time;
        private String surplusNum;

        public String getCurriculumType() {
            return curriculumType;
        }

        public void setCurriculumType(String curriculumType) {
            this.curriculumType = curriculumType;
        }

        public String getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(String totalPrice) {
            this.totalPrice = totalPrice;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getSurplusNum() {
            return surplusNum;
        }

        public void setSurplusNum(String surplusNum) {
            this.surplusNum = surplusNum;
        }
    }
}
