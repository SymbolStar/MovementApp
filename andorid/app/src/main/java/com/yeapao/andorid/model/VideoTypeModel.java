package com.yeapao.andorid.model;

import java.util.List;

/**
 * Created by fujindong on 2017/8/21.
 */

public class VideoTypeModel {

    /**
     * errcode : 0
     * errmsg : ok
     * data : [{"type_name":"增肌","type_index":0},{"type_name":"减脂","type_index":1},{"type_name":"缓解肌肉疲劳","type_index":2},{"type_name":"释放压力","type_index":3}]
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
         * type_name : 增肌
         * type_index : 0
         */

        private String type_name;
        private int type_index;

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public int getType_index() {
            return type_index;
        }

        public void setType_index(int type_index) {
            this.type_index = type_index;
        }
    }
}
