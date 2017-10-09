package com.yeapao.andorid.model;

/**
 * Created by fujindong on 2017/9/15.
 */

public class ActialOrderDetailModel {

    /**
     * errcode : 0
     * errmsg : ok
     * data : {"actualOrderId":"1","price":"12","time":"60","warehouseName":"0210001","actualOrdersCode":"sj201708290000100001"}
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
         * actualOrderId : 1
         * price : 12
         * time : 60
         * warehouseName : 0210001
         * actualOrdersCode : sj201708290000100001
         */

        private String actualOrderId;
        private String price;
        private String time;
        private String warehouseName;
        private String actualOrdersCode;

        public String getActualOrderId() {
            return actualOrderId;
        }

        public void setActualOrderId(String actualOrderId) {
            this.actualOrderId = actualOrderId;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getWarehouseName() {
            return warehouseName;
        }

        public void setWarehouseName(String warehouseName) {
            this.warehouseName = warehouseName;
        }

        public String getActualOrdersCode() {
            return actualOrdersCode;
        }

        public void setActualOrdersCode(String actualOrdersCode) {
            this.actualOrdersCode = actualOrdersCode;
        }
    }
}
