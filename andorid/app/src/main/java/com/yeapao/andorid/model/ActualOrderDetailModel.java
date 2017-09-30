package com.yeapao.andorid.model;

/**
 * Created by fujindong on 2017/9/20.
 */

public class ActualOrderDetailModel {

    /**
     * errcode : 0
     * errmsg : ok
     * data : {"actualOrdersId":27,"actualOrdersCode":"sj201709160000100027","startDate":"2017-09-16 16:05:02.0","duration":60,"warehouseName":"05120001","price":12,"types":null}
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
         * actualOrdersId : 27
         * actualOrdersCode : sj201709160000100027
         * startDate : 2017-09-16 16:05:02.0
         * duration : 60
         * warehouseName : 05120001
         * price : 12
         * types : null
         */

        private int actualOrdersId;
        private String actualOrdersCode;
        private String startDate;
        private int duration;
        private String warehouseName;
        private String price;
        private String types;

        public String getReservaOrdersId() {
            return reservaOrdersId;
        }

        public void setReservaOrdersId(String reservaOrdersId) {
            this.reservaOrdersId = reservaOrdersId;
        }

        public String getReservaOrdersCode() {
            return reservaOrdersCode;
        }

        public void setReservaOrdersCode(String reservaOrdersCode) {
            this.reservaOrdersCode = reservaOrdersCode;
        }

        private String reservaOrdersId;
        private String reservaOrdersCode;

        public int getActualOrdersId() {
            return actualOrdersId;
        }

        public void setActualOrdersId(int actualOrdersId) {
            this.actualOrdersId = actualOrdersId;
        }

        public String getActualOrdersCode() {
            return actualOrdersCode;
        }

        public void setActualOrdersCode(String actualOrdersCode) {
            this.actualOrdersCode = actualOrdersCode;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public String getWarehouseName() {
            return warehouseName;
        }

        public void setWarehouseName(String warehouseName) {
            this.warehouseName = warehouseName;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getTypes() {
            return types;
        }

        public void setTypes(String types) {
            this.types = types;
        }
    }
}
