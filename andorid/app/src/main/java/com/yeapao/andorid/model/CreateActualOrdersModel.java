package com.yeapao.andorid.model;

/**
 * Created by fujindong on 2017/9/15.
 */

public class CreateActualOrdersModel {

    /**
     * errcode : 0
     * errmsg : ok
     * data : {"actualOrdersId":"1","startDate":"2017-08-29 17:32:19"}
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
         * actualOrdersId : 1
         * startDate : 2017-08-29 17:32:19
         */

        private String actualOrdersId;
        private String startDate;

        public String getActualOrdersId() {
            return actualOrdersId;
        }

        public void setActualOrdersId(String actualOrdersId) {
            this.actualOrdersId = actualOrdersId;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }
    }
}
