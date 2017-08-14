package com.yeapao.andorid.model;

/**
 * Created by fujindong on 2017/8/14.
 */

public class LessonOrderModel {


    /**
     * errcode : 0
     * errmsg : ok
     * data : {"curriculumName":"单车","orderCode":"201708140000100055","price":200}
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
         * curriculumName : 单车
         * orderCode : 201708140000100055
         * price : 200
         */

        private String curriculumName;
        private String orderCode;
        private int price;

        public String getCurriculumName() {
            return curriculumName;
        }

        public void setCurriculumName(String curriculumName) {
            this.curriculumName = curriculumName;
        }

        public String getOrderCode() {
            return orderCode;
        }

        public void setOrderCode(String orderCode) {
            this.orderCode = orderCode;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }
}
