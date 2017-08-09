package com.yeapao.andorid.model;

import java.util.List;

/**
 * Created by fujindong on 2017/8/8.
 */

public class BodySideTwoGetBackModel {

    /**
     * errcode : 0
     * errmsg : ok
     * data : [{"bodySideTwoId":5,"customerId":17,"upperRight":"1","upperLeft":"1","abdomen":"1","waist":"1","hips":"1","lowerRight":"1","lowerLeft":"1","bodySideId":24},{"bodySideTwoId":6,"customerId":21,"upperRight":"2","upperLeft":"12","abdomen":"2","waist":"2","hips":"2","lowerRight":"2","lowerLeft":"2","bodySideId":25}]
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
         * bodySideTwoId : 5
         * customerId : 17
         * upperRight : 1
         * upperLeft : 1
         * abdomen : 1
         * waist : 1
         * hips : 1
         * lowerRight : 1
         * lowerLeft : 1
         * bodySideId : 24
         */

        private int bodySideTwoId;
        private int customerId;
        private String upperRight;
        private String upperLeft;
        private String abdomen;
        private String waist;
        private String hips;
        private String lowerRight;
        private String lowerLeft;
        private int bodySideId;

        public int getBodySideTwoId() {
            return bodySideTwoId;
        }

        public void setBodySideTwoId(int bodySideTwoId) {
            this.bodySideTwoId = bodySideTwoId;
        }

        public int getCustomerId() {
            return customerId;
        }

        public void setCustomerId(int customerId) {
            this.customerId = customerId;
        }

        public String getUpperRight() {
            return upperRight;
        }

        public void setUpperRight(String upperRight) {
            this.upperRight = upperRight;
        }

        public String getUpperLeft() {
            return upperLeft;
        }

        public void setUpperLeft(String upperLeft) {
            this.upperLeft = upperLeft;
        }

        public String getAbdomen() {
            return abdomen;
        }

        public void setAbdomen(String abdomen) {
            this.abdomen = abdomen;
        }

        public String getWaist() {
            return waist;
        }

        public void setWaist(String waist) {
            this.waist = waist;
        }

        public String getHips() {
            return hips;
        }

        public void setHips(String hips) {
            this.hips = hips;
        }

        public String getLowerRight() {
            return lowerRight;
        }

        public void setLowerRight(String lowerRight) {
            this.lowerRight = lowerRight;
        }

        public String getLowerLeft() {
            return lowerLeft;
        }

        public void setLowerLeft(String lowerLeft) {
            this.lowerLeft = lowerLeft;
        }

        public int getBodySideId() {
            return bodySideId;
        }

        public void setBodySideId(int bodySideId) {
            this.bodySideId = bodySideId;
        }
    }
}
