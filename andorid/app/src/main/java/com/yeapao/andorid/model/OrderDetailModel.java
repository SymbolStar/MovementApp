package com.yeapao.andorid.model;

/**
 * Created by fujindong on 2017/8/22.
 */

public class OrderDetailModel {

    /**
     * errcode : 0
     * errmsg : ok
     * data : {"orderId":7,"endTime":"2017-09-22","endDate":"2017-08-22 06:15:11","curriculumName":"减脂营","startDate":"2017-08-22 05:15","orderCode":"201708220000100007","price":"1","isContinue":"0","status":"待支付","types":"0"}
     */

    private int errcode;
    private String errmsg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * orderId : 7
         * endTime : 2017-09-22
         * endDate : 2017-08-22 06:15:11
         * curriculumName : 减脂营
         * startDate : 2017-08-22 05:15
         * orderCode : 201708220000100007
         * price : 1
         * isContinue : 0
         * status : 待支付
         * types : 0
         */

        private int orderId;
        private String endTime;
        private String endDate;
        private String curriculumName;
        private String startDate;
        private String orderCode;
        private String price;
        private String isContinue;
        private String status;
        private String types;

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getCurriculumName() {
            return curriculumName;
        }

        public void setCurriculumName(String curriculumName) {
            this.curriculumName = curriculumName;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getOrderCode() {
            return orderCode;
        }

        public void setOrderCode(String orderCode) {
            this.orderCode = orderCode;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getIsContinue() {
            return isContinue;
        }

        public void setIsContinue(String isContinue) {
            this.isContinue = isContinue;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTypes() {
            return types;
        }

        public void setTypes(String types) {
            this.types = types;
        }
    }
}
