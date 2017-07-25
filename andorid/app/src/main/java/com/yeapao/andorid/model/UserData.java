package com.yeapao.andorid.model;

import java.io.Serializable;

/**
 * Created by fujindong on 2017/7/25.
 */

public class UserData implements Serializable{

    /**
     * id : 1
     * name : 马敏
     * phone : 15722822435
     * master : 1
     * grade : 11
     * head : /image/12.jpg
     */

    private int id;
    private String name;
    private String phone;
    private String master;
    private int grade;
    private String head;
    private String password;
    private boolean isLogin = false;

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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
}
