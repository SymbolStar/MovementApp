package com.yeapao.andorid.model;

/**
 * Created by fujindong on 2017/9/28.
 */

public class CalorieMessageModel {

    /**
     * errcode : 0
     * errmsg : ok
     * data : {"gender":"1","food":"2","objectiveId":2,"number":0.6}
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
         * gender : 1
         * food : 2
         * objectiveId : 2
         * number : 0.6
         */

        private String gender;
        private int food;
        private int objectiveId;
        private double number;

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public int getFood() {
            return food;
        }

        public void setFood(int food) {
            this.food = food;
        }

        public int getObjectiveId() {
            return objectiveId;
        }

        public void setObjectiveId(int objectiveId) {
            this.objectiveId = objectiveId;
        }

        public double getNumber() {
            return number;
        }

        public void setNumber(double number) {
            this.number = number;
        }
    }
}
