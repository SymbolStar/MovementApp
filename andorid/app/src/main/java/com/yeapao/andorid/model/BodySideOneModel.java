package com.yeapao.andorid.model;

/**
 * Created by fujindong on 2017/8/3.
 */

public class BodySideOneModel {


    /**
     * errcode : 0
     * errmsg : ok
     * data : {"bodySideOne":"1"}
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
         * bodySideOne : 1
         */

        private String bodySideOne;

        public String getBodySideOne() {
            return bodySideOne;
        }

        public void setBodySideOne(String bodySideOne) {
            this.bodySideOne = bodySideOne;
        }
    }
}
