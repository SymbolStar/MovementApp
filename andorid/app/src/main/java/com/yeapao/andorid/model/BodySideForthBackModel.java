package com.yeapao.andorid.model;

/**
 * Created by fujindong on 2017/8/8.
 */

public class BodySideForthBackModel {

    /**
     * errcode : 0
     * errmsg : ok
     * data : null
     */

    private int errcode;
    private String errmsg;
    private Object data;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
