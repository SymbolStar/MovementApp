package com.yeapao.andorid.model;

/**
 * Created by fujindong on 2017/8/14.
 */

public class CallPaymentModel {


    /**
     * errcode : 0
     * errmsg : ok
     * data : {"aliPayInfo":""}
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
         * aliPayInfo :
         */

        private String aliPayInfo;
        private String wxPayReq;
        private String price;
        private String orderCode;
        private String paymentType;
        private String prepayid;
        private String noncestr;
        private String timeStamp;

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getTimeStamp() {
            return timeStamp;
        }

        public void setTimeStamp(String timeStamp) {
            this.timeStamp = timeStamp;
        }

        public String getWxPayReq() {
            return wxPayReq;
        }

        public void setWxPayReq(String wxPayReq) {
            this.wxPayReq = wxPayReq;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getOrderCode() {
            return orderCode;
        }

        public void setOrderCode(String orderCode) {
            this.orderCode = orderCode;
        }

        public String getPaymentType() {
            return paymentType;
        }

        public void setPaymentType(String paymentType) {
            this.paymentType = paymentType;
        }

        public String getAliPayInfo() {
            return aliPayInfo;
        }

        public void setAliPayInfo(String aliPayInfo) {
            this.aliPayInfo = aliPayInfo;
        }
    }
}
