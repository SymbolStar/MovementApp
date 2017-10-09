package com.yeapao.andorid.model;

/**
 * Created by fujindong on 2017/8/3.
 */

public class RegisterModel {

    /**
     * errcode : 0
     * errmsg : ok
     * data : {"id":1,"name":"马敏","phone":"15722822435","master":"1","grade":11,"head":"/image/head03.png","post":"shopowner"}
     */

    private int errcode;
    private String errmsg;
    private UserData data;

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
         * head : /image/head03.png
         * post : shopowner
         */

        private int id;
        private String name;
        private String phone;
        private String master;
        private int grade;
        private String head;
        private String post;

        public int getId() {
            return id;
        }

        public void setId(int id) {
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

        public int getGrade() {
            return grade;
        }

        public void setGrade(int grade) {
            this.grade = grade;
        }

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }

        public String getPost() {
            return post;
        }

        public void setPost(String post) {
            this.post = post;
        }
    }
}
