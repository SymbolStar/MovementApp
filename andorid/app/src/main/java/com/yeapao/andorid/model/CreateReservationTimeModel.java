package com.yeapao.andorid.model;

/**
 * Created by fujindong on 2017/9/12.
 */

public class CreateReservationTimeModel {


    /**
     * errcode : 0
     * errmsg : ok
     * data : {"reservaTimeOrderId":5,"price":6,"time":30,"reservaTimeOrderCode":"yy201708290000100005","warehouseName":"0210001"}
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
         * reservaTimeOrderId : 5
         * price : 6
         * time : 30
         * reservaTimeOrderCode : yy201708290000100005
         * warehouseName : 0210001
         */

        private int reservaTimeOrderId;
        private int price;
        private int time;
        private String reservaTimeOrderCode;
        private String warehouseName;

        public int getReservaTimeOrderId() {
            return reservaTimeOrderId;
        }

        public void setReservaTimeOrderId(int reservaTimeOrderId) {
            this.reservaTimeOrderId = reservaTimeOrderId;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public String getReservaTimeOrderCode() {
            return reservaTimeOrderCode;
        }

        public void setReservaTimeOrderCode(String reservaTimeOrderCode) {
            this.reservaTimeOrderCode = reservaTimeOrderCode;
        }

        public String getWarehouseName() {
            return warehouseName;
        }

        public void setWarehouseName(String warehouseName) {
            this.warehouseName = warehouseName;
        }
    }
}
