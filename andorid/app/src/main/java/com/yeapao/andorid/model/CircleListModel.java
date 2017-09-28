package com.yeapao.andorid.model;

import java.util.List;

/**
 * Created by fujindong on 2017/9/21.
 */

public class CircleListModel {

    /**
     * errcode : 0
     * errmsg : ok
     * data : {"bannerList":["http://47.92.113.97:8080/yepao/image/1.png","http://47.92.113.97:8080/yepao/image/2.png","http://47.92.113.97:8080/yepao/image/3.png","http://47.92.113.97:8080/yepao/image/4.png"],"communityList":[{"commentNumber":6,"images":[],"gender":"男","headUrl":"http://47.92.113.97:8080/yepao/image/c9/cf/c9cfaf1fe0e844eab4cceb2c4e824bbd.jpg","userName":"Scott","content":"减肥","master":"0","shareNumber":1,"createTime":1505368594561,"grade":1,"communityId":1,"fabulous":"0","thumbsUp":1},{"commentNumber":3,"images":[],"gender":"男","headUrl":"http://47.92.113.97:8080/yepao/image/9f/b5/9fb5f77d9cd84994a079adff2b185f70.png","userName":"季","content":"减肥","master":"0","shareNumber":1,"createTime":1505370294210,"grade":1,"communityId":2,"fabulous":"0","thumbsUp":1},{"commentNumber":4,"images":[],"gender":"男","headUrl":"http://47.92.113.97:8080/yepao/image/9f/b5/9fb5f77d9cd84994a079adff2b185f70.png","userName":"季","content":"减肥","master":"0","shareNumber":2,"createTime":1505371077957,"grade":1,"communityId":3,"fabulous":"0","thumbsUp":1},{"commentNumber":null,"images":[{"imageUrl":"http://47.92.113.97:8080/yepao/image/79/cc/79ccb54278814b25b8d136b3a194cc00.png"}],"gender":"男","headUrl":"http://47.92.113.97:8080/yepao/image/c9/cf/c9cfaf1fe0e844eab4cceb2c4e824bbd.jpg","userName":"Scott","content":"哈哈好傻好傻啊啊啊啊哈","master":"0","shareNumber":null,"createTime":1505807981531,"grade":1,"communityId":6,"fabulous":"0","thumbsUp":null},{"commentNumber":null,"images":[{"imageUrl":"http://47.92.113.97:8080/yepao/image/1f/f2/1ff27e35f80b482a827b4ec96c8fb9fe.png"},{"imageUrl":"http://47.92.113.97:8080/yepao/image/af/96/af96e4bc05db47448f6011dc325a5826.png"}],"gender":"男","headUrl":"http://47.92.113.97:8080/yepao/image/c9/cf/c9cfaf1fe0e844eab4cceb2c4e824bbd.jpg","userName":"Scott","content":"哈哈哈哈哈哈哈哈哈哈哈哈","master":"0","shareNumber":null,"createTime":1505810621765,"grade":1,"communityId":7,"fabulous":"0","thumbsUp":null},{"commentNumber":null,"images":[{"imageUrl":"http://47.92.113.97:8080/yepao/image/96/75/9675b7714f804e5e9c1490e79830faec.png"},{"imageUrl":"http://47.92.113.97:8080/yepao/image/8a/d7/8ad7ace443ff44f58059f2b9ea1a90c5.png"},{"imageUrl":"http://47.92.113.97:8080/yepao/image/c2/6e/c26e9a5df0c2458e9f57e6bb5f0e11af.png"},{"imageUrl":"http://47.92.113.97:8080/yepao/image/2a/3d/2a3d4e380b9e48db8c24e973b6283558.png"},{"imageUrl":"http://47.92.113.97:8080/yepao/image/80/11/801150c49a1c434db636ce5dc1bfab31.png"},{"imageUrl":"http://47.92.113.97:8080/yepao/image/6b/36/6b360f3507b7487ab540e7446af49d1c.png"}],"gender":"男","headUrl":"http://47.92.113.97:8080/yepao/image/e9/0d/e90d7f6b97dc4076b07375e5131cc551.png","userName":"neo","content":"哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈家啊就哈哈哈哈家哈哈姐姐啊哈哈家哈哈哈哈哈哈家","master":"0","shareNumber":null,"createTime":1505886797890,"grade":1,"communityId":8,"fabulous":"0","thumbsUp":null}]}
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
        private List<String> bannerList;
        private List<CommunityListBean> communityList;
        private int totalPage;

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public List<String> getBannerList() {
            return bannerList;
        }

        public void setBannerList(List<String> bannerList) {
            this.bannerList = bannerList;
        }

        public List<CommunityListBean> getCommunityList() {
            return communityList;
        }

        public void setCommunityList(List<CommunityListBean> communityList) {
            this.communityList = communityList;
        }

        public static class CommunityListBean {
            /**
             * commentNumber : 6
             * images : []
             * gender : 男
             * headUrl : http://47.92.113.97:8080/yepao/image/c9/cf/c9cfaf1fe0e844eab4cceb2c4e824bbd.jpg
             * userName : Scott
             * content : 减肥
             * master : 0
             * shareNumber : 1
             * createTime : 1505368594561
             * grade : 1
             * communityId : 1
             * fabulous : 0
             * thumbsUp : 1
             */

            private int commentNumber;
            private String gender;
            private String headUrl;
            private String userName;
            private String type;
            private String content;
            private String master;
            private int shareNumber;
            private long createTime;
            private int grade;
            private int communityId;
            private String fabulous;
            private int thumbsUp;
            private List<imagesUrl> images;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getCommentNumber() {
                return commentNumber;
            }

            public void setCommentNumber(int commentNumber) {
                this.commentNumber = commentNumber;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public String getHeadUrl() {
                return headUrl;
            }

            public void setHeadUrl(String headUrl) {
                this.headUrl = headUrl;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getMaster() {
                return master;
            }

            public void setMaster(String master) {
                this.master = master;
            }

            public int getShareNumber() {
                return shareNumber;
            }

            public void setShareNumber(int shareNumber) {
                this.shareNumber = shareNumber;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public int getGrade() {
                return grade;
            }

            public void setGrade(int grade) {
                this.grade = grade;
            }

            public int getCommunityId() {
                return communityId;
            }

            public void setCommunityId(int communityId) {
                this.communityId = communityId;
            }

            public String getFabulous() {
                return fabulous;
            }

            public void setFabulous(String fabulous) {
                this.fabulous = fabulous;
            }

            public int getThumbsUp() {
                return thumbsUp;
            }

            public void setThumbsUp(int thumbsUp) {
                this.thumbsUp = thumbsUp;
            }

            public List<imagesUrl> getImages() {
                return images;
            }

            public void setImages(List<imagesUrl> images) {
                this.images = images;
            }

            public class imagesUrl {
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
}
