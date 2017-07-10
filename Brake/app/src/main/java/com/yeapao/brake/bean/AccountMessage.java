package com.yeapao.brake.bean;

/**
 * Created by fujindong on 2017/7/7.
 */

public class AccountMessage {
    private String rs;
    private String flag;
    private String checkinfee;
    private UserInfo user;

    public String getRs() {
        return rs;
    }

    public void setRs(String rs) {
        this.rs = rs;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCheckinfee() {
        return checkinfee;
    }

    public void setCheckinfee(String checkinfee) {
        this.checkinfee = checkinfee;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }
}
