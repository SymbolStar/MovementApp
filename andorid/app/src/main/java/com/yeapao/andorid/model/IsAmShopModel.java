package com.yeapao.andorid.model;

/**
 * Created by fujindong on 2017/8/5.
 */

public class IsAmShopModel {


    /**
     * errcode : 0
     * errmsg : ok
     * data : {"bespeakNum":"4","admissionNum":"4","totalSale":"200"}
     */

    private String errcode;
    private String errmsg;
    private DataBean data;

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
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
         * bespeakNum : 4
         * admissionNum : 4
         * totalSale : 200
         */

        private String bespeakNum="0";
        private String admissionNum="0";
        private String totalSale="0";

        public String getBespeakNum() {
            return bespeakNum;
        }

        public void setBespeakNum(String bespeakNum) {
            this.bespeakNum = bespeakNum;
        }

        public String getAdmissionNum() {
            return admissionNum;
        }

        public void setAdmissionNum(String admissionNum) {
            this.admissionNum = admissionNum;
        }

        public String getTotalSale() {
            return totalSale;
        }

        public void setTotalSale(String totalSale) {
            this.totalSale = totalSale;
        }
    }
}
