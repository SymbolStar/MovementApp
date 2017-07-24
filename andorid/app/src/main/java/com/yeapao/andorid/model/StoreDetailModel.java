package com.yeapao.andorid.model;

/**
 * Created by fujindong on 2017/7/19.
 */

public class StoreDetailModel {

    /**
     * errcode : 0
     * errmsg : ok
     * data : {"shopId":1,"shopName":"也跑总店","address":"高新区运河路47号","businessHoursStart":"09:30","businessHoursEnd":"21:00","phone":"15722822435","summary":"公司总部","facilities":"单车","backgroundImage1":"/image/12.jpg","backgroundImage2":"/image/12.jpg","backgroundImage3":"/image/12.jpg","backgroundImage4":"/image/12.jpg","backgroundImage5":"/image/12.jpg","staffName":"马敏","star":"4","beGoodAt":"吹牛","qualifications":"锤子","head":"/image/12.jpg","prompt":"走过路过,不要错过"}
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
         * shopId : 1
         * shopName : 也跑总店
         * address : 高新区运河路47号
         * businessHoursStart : 09:30
         * businessHoursEnd : 21:00
         * phone : 15722822435
         * summary : 公司总部
         * facilities : 单车
         * backgroundImage1 : /image/12.jpg
         * backgroundImage2 : /image/12.jpg
         * backgroundImage3 : /image/12.jpg
         * backgroundImage4 : /image/12.jpg
         * backgroundImage5 : /image/12.jpg
         * staffName : 马敏
         * star : 4
         * beGoodAt : 吹牛
         * qualifications : 锤子
         * head : /image/12.jpg
         * prompt : 走过路过,不要错过
         */

        private int shopId;
        private String shopName;
        private String address;
        private String businessHoursStart;
        private String businessHoursEnd;
        private String phone;
        private String summary;
        private String facilities;
        private String backgroundImage1;
        private String backgroundImage2;
        private String backgroundImage3;
        private String backgroundImage4;
        private String backgroundImage5;
        private String staffName;
        private String star;
        private String beGoodAt;
        private String qualifications;
        private String head;
        private String prompt;

        public int getShopId() {
            return shopId;
        }

        public void setShopId(int shopId) {
            this.shopId = shopId;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getBusinessHoursStart() {
            return businessHoursStart;
        }

        public void setBusinessHoursStart(String businessHoursStart) {
            this.businessHoursStart = businessHoursStart;
        }

        public String getBusinessHoursEnd() {
            return businessHoursEnd;
        }

        public void setBusinessHoursEnd(String businessHoursEnd) {
            this.businessHoursEnd = businessHoursEnd;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getFacilities() {
            return facilities;
        }

        public void setFacilities(String facilities) {
            this.facilities = facilities;
        }

        public String getBackgroundImage1() {
            return backgroundImage1;
        }

        public void setBackgroundImage1(String backgroundImage1) {
            this.backgroundImage1 = backgroundImage1;
        }

        public String getBackgroundImage2() {
            return backgroundImage2;
        }

        public void setBackgroundImage2(String backgroundImage2) {
            this.backgroundImage2 = backgroundImage2;
        }

        public String getBackgroundImage3() {
            return backgroundImage3;
        }

        public void setBackgroundImage3(String backgroundImage3) {
            this.backgroundImage3 = backgroundImage3;
        }

        public String getBackgroundImage4() {
            return backgroundImage4;
        }

        public void setBackgroundImage4(String backgroundImage4) {
            this.backgroundImage4 = backgroundImage4;
        }

        public String getBackgroundImage5() {
            return backgroundImage5;
        }

        public void setBackgroundImage5(String backgroundImage5) {
            this.backgroundImage5 = backgroundImage5;
        }

        public String getStaffName() {
            return staffName;
        }

        public void setStaffName(String staffName) {
            this.staffName = staffName;
        }

        public String getStar() {
            return star;
        }

        public void setStar(String star) {
            this.star = star;
        }

        public String getBeGoodAt() {
            return beGoodAt;
        }

        public void setBeGoodAt(String beGoodAt) {
            this.beGoodAt = beGoodAt;
        }

        public String getQualifications() {
            return qualifications;
        }

        public void setQualifications(String qualifications) {
            this.qualifications = qualifications;
        }

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }

        public String getPrompt() {
            return prompt;
        }

        public void setPrompt(String prompt) {
            this.prompt = prompt;
        }
    }
}
