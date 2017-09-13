package com.yeapao.andorid.model;

/**
 * Created by fujindong on 2017/9/12.
 */

public class MyAuthenticationModel {


    /**
     * errcode : 0
     * errmsg : ok
     * data : {"isAuthentication":"1","types":"1","depositOrdersCode":"yj201708300000200013","price":"99"}
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
         * isAuthentication : 1
         * types : 1
         * depositOrdersCode : yj201708300000200013
         * price : 99
         */

        private String isAuthentication;
        private String types;
        private String depositOrdersCode;
        private String price;

        public String getIsAuthentication() {
            return isAuthentication;
        }

        public void setIsAuthentication(String isAuthentication) {
            this.isAuthentication = isAuthentication;
        }

        public String getTypes() {
            return types;
        }

        public void setTypes(String types) {
            this.types = types;
        }

        public String getDepositOrdersCode() {
            return depositOrdersCode;
        }

        public void setDepositOrdersCode(String depositOrdersCode) {
            this.depositOrdersCode = depositOrdersCode;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
}
