package com.yeapao.andorid.model;

/**
 * Created by fujindong on 2017/7/26.
 */

public class ReservationLessonModel {

    /**
     * errcode : 0
     * errmsg : ok
     * data : {"bespeak":20}
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
         * bespeak : 20
         */

        private int bespeak;

        public int getBespeak() {
            return bespeak;
        }

        public void setBespeak(int bespeak) {
            this.bespeak = bespeak;
        }
    }
}
