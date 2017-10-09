package com.yeapao.andorid.model;

import java.util.List;

/**
 * Created by fujindong on 2017/9/18.
 */

public class ActualOrderListModel {


    /**
     * errcode : 0
     * errmsg : ok
     * data : [{"price":12,"duration":60,"status":"1","warehouseName":"05120001","actualOrdersCode":"sj201709160000100027","actualOrdersId":27}]
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
         * price : 12
         * duration : 60
         * status : 1
         * warehouseName : 05120001
         * actualOrdersCode : sj201709160000100027
         * actualOrdersId : 27
         */

        private String price;
        private int duration;
        private String status;
        private String warehouseName;
        private String actualOrdersCode;
        private int actualOrdersId;

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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

        public int getActualOrdersId() {
            return actualOrdersId;
        }

        public void setActualOrdersId(int actualOrdersId) {
            this.actualOrdersId = actualOrdersId;
        }
    }
}
