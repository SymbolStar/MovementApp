package com.yeapao.andorid.model;

import java.util.List;

/**
 * Created by fujindong on 2017/9/20.
 */

public class CangReservationOrderListModel {

    /**
     * errcode : 0
     * errmsg : ok
     * data : [{"reservaOrdersId":14,"price":1,"duration":0,"status":"2"}]
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
         * reservaOrdersId : 14
         * price : 1
         * duration : 0
         * status : 2
         */

        private int reservaOrdersId;
        private int price;
        private int duration;
        private String status;

        public int getReservaOrdersId() {
            return reservaOrdersId;
        }

        public void setReservaOrdersId(int reservaOrdersId) {
            this.reservaOrdersId = reservaOrdersId;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
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
    }
}
