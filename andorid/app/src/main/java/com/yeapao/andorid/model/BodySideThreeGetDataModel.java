package com.yeapao.andorid.model;

import java.util.List;

/**
 * Created by fujindong on 2017/8/8.
 */

public class BodySideThreeGetDataModel {


    /**
     * errcode : 0
     * errmsg : ok
     * data : [{"bodySideThreeId":5,"customerId":17,"upperLimbStrength":"1","lowerExtremityStrength":"1","precursor":"1","heartRateOne":"1","heartRateTwo":"1","heartRateThree":"1","bodySideId":24},{"bodySideThreeId":6,"customerId":21,"upperLimbStrength":"2","lowerExtremityStrength":"2","precursor":"22","heartRateOne":"2","heartRateTwo":"22","heartRateThree":"2","bodySideId":25}]
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
         * bodySideThreeId : 5
         * customerId : 17
         * upperLimbStrength : 1
         * lowerExtremityStrength : 1
         * precursor : 1
         * heartRateOne : 1
         * heartRateTwo : 1
         * heartRateThree : 1
         * bodySideId : 24
         */

        private int bodySideThreeId;
        private int customerId;
        private String upperLimbStrength;
        private String lowerExtremityStrength;
        private String precursor;
        private String heartRateOne;
        private String heartRateTwo;
        private String heartRateThree;
        private int bodySideId;

        public int getBodySideThreeId() {
            return bodySideThreeId;
        }

        public void setBodySideThreeId(int bodySideThreeId) {
            this.bodySideThreeId = bodySideThreeId;
        }

        public int getCustomerId() {
            return customerId;
        }

        public void setCustomerId(int customerId) {
            this.customerId = customerId;
        }

        public String getUpperLimbStrength() {
            return upperLimbStrength;
        }

        public void setUpperLimbStrength(String upperLimbStrength) {
            this.upperLimbStrength = upperLimbStrength;
        }

        public String getLowerExtremityStrength() {
            return lowerExtremityStrength;
        }

        public void setLowerExtremityStrength(String lowerExtremityStrength) {
            this.lowerExtremityStrength = lowerExtremityStrength;
        }

        public String getPrecursor() {
            return precursor;
        }

        public void setPrecursor(String precursor) {
            this.precursor = precursor;
        }

        public String getHeartRateOne() {
            return heartRateOne;
        }

        public void setHeartRateOne(String heartRateOne) {
            this.heartRateOne = heartRateOne;
        }

        public String getHeartRateTwo() {
            return heartRateTwo;
        }

        public void setHeartRateTwo(String heartRateTwo) {
            this.heartRateTwo = heartRateTwo;
        }

        public String getHeartRateThree() {
            return heartRateThree;
        }

        public void setHeartRateThree(String heartRateThree) {
            this.heartRateThree = heartRateThree;
        }

        public int getBodySideId() {
            return bodySideId;
        }

        public void setBodySideId(int bodySideId) {
            this.bodySideId = bodySideId;
        }
    }
}
