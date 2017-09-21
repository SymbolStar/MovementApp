package com.yeapao.andorid.model;

/**
 * Created by fujindong on 2017/9/21.
 */

public class CangDeleteActualOrdersModel {


    /**
     * error_code : 0
     * data : {"uid":"1","username":"12154545","name":"吴系挂","groupid":2,"reg_time":"1436864169","last_login_time":"0"}
     */

    private int error_code;
    private DataBean data;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * uid : 1
         * username : 12154545
         * name : 吴系挂
         * groupid : 2
         * reg_time : 1436864169
         * last_login_time : 0
         */

        private String uid;
        private String username;
        private String name;
        private int groupid;
        private String reg_time;
        private String last_login_time;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getGroupid() {
            return groupid;
        }

        public void setGroupid(int groupid) {
            this.groupid = groupid;
        }

        public String getReg_time() {
            return reg_time;
        }

        public void setReg_time(String reg_time) {
            this.reg_time = reg_time;
        }

        public String getLast_login_time() {
            return last_login_time;
        }

        public void setLast_login_time(String last_login_time) {
            this.last_login_time = last_login_time;
        }
    }
}
