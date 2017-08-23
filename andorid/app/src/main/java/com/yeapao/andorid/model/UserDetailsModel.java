package com.yeapao.andorid.model;

/**
 * Created by fujindong on 2017/8/21.
 */

public class UserDetailsModel {

    /**
     * errcode : 0
     * errmsg : ok
     * data : {"id":1,"name":"季风了","phone":"15722822435","master":"0","grade":1,"head":"/image/3d/a7/3da726f9e9e548c28464982c2ab3f8e4.png","post":"2","status":0,"age":29,"gender":"男"}
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
         * name : 季风了
         * phone : 15722822435
         * master : 0
         * grade : 1
         * head : /image/3d/a7/3da726f9e9e548c28464982c2ab3f8e4.png
         * post : 2
         * status : 0
         * age : 29
         * gender : 男
         */

        private int id;
        private String name;
        private String phone;
        private String master;
        private int grade;
        private String head;
        private String post;
        private int status;
        private int age;
        private String gender;

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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }
    }
}
