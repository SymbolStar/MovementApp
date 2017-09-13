package com.yeapao.andorid.model;

/**
 * Created by fujindong on 2017/9/12.
 */

public class DepositOrdersModel {

    /**
     * errcode : 0
     * errmsg : ok
     * data : {"depositOrdersId":1,"depositOrdersName":"押金","price":99,"depositOrdersCode":"yj201708290000100001"}
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
         * depositOrdersId : 1
         * depositOrdersName : 押金
         * price : 99
         * depositOrdersCode : yj201708290000100001
         */

        private int depositOrdersId;
        private String depositOrdersName;
        private int price;
        private String depositOrdersCode;

        public int getDepositOrdersId() {
            return depositOrdersId;
        }

        public void setDepositOrdersId(int depositOrdersId) {
            this.depositOrdersId = depositOrdersId;
        }

        public String getDepositOrdersName() {
            return depositOrdersName;
        }

        public void setDepositOrdersName(String depositOrdersName) {
            this.depositOrdersName = depositOrdersName;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getDepositOrdersCode() {
            return depositOrdersCode;
        }

        public void setDepositOrdersCode(String depositOrdersCode) {
            this.depositOrdersCode = depositOrdersCode;
        }
    }
}
