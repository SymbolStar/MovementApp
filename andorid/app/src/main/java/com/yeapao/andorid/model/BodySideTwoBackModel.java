package com.yeapao.andorid.model;

/**
 * Created by fujindong on 2017/8/8.
 */

public class BodySideTwoBackModel {


    /**
     * errcode : 0
     * errmsg : ok
     * data : {"bodySideTwo":"1"}
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
         * bodySideTwo : 1
         */

        private String bodySideTwo;

        public String getBodySideTwo() {
            return bodySideTwo;
        }

        public void setBodySideTwo(String bodySideTwo) {
            this.bodySideTwo = bodySideTwo;
        }
    }
}
