package com.yeapao.andorid.model;

import java.util.List;

/**
 * Created by fujindong on 2017/7/26.
 */

public class ReservationListModel {

    /**
     * errcode : 0
     * errmsg : ok
     * data : [{"curriculumType":"团体课","shopsName":"也跑总店","date":"2017-07-25","time":"17:48-17:48","status":"1","reservationDetailsId":21}]
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
         * shopsName : 也跑总店
         * date : 2017-07-25
         * time : 17:48-17:48
         * status : 1
         * reservationDetailsId : 21
         */

        private String curriculumType;
        private String shopsName;
        private String date;
        private String time;
        private String status;
        private int reservationDetailsId;

        public String getCurriculumType() {
            return curriculumType;
        }

        public void setCurriculumType(String curriculumType) {
            this.curriculumType = curriculumType;
        }

        public String getShopsName() {
            return shopsName;
        }

        public void setShopsName(String shopsName) {
            this.shopsName = shopsName;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getReservationDetailsId() {
            return reservationDetailsId;
        }

        public void setReservationDetailsId(int reservationDetailsId) {
            this.reservationDetailsId = reservationDetailsId;
        }
    }
}
