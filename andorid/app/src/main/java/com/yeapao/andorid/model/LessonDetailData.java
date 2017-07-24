package com.yeapao.andorid.model;

import java.util.List;

/**
 * Created by fujindong on 2017/7/19.
 */

public class LessonDetailData {

    /**
     * errcode : 0
     * errmsg : ok
     * data : {"actionEssentialsList":[{"image":["/image/12.jpg","/image/12.jpg","/image/12.jpg","/image/12.jpg"],"content":"蝶泳的泳姿动作要领包括：划水、呼吸、移臂、入水和海豚式打水。"},{"image":["/image/12.jpg","/image/12.jpg","/image/12.jpg","/image/12.jpg"],"content":"可以先从最简单的移动来感觉动作要领。"},{"image":["/image/12.jpg","/image/12.jpg","/image/12.jpg","/image/12.jpg"],"content":"本文以散级跳远技术过程，分为助跑与上板、第一跳、第二跳、以及第三跳等四个阶段，配合「单、双、双」摆臂方式，分别说明其动作要领。"},{"image":["/image/12.jpg","/image/12.jpg","/image/12.jpg","/image/12.jpg"],"content":"文章介绍了跳水转体的动作要领及转体训练的教学方法。"},{"image":["/image/12.jpg","/image/12.jpg","/image/12.jpg","/image/12.jpg"],"content":"简述了飞机斤斗飞行的基本运动关系和操纵动作要领。"}],"curriculum":{"curriculumId":1,"shopName":"也跑总店","address":"高新区运河路47号","classTimeStart":"9","classTimeEnd":"21","coach":"张","head":"/image/12.jpg","star":"4","beGoodAt":"吹牛"}}
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
         * actionEssentialsList : [{"image":["/image/12.jpg","/image/12.jpg","/image/12.jpg","/image/12.jpg"],"content":"蝶泳的泳姿动作要领包括：划水、呼吸、移臂、入水和海豚式打水。"},{"image":["/image/12.jpg","/image/12.jpg","/image/12.jpg","/image/12.jpg"],"content":"可以先从最简单的移动来感觉动作要领。"},{"image":["/image/12.jpg","/image/12.jpg","/image/12.jpg","/image/12.jpg"],"content":"本文以散级跳远技术过程，分为助跑与上板、第一跳、第二跳、以及第三跳等四个阶段，配合「单、双、双」摆臂方式，分别说明其动作要领。"},{"image":["/image/12.jpg","/image/12.jpg","/image/12.jpg","/image/12.jpg"],"content":"文章介绍了跳水转体的动作要领及转体训练的教学方法。"},{"image":["/image/12.jpg","/image/12.jpg","/image/12.jpg","/image/12.jpg"],"content":"简述了飞机斤斗飞行的基本运动关系和操纵动作要领。"}]
         * curriculum : {"curriculumId":1,"shopName":"也跑总店","address":"高新区运河路47号","classTimeStart":"9","classTimeEnd":"21","coach":"张","head":"/image/12.jpg","star":"4","beGoodAt":"吹牛"}
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
             * curriculumId : 1
             * shopName : 也跑总店
             * address : 高新区运河路47号
             * classTimeStart : 9
             * classTimeEnd : 21
             * coach : 张
             * head : /image/12.jpg
             * star : 4
             * beGoodAt : 吹牛
             */

            private int curriculumId;
            private String shopName;
            private String address;
            private String classTimeStart;
            private String classTimeEnd;
            private String coach;
            private String head;
            private String star;
            private String beGoodAt;
            private String bespeak;
            private String totalNumber;

            public String getBespeak() {
                return bespeak;
            }

            public void setBespeak(String bespeak) {
                this.bespeak = bespeak;
            }

            public String getTotalNumber() {
                return totalNumber;
            }

            public void setTotalNumber(String totalNumber) {
                this.totalNumber = totalNumber;
            }

            public int getCurriculumId() {
                return curriculumId;
            }

            public void setCurriculumId(int curriculumId) {
                this.curriculumId = curriculumId;
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
        }

        public static class ActionEssentialsListBean {
            /**
             * image : ["/image/12.jpg","/image/12.jpg","/image/12.jpg","/image/12.jpg"]
             * content : 蝶泳的泳姿动作要领包括：划水、呼吸、移臂、入水和海豚式打水。
             */

            private String content;
            private List<String> image;
            private int currentId = 0;

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

            public List<String> getImage() {
                return image;
            }

            public void setImage(List<String> image) {
                this.image = image;
            }
        }
    }
}
