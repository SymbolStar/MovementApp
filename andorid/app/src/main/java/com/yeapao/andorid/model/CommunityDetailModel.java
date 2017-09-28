package com.yeapao.andorid.model;

import java.util.List;

/**
 * Created by fujindong on 2017/9/25.
 */

public class CommunityDetailModel {


    /**
     * errcode : 0
     * errmsg : ok
     * data : {"commentNumber":7,"images":[{"imageUrl":"http://47.92.113.97:8080/yepao/image/79/cc/79ccb54278814b25b8d136b3a194cc00.png"}],"comments":[{"id":10,"comment":"哈哈哈哈哈哈哈哈啊哈哈哈哈哈哈哈哈哈哈哈哈哈哈","communityId":6,"customerId":5,"createTime":1505895138312,"status":"","name":"neo","head":"http://47.92.113.97:8080/yepao/image/e9/0d/e90d7f6b97dc4076b07375e5131cc551.png","grade":"1","master":"0","isDelete":"0","communityCommentsOuts":[{"id":21,"comment":"啦啦","communityId":6,"customerId":5,"createTime":1505984097031,"status":"","name":"neo","head":"http://47.92.113.97:8080/yepao/image/e9/0d/e90d7f6b97dc4076b07375e5131cc551.png","grade":"1","master":"0","passiveCustomerName":"neo","isDelete":"0"},{"id":22,"comment":"我要去皮","communityId":6,"customerId":5,"createTime":1505984102140,"status":"","name":"neo","head":"http://47.92.113.97:8080/yepao/image/e9/0d/e90d7f6b97dc4076b07375e5131cc551.png","grade":"1","master":"0","passiveCustomerName":"neo","isDelete":"0"},{"id":23,"comment":"啦啦啦啦啦啦啦啦啦啦啦啦哈哈艰难啊就哈哈哈家哈哈哈看看","communityId":6,"customerId":5,"createTime":1505985261031,"status":"","name":"neo","head":"http://47.92.113.97:8080/yepao/image/e9/0d/e90d7f6b97dc4076b07375e5131cc551.png","grade":"1","master":"0","passiveCustomerName":"neo","isDelete":"0"}]}],"gender":"男","isDelete":"1","headUrl":"http://47.92.113.97:8080/yepao/image/c9/cf/c9cfaf1fe0e844eab4cceb2c4e824bbd.jpg","userName":"Scott","content":"哈哈好傻好傻啊啊啊啊哈","master":"0","shareNumber":null,"createTime":1505807981531,"grade":1,"communityId":6,"thumbsUp":null}
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
         * commentNumber : 7
         * images : [{"imageUrl":"http://47.92.113.97:8080/yepao/image/79/cc/79ccb54278814b25b8d136b3a194cc00.png"}]
         * comments : [{"id":10,"comment":"哈哈哈哈哈哈哈哈啊哈哈哈哈哈哈哈哈哈哈哈哈哈哈","communityId":6,"customerId":5,"createTime":1505895138312,"status":"","name":"neo","head":"http://47.92.113.97:8080/yepao/image/e9/0d/e90d7f6b97dc4076b07375e5131cc551.png","grade":"1","master":"0","isDelete":"0","communityCommentsOuts":[{"id":21,"comment":"啦啦","communityId":6,"customerId":5,"createTime":1505984097031,"status":"","name":"neo","head":"http://47.92.113.97:8080/yepao/image/e9/0d/e90d7f6b97dc4076b07375e5131cc551.png","grade":"1","master":"0","passiveCustomerName":"neo","isDelete":"0"},{"id":22,"comment":"我要去皮","communityId":6,"customerId":5,"createTime":1505984102140,"status":"","name":"neo","head":"http://47.92.113.97:8080/yepao/image/e9/0d/e90d7f6b97dc4076b07375e5131cc551.png","grade":"1","master":"0","passiveCustomerName":"neo","isDelete":"0"},{"id":23,"comment":"啦啦啦啦啦啦啦啦啦啦啦啦哈哈艰难啊就哈哈哈家哈哈哈看看","communityId":6,"customerId":5,"createTime":1505985261031,"status":"","name":"neo","head":"http://47.92.113.97:8080/yepao/image/e9/0d/e90d7f6b97dc4076b07375e5131cc551.png","grade":"1","master":"0","passiveCustomerName":"neo","isDelete":"0"}]}]
         * gender : 男
         * isDelete : 1
         * headUrl : http://47.92.113.97:8080/yepao/image/c9/cf/c9cfaf1fe0e844eab4cceb2c4e824bbd.jpg
         * userName : Scott
         * content : 哈哈好傻好傻啊啊啊啊哈
         * master : 0
         * shareNumber : null
         * isFabulous
         * createTime : 1505807981531
         * grade : 1
         * communityId : 6
         * thumbsUp : null
         */

        private int commentNumber;
        private String gender;
        private String isDelete;
        private String headUrl;
        private String userName;
        private String content;
        private String master;
        private Object shareNumber;
        private String isFabulous;
        private long createTime;
        private int grade;
        private int communityId;
        private int thumbsUp;
        private List<CircleListModel.DataBean.CommunityListBean.imagesUrl> images;
        private List<CommentsBean> comments;
        public String getIsFabulous() {
            return isFabulous;
        }

        public void setIsFabulous(String isFabulous) {
            this.isFabulous = isFabulous;
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

        public String getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(String isDelete) {
            this.isDelete = isDelete;
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

        public Object getShareNumber() {
            return shareNumber;
        }

        public void setShareNumber(Object shareNumber) {
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

        public int getThumbsUp() {
            return thumbsUp;
        }

        public void setThumbsUp(int thumbsUp) {
            this.thumbsUp = thumbsUp;
        }

        public List<CircleListModel.DataBean.CommunityListBean.imagesUrl> getImages() {
            return images;
        }

        public void setImages(List<CircleListModel.DataBean.CommunityListBean.imagesUrl> images) {
            this.images = images;
        }

        public List<CommentsBean> getComments() {
            return comments;
        }

        public void setComments(List<CommentsBean> comments) {
            this.comments = comments;
        }

        public static class ImagesBean {
            /**
             * imageUrl : http://47.92.113.97:8080/yepao/image/79/cc/79ccb54278814b25b8d136b3a194cc00.png
             */

            private String imageUrl;

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }
        }

        public static class CommentsBean {
            /**
             * id : 10
             * comment : 哈哈哈哈哈哈哈哈啊哈哈哈哈哈哈哈哈哈哈哈哈哈哈
             * communityId : 6
             * customerId : 5
             * createTime : 1505895138312
             * status :
             * name : neo
             * head : http://47.92.113.97:8080/yepao/image/e9/0d/e90d7f6b97dc4076b07375e5131cc551.png
             * grade : 1
             * master : 0
             * isDelete : 0
             * communityCommentsOuts : [{"id":21,"comment":"啦啦","communityId":6,"customerId":5,"createTime":1505984097031,"status":"","name":"neo","head":"http://47.92.113.97:8080/yepao/image/e9/0d/e90d7f6b97dc4076b07375e5131cc551.png","grade":"1","master":"0","passiveCustomerName":"neo","isDelete":"0"},{"id":22,"comment":"我要去皮","communityId":6,"customerId":5,"createTime":1505984102140,"status":"","name":"neo","head":"http://47.92.113.97:8080/yepao/image/e9/0d/e90d7f6b97dc4076b07375e5131cc551.png","grade":"1","master":"0","passiveCustomerName":"neo","isDelete":"0"},{"id":23,"comment":"啦啦啦啦啦啦啦啦啦啦啦啦哈哈艰难啊就哈哈哈家哈哈哈看看","communityId":6,"customerId":5,"createTime":1505985261031,"status":"","name":"neo","head":"http://47.92.113.97:8080/yepao/image/e9/0d/e90d7f6b97dc4076b07375e5131cc551.png","grade":"1","master":"0","passiveCustomerName":"neo","isDelete":"0"}]
             */

            private int id;
            private String comment;
            private int communityId;
            private int customerId;
            private long createTime;
            private String status;
            private String name;
            private String head;
            private String grade;
            private String master;
            private String isDelete;
            private List<CommunityCommentsOutsBean> communityCommentsOuts;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public int getCommunityId() {
                return communityId;
            }

            public void setCommunityId(int communityId) {
                this.communityId = communityId;
            }

            public int getCustomerId() {
                return customerId;
            }

            public void setCustomerId(int customerId) {
                this.customerId = customerId;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getHead() {
                return head;
            }

            public void setHead(String head) {
                this.head = head;
            }

            public String getGrade() {
                return grade;
            }

            public void setGrade(String grade) {
                this.grade = grade;
            }

            public String getMaster() {
                return master;
            }

            public void setMaster(String master) {
                this.master = master;
            }

            public String getIsDelete() {
                return isDelete;
            }

            public void setIsDelete(String isDelete) {
                this.isDelete = isDelete;
            }

            public List<CommunityCommentsOutsBean> getCommunityCommentsOuts() {
                return communityCommentsOuts;
            }

            public void setCommunityCommentsOuts(List<CommunityCommentsOutsBean> communityCommentsOuts) {
                this.communityCommentsOuts = communityCommentsOuts;
            }

            public static class CommunityCommentsOutsBean {
                /**
                 * id : 21
                 * comment : 啦啦
                 * communityId : 6
                 * customerId : 5
                 * createTime : 1505984097031
                 * status :
                 * name : neo
                 * head : http://47.92.113.97:8080/yepao/image/e9/0d/e90d7f6b97dc4076b07375e5131cc551.png
                 * grade : 1
                 * master : 0
                 * passiveCustomerName : neo
                 * isDelete : 0
                 */

                private int id;
                private String comment;
                private int communityId;
                private int customerId;
                private long createTime;
                private String status;
                private String name;
                private String head;
                private String grade;
                private String master;
                private String passiveCustomerName;
                private String isDelete;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getComment() {
                    return comment;
                }

                public void setComment(String comment) {
                    this.comment = comment;
                }

                public int getCommunityId() {
                    return communityId;
                }

                public void setCommunityId(int communityId) {
                    this.communityId = communityId;
                }

                public int getCustomerId() {
                    return customerId;
                }

                public void setCustomerId(int customerId) {
                    this.customerId = customerId;
                }

                public long getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(long createTime) {
                    this.createTime = createTime;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getHead() {
                    return head;
                }

                public void setHead(String head) {
                    this.head = head;
                }

                public String getGrade() {
                    return grade;
                }

                public void setGrade(String grade) {
                    this.grade = grade;
                }

                public String getMaster() {
                    return master;
                }

                public void setMaster(String master) {
                    this.master = master;
                }

                public String getPassiveCustomerName() {
                    return passiveCustomerName;
                }

                public void setPassiveCustomerName(String passiveCustomerName) {
                    this.passiveCustomerName = passiveCustomerName;
                }

                public String getIsDelete() {
                    return isDelete;
                }

                public void setIsDelete(String isDelete) {
                    this.isDelete = isDelete;
                }
            }
        }
    }
}
