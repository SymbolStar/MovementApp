package com.yeapao.andorid.model;

import java.util.List;

/**
 * Created by fujindong on 2017/7/19.
 */

public class LessonDetailData {
    /**
     * errcode : 0
     * errmsg : ok
     * data : {"actionEssentialsList":[{"image":["null/image/91/34/9134bf22f55c422a9fca370d2ec79507.jpg","/image/d4/52/d452449682b44dac94c66d4f89d8425d.jpg","/image/ae/d9/aed99fd48ff1480aad2f5abe59990b4e.jpg","/image/f2/70/f27089d11fe646f0b30b3b25d6570c78.jpg","/image/60/65/6065ccd6393a4fa2a25303242bb95c32.jpg"],"content":"第一步","title":"第一步"},{"image":["null/image/c5/50/c550a311279a4868a2c43cd85802abdc.jpg","/image/3b/51/3b5149808121456783a518e2f59d36fb.jpg","/image/1a/72/1a72b1e491d54bf9a43245b039da1dad.jpg","/image/cf/8d/cf8d896a58434889bcfde3272f25dfa3.jpg","/image/70/3a/703a981333ca4223be70f0af1fa25b45.jpg"],"content":"第二部","title":"第二步"},{"image":["null/image/d0/a4/d0a44c290eb249ee83d8e5985c836247.jpg","/image/3a/3b/3a3b3f345c904f56be71e34d2bf69071.jpg","/image/5b/cf/5bcf586a74c64b7d9a8eef1ef714b713.jpg","/image/ee/51/ee51e864022d40d799bbe23a32b3c0cf.jpg"],"content":"第三部","title":"第三步"},{"image":["null/image/7d/15/7d154126b1b94f68885d3fb98ad997ef.jpg","/image/37/27/37273f4e52ca4f8b80741cf78c30a149.jpg","/image/fe/93/fe93b85992ec41d5ba15933010270035.jpg","/image/7f/66/7f66511bb22742ed825af595dec19d53.jpg"],"content":"第四部","title":"第四步"},{"image":["null/image/ab/30/ab3040fd94fb4a889f430a4802abf63c.jpg","/image/93/66/93667d4eeccf452f95bf5d6f356adc91.jpg","/image/13/1d/131df671a3414c528cd62ff267acbf3e.jpg","/image/90/e2/90e24b48417c44c4918da1a74c55d0d3.jpg"],"content":"第五部","title":"第五步"}],"curriculum":{"curriculumId":18,"scheduleId":62,"shopName":"也跑总店","address":"苏州高新区运河路47号","classTimeStart":"14:00","classTimeEnd":"16:00","coach":"25","head":null,"star":null,"beGoodAt":null,"bespeak":0,"totalNumber":20,"linePrice":20,"onLinePrice":null,"map_curriculum_typesId":17,"curriculumName":"减脂课","mySchedule":"0","isBespeak":"0"}}
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
         * actionEssentialsList : [{"image":["null/image/91/34/9134bf22f55c422a9fca370d2ec79507.jpg","/image/d4/52/d452449682b44dac94c66d4f89d8425d.jpg","/image/ae/d9/aed99fd48ff1480aad2f5abe59990b4e.jpg","/image/f2/70/f27089d11fe646f0b30b3b25d6570c78.jpg","/image/60/65/6065ccd6393a4fa2a25303242bb95c32.jpg"],"content":"第一步","title":"第一步"},{"image":["null/image/c5/50/c550a311279a4868a2c43cd85802abdc.jpg","/image/3b/51/3b5149808121456783a518e2f59d36fb.jpg","/image/1a/72/1a72b1e491d54bf9a43245b039da1dad.jpg","/image/cf/8d/cf8d896a58434889bcfde3272f25dfa3.jpg","/image/70/3a/703a981333ca4223be70f0af1fa25b45.jpg"],"content":"第二部","title":"第二步"},{"image":["null/image/d0/a4/d0a44c290eb249ee83d8e5985c836247.jpg","/image/3a/3b/3a3b3f345c904f56be71e34d2bf69071.jpg","/image/5b/cf/5bcf586a74c64b7d9a8eef1ef714b713.jpg","/image/ee/51/ee51e864022d40d799bbe23a32b3c0cf.jpg"],"content":"第三部","title":"第三步"},{"image":["null/image/7d/15/7d154126b1b94f68885d3fb98ad997ef.jpg","/image/37/27/37273f4e52ca4f8b80741cf78c30a149.jpg","/image/fe/93/fe93b85992ec41d5ba15933010270035.jpg","/image/7f/66/7f66511bb22742ed825af595dec19d53.jpg"],"content":"第四部","title":"第四步"},{"image":["null/image/ab/30/ab3040fd94fb4a889f430a4802abf63c.jpg","/image/93/66/93667d4eeccf452f95bf5d6f356adc91.jpg","/image/13/1d/131df671a3414c528cd62ff267acbf3e.jpg","/image/90/e2/90e24b48417c44c4918da1a74c55d0d3.jpg"],"content":"第五部","title":"第五步"}]
         * curriculum : {"curriculumId":18,"scheduleId":62,"shopName":"也跑总店","address":"苏州高新区运河路47号","classTimeStart":"14:00","classTimeEnd":"16:00","coach":"25","head":null,"star":null,"beGoodAt":null,"bespeak":0,"totalNumber":20,"linePrice":20,"onLinePrice":null,"map_curriculum_typesId":17,"curriculumName":"减脂课","mySchedule":"0","isBespeak":"0"}
         */

        private CurriculumBean curriculum;
        private List<ActionEssentialsListBean> actionEssentialsList;

        public CurriculumBean getCurriculum() {
            return curriculum;
        }

        public void setCurriculum(CurriculumBean curriculum) {
            this.curriculum = curriculum;
        }

        public List<ActionEssentialsListBean> getActionEssentialsList() {
            return actionEssentialsList;
        }

        public void setActionEssentialsList(List<ActionEssentialsListBean> actionEssentialsList) {
            this.actionEssentialsList = actionEssentialsList;
        }

        public static class CurriculumBean {
            /**
             * curriculumId : 18
             * scheduleId : 62
             * shopName : 也跑总店
             * address : 苏州高新区运河路47号
             * classTimeStart : 14:00
             * classTimeEnd : 16:00
             * coach : 25
             * head : null
             * star : null
             * beGoodAt : null
             * bespeak : 0
             * totalNumber : 20
             * linePrice : 20
             * onLinePrice : null
             * map_curriculum_typesId : 17
             * curriculumName : 减脂课
             * mySchedule : 0
             * isBespeak : 0
             */

            private int curriculumId;
            private int scheduleId;
            private String shopName;
            private String address;
            private String classTimeStart;
            private String classTimeEnd;
            private String coach;
            private String head;
            private String star;
            private String beGoodAt;
            private int bespeak;
            private int totalNumber;
            private int linePrice;
            private String onLinePrice;
            private int map_curriculum_typesId;
            private String curriculumName;
            private String mySchedule;
            private String isBespeak;

            public int getCurriculumId() {
                return curriculumId;
            }

            public void setCurriculumId(int curriculumId) {
                this.curriculumId = curriculumId;
            }

            public int getScheduleId() {
                return scheduleId;
            }

            public void setScheduleId(int scheduleId) {
                this.scheduleId = scheduleId;
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

            public String getClassTimeStart() {
                return classTimeStart;
            }

            public void setClassTimeStart(String classTimeStart) {
                this.classTimeStart = classTimeStart;
            }

            public String getClassTimeEnd() {
                return classTimeEnd;
            }

            public void setClassTimeEnd(String classTimeEnd) {
                this.classTimeEnd = classTimeEnd;
            }

            public String getCoach() {
                return coach;
            }

            public void setCoach(String coach) {
                this.coach = coach;
            }

            public String getHead() {
                return head;
            }

            public void setHead(String head) {
                this.head = head;
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

            public int getBespeak() {
                return bespeak;
            }

            public void setBespeak(int bespeak) {
                this.bespeak = bespeak;
            }

            public int getTotalNumber() {
                return totalNumber;
            }

            public void setTotalNumber(int totalNumber) {
                this.totalNumber = totalNumber;
            }

            public int getLinePrice() {
                return linePrice;
            }

            public void setLinePrice(int linePrice) {
                this.linePrice = linePrice;
            }

            public String getOnLinePrice() {
                return onLinePrice;
            }

            public void setOnLinePrice(String onLinePrice) {
                this.onLinePrice = onLinePrice;
            }

            public int getMap_curriculum_typesId() {
                return map_curriculum_typesId;
            }

            public void setMap_curriculum_typesId(int map_curriculum_typesId) {
                this.map_curriculum_typesId = map_curriculum_typesId;
            }

            public String getCurriculumName() {
                return curriculumName;
            }

            public void setCurriculumName(String curriculumName) {
                this.curriculumName = curriculumName;
            }

            public String getMySchedule() {
                return mySchedule;
            }

            public void setMySchedule(String mySchedule) {
                this.mySchedule = mySchedule;
            }

            public String getIsBespeak() {
                return isBespeak;
            }

            public void setIsBespeak(String isBespeak) {
                this.isBespeak = isBespeak;
            }
        }

        public static class ActionEssentialsListBean {
            /**
             * image : ["null/image/91/34/9134bf22f55c422a9fca370d2ec79507.jpg","/image/d4/52/d452449682b44dac94c66d4f89d8425d.jpg","/image/ae/d9/aed99fd48ff1480aad2f5abe59990b4e.jpg","/image/f2/70/f27089d11fe646f0b30b3b25d6570c78.jpg","/image/60/65/6065ccd6393a4fa2a25303242bb95c32.jpg"]
             * content : 第一步
             * title : 第一步
             */

            private String content;
            private String title;
            private List<String> image;
            private int currentId;

            public int getCurrentId() {
                return currentId;
            }

            public void setCurrentId(int currentId) {
                this.currentId = currentId;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public List<String> getImage() {
                return image;
            }

            public void setImage(List<String> image) {
                this.image = image;
            }
        }
    }
}
