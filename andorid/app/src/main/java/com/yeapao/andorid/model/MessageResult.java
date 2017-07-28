package com.yeapao.andorid.model;

import java.util.Objects;

/**
 * Created by fujindong on 2017/7/18.
 */

public class MessageResult {
    private int errcode;
    private String errmsg;
    private HomeList data;

    public HomeList getData() {
        return data;
    }

    public void setData(HomeList data) {
        this.data = data;
    }

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


}
