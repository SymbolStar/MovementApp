package com.yeapao.andorid.model;

import java.util.List;

/**
 * Created by fujindong on 2017/8/9.
 */

public class HealthDataModel {


    /**
     * errcode : 0
     * errmsg : ok
     * data : {"testTecordListOuts":[{"startDate":"2017-08-07 14:35:52.0","bodySideId":11},{"startDate":"2017-08-05 14:35:52.0","bodySideId":13}],"BMIListOut":[{"startDate":"2017-08-08 19:41:37","bmi":"21.8"},{"startDate":"2017-08-07 14:35:52.0","bmi":"19.8"},{"startDate":"2017-08-06 19:42:37","bmi":"22.5"},{"startDate":"2017-08-05 14:35:52.0","bmi":"20.0"},{"startDate":"2017-08-04 19:40:37","bmi":"23.1"}],"weightListOuts":[{"startDate":"2017-08-08 19:41:37","weight":"66"},{"startDate":"2017-08-07 14:35:52.0","weight":"60"},{"startDate":"2017-08-06 19:42:37","weight":"68"},{"startDate":"2017-08-05 14:35:52.0","weight":"60.5"},{"startDate":"2017-08-04 19:40:37","weight":"70"}],"testScoresListOuts":[{"precursor":100,"legEndurance":85,"upperLimbStrength":40,"lowerExtremityStrength":0,"inBody":"99.5"},{"precursor":100,"legEndurance":90,"upperLimbStrength":100,"lowerExtremityStrength":0,"inBody":"70"}],"customer":{"customerId":1,"head":"/image/head03.png","name":"马敏","gender":"男","height":174,"weight":60,"age":29,"birthDate":"1989-08-01 17:43:11.0","startDate":"66"}}
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
         * testTecordListOuts : [{"startDate":"2017-08-07 14:35:52.0","bodySideId":11},{"startDate":"2017-08-05 14:35:52.0","bodySideId":13}]
         * BMIListOut : [{"startDate":"2017-08-08 19:41:37","bmi":"21.8"},{"startDate":"2017-08-07 14:35:52.0","bmi":"19.8"},{"startDate":"2017-08-06 19:42:37","bmi":"22.5"},{"startDate":"2017-08-05 14:35:52.0","bmi":"20.0"},{"startDate":"2017-08-04 19:40:37","bmi":"23.1"}]
         * weightListOuts : [{"startDate":"2017-08-08 19:41:37","weight":"66"},{"startDate":"2017-08-07 14:35:52.0","weight":"60"},{"startDate":"2017-08-06 19:42:37","weight":"68"},{"startDate":"2017-08-05 14:35:52.0","weight":"60.5"},{"startDate":"2017-08-04 19:40:37","weight":"70"}]
         * testScoresListOuts : [{"precursor":100,"legEndurance":85,"upperLimbStrength":40,"lowerExtremityStrength":0,"inBody":"99.5"},{"precursor":100,"legEndurance":90,"upperLimbStrength":100,"lowerExtremityStrength":0,"inBody":"70"}]
         * customer : {"customerId":1,"head":"/image/head03.png","name":"马敏","gender":"男","height":174,"weight":60,"age":29,"birthDate":"1989-08-01 17:43:11.0","startDate":"66"}
         */

        private CustomerBean customer;
        private List<TestTecordListOutsBean> testTecordListOuts;
        private List<BMIListOutBean> BMIListOut;
        private List<WeightListOutsBean> weightListOuts;
        private List<TestScoresListOutsBean> testScoresListOuts;

        public CustomerBean getCustomer() {
            return customer;
        }

        public void setCustomer(CustomerBean customer) {
            this.customer = customer;
        }

        public List<TestTecordListOutsBean> getTestTecordListOuts() {
            return testTecordListOuts;
        }

        public void setTestTecordListOuts(List<TestTecordListOutsBean> testTecordListOuts) {
            this.testTecordListOuts = testTecordListOuts;
        }

        public List<BMIListOutBean> getBMIListOut() {
            return BMIListOut;
        }

        public void setBMIListOut(List<BMIListOutBean> BMIListOut) {
            this.BMIListOut = BMIListOut;
        }

        public List<WeightListOutsBean> getWeightListOuts() {
            return weightListOuts;
        }

        public void setWeightListOuts(List<WeightListOutsBean> weightListOuts) {
            this.weightListOuts = weightListOuts;
        }

        public List<TestScoresListOutsBean> getTestScoresListOuts() {
            return testScoresListOuts;
        }

        public void setTestScoresListOuts(List<TestScoresListOutsBean> testScoresListOuts) {
            this.testScoresListOuts = testScoresListOuts;
        }

        public static class CustomerBean {
            /**
             * customerId : 1
             * head : /image/head03.png
             * name : 马敏
             * gender : 男
             * height : 174
             * weight : 60
             * age : 29
             * birthDate : 1989-08-01 17:43:11.0
             * startDate : 66
             */

            private int customerId;
            private String head;
            private String name;
            private String gender;
            private int height;
            private int weight;
            private int age;
            private String birthDate;
            private String startDate;

            public int getCustomerId() {
                return customerId;
            }

            public void setCustomerId(int customerId) {
                this.customerId = customerId;
            }

            public String getHead() {
                return head;
            }

            public void setHead(String head) {
                this.head = head;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public int getWeight() {
                return weight;
            }

            public void setWeight(int weight) {
                this.weight = weight;
            }

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }

            public String getBirthDate() {
                return birthDate;
            }

            public void setBirthDate(String birthDate) {
                this.birthDate = birthDate;
            }

            public String getStartDate() {
                return startDate;
            }

            public void setStartDate(String startDate) {
                this.startDate = startDate;
            }
        }

        public static class TestTecordListOutsBean {
            /**
             * startDate : 2017-08-07 14:35:52.0
             * bodySideId : 11
             */

            private String startDate;
            private int bodySideId;

            public String getStartDate() {
                return startDate;
            }

            public void setStartDate(String startDate) {
                this.startDate = startDate;
            }

            public int getBodySideId() {
                return bodySideId;
            }

            public void setBodySideId(int bodySideId) {
                this.bodySideId = bodySideId;
            }
        }

        public static class BMIListOutBean {
            /**
             * startDate : 2017-08-08 19:41:37
             * bmi : 21.8
             */

            private String startDate;
            private String bmi;

            public String getStartDate() {
                return startDate;
            }

            public void setStartDate(String startDate) {
                this.startDate = startDate;
            }

            public String getBmi() {
                return bmi;
            }

            public void setBmi(String bmi) {
                this.bmi = bmi;
            }
        }

        public static class WeightListOutsBean {
            /**
             * startDate : 2017-08-08 19:41:37
             * weight : 66
             */

            private String startDate;
            private String weight;

            public String getStartDate() {
                return startDate;
            }

            public void setStartDate(String startDate) {
                this.startDate = startDate;
            }

            public String getWeight() {
                return weight;
            }

            public void setWeight(String weight) {
                this.weight = weight;
            }
        }

        public static class TestScoresListOutsBean {
            /**
             * precursor : 100
             * legEndurance : 85
             * upperLimbStrength : 40
             * lowerExtremityStrength : 0
             * inBody : 99.5
             */

            private int precursor;
            private int legEndurance;
            private int upperLimbStrength;
            private int lowerExtremityStrength;
            private String inBody;

            public int getPrecursor() {
                return precursor;
            }

            public void setPrecursor(int precursor) {
                this.precursor = precursor;
            }

            public int getLegEndurance() {
                return legEndurance;
            }

            public void setLegEndurance(int legEndurance) {
                this.legEndurance = legEndurance;
            }

            public int getUpperLimbStrength() {
                return upperLimbStrength;
            }

            public void setUpperLimbStrength(int upperLimbStrength) {
                this.upperLimbStrength = upperLimbStrength;
            }

            public int getLowerExtremityStrength() {
                return lowerExtremityStrength;
            }

            public void setLowerExtremityStrength(int lowerExtremityStrength) {
                this.lowerExtremityStrength = lowerExtremityStrength;
            }

            public String getInBody() {
                return inBody;
            }

            public void setInBody(String inBody) {
                this.inBody = inBody;
            }
        }
    }
}
