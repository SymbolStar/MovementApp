package com.yeapao.andorid.model;

import java.util.List;

/**
 * Created by fujindong on 2017/8/22.
 */

public class MyOrderDataModel {

    /**
     * errcode : 0
     * errmsg : ok
     * data : [{"scheduleId":1,"orderId":7,"image":"/image/c3/08/c3083e1fc23040af8af708f88783e158.png","curriculumName":"减脂营","endTime":"2017-09-22","status":"待支付","isContinue":"1","endDate":"2017-08-22 06:15:11","orderCode":"201708220000100007","price":"1"}]
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
         * scheduleId : 1
         * orderId : 7
         * image : /image/c3/08/c3083e1fc23040af8af708f88783e158.png
         * curriculumName : 减脂营
         * endTime : 2017-09-22
         * status : 待支付
         * isContinue : 1
         * endDate : 2017-08-22 06:15:11
         * orderCode : 201708220000100007
         * price : 1
         */

        private int scheduleId;
        private int orderId;
        private String image;
        private String curriculumName;
        private String endTime;
        private String status;
        private String isContinue;
        private String endDate;
        private String orderCode;
        private String price;

        public int getScheduleId() {
            return scheduleId;
        }

        public void setScheduleId(int scheduleId) {
            this.scheduleId = scheduleId;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getCurriculumName() {
            return curriculumName;
        }

        public void setCurriculumName(String curriculumName) {
            this.curriculumName = curriculumName;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getIsContinue() {
            return isContinue;
        }

        public void setIsContinue(String isContinue) {
            this.isContinue = isContinue;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
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
    }
}
