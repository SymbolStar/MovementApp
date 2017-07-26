package com.yeapao.andorid.model;

/**
 * Created by fujindong on 2017/7/26.
 */

public class RegisterBackModel {

    /**
     * errcode : 0
     * errmsg : ok
     * data : {"id":"1","name":"马敏","phone":"15722822435","master":"1","grade":"11","head":"/image/12.jpg"}
     */

    private String errcode;
    private String errmsg;
    private UserData data;

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

    public UserData getData() {
        return data;
    }

    public void setData(UserData data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * name : 马敏
         * phone : 15722822435
         * master : 1
         * grade : 11
         * head : /image/12.jpg
         */

        private String id;
        private String name;
        private String phone;
        private String master;
        private String grade;
        private String head;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getMaster() {
            return master;
        }

        public void setMaster(String master) {
            this.master = master;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }
    }
}
