package com.yeapao.andorid.model;

import java.util.List;

/**
 * Created by fujindong on 2017/9/28.
 */

public class SingleCommunityModel {

    /**
     * errcode : 0
     * errmsg : ok
     * data : {"gender":"男","userName":"美男子","images":[{"imageUrl":"http://47.92.113.97:8080/yepao/image/28/a4/28a458cf69af41e681ce76e02324cec2.png"}],"type":"community","content":"啪啪啪啪啪啪啪","grade":"男","fabulous":"0","commentNumber":0,"shareNumber":null,"master":"0","createTime":1506578730781,"headUrl":"http://47.92.113.97:8080/yepao/image/head05.png","communityId":"97","thumbsUp":4}
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
         * gender : 男
         * userName : 美男子
         * images : [{"imageUrl":"http://47.92.113.97:8080/yepao/image/28/a4/28a458cf69af41e681ce76e02324cec2.png"}]
         * type : community
         * content : 啪啪啪啪啪啪啪
         * grade : 男
         * fabulous : 0
         * commentNumber : 0
         * shareNumber : null
         * master : 0
         * createTime : 1506578730781
         * headUrl : http://47.92.113.97:8080/yepao/image/head05.png
         * communityId : 97
         * thumbsUp : 4
         */

        private String gender;
        private String userName;
        private String type;
        private String content;
        private String grade;
        private String fabulous;
        private int commentNumber;
        private Object shareNumber;
        private String master;
        private long createTime;
        private String headUrl;
        private String communityId;
        private int thumbsUp;
        private List<ImagesBean> images;

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public String getFabulous() {
            return fabulous;
        }

        public void setFabulous(String fabulous) {
            this.fabulous = fabulous;
        }

        public int getCommentNumber() {
            return commentNumber;
        }

        public void setCommentNumber(int commentNumber) {
            this.commentNumber = commentNumber;
        }

        public Object getShareNumber() {
            return shareNumber;
        }

        public void setShareNumber(Object shareNumber) {
            this.shareNumber = shareNumber;
        }

        public String getMaster() {
            return master;
        }

        public void setMaster(String master) {
            this.master = master;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getHeadUrl() {
            return headUrl;
        }

        public void setHeadUrl(String headUrl) {
            this.headUrl = headUrl;
        }

        public String getCommunityId() {
            return communityId;
        }

        public void setCommunityId(String communityId) {
            this.communityId = communityId;
        }

        public int getThumbsUp() {
            return thumbsUp;
        }

        public void setThumbsUp(int thumbsUp) {
            this.thumbsUp = thumbsUp;
        }

        public List<ImagesBean> getImages() {
            return images;
        }

        public void setImages(List<ImagesBean> images) {
            this.images = images;
        }

        public static class ImagesBean {
            /**
             * imageUrl : http://47.92.113.97:8080/yepao/image/28/a4/28a458cf69af41e681ce76e02324cec2.png
             */

            private String imageUrl;

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }
        }
    }
}
