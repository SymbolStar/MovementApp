package com.yeapao.andorid.model;

import java.util.List;

/**
 * Created by fujindong on 2017/8/7.
 */

public class BodySideOneGetModel {


    /**
     * errcode : 0
     * errmsg : ok
     * data : [{"bodySideOneId":20,"customerId":17,"quietHeartRate":"1","bloodPressure":"1-1","height":"1","weight":"1","inBody":"1","presentation":"/image/40/19/4019f04e0ec344ae8e84e8b0bebe9c93.jpg","bodySideId":20},{"bodySideOneId":21,"customerId":21,"quietHeartRate":"2","bloodPressure":"2-2","height":"2","weight":"2","inBody":"2","presentation":"/image/43/50/4350e1d6f58d4188931b38e46e3e9c54.jpg","bodySideId":21}]
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
         * bodySideOneId : 20
         * customerId : 17
         * quietHeartRate : 1
         * bloodPressure : 1-1
         * height : 1
         * weight : 1
         * inBody : 1
         * presentation : /image/40/19/4019f04e0ec344ae8e84e8b0bebe9c93.jpg
         * bodySideId : 20
         */

        private int bodySideOneId;
        private int customerId;
        private String quietHeartRate;
        private String bloodPressure;
        private String height;
        private String weight;
        private String inBody;
        private String presentation;
        private int bodySideId;

        public int getBodySideOneId() {
            return bodySideOneId;
        }

        public void setBodySideOneId(int bodySideOneId) {
            this.bodySideOneId = bodySideOneId;
        }

        public int getCustomerId() {
            return customerId;
        }

        public void setCustomerId(int customerId) {
            this.customerId = customerId;
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

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
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

        public String getPresentation() {
            return presentation;
        }

        public void setPresentation(String presentation) {
            this.presentation = presentation;
        }

        public int getBodySideId() {
            return bodySideId;
        }

        public void setBodySideId(int bodySideId) {
            this.bodySideId = bodySideId;
        }
    }
}
