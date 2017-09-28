package com.yeapao.andorid.model;

import java.util.List;

/**
 * Created by fujindong on 2017/9/27.
 */

public class CircleMessageModel {

    /**
     * errcode : 0
     * errmsg : ok
     * data : [{"userMessageId":13,"messageContent":"哈哈哈哈哈哈哈哈哈哈","name":"neo","date":"1506498984656","communityCommentId":"60","community":"1"},{"userMessageId":12,"messageContent":"哈哈哈哈","name":"neo","date":"1506498427437","communityCommentId":"60","community":"1"},{"userMessageId":10,"messageContent":"123","name":"neo","date":"1506497248546","communityCommentId":"60","community":"1"},{"userMessageId":9,"messageContent":"一起fight","name":"neo","date":"1506496309672","communityCommentId":"60","community":"1"}]
     */

    private int errcode;
    private String errmsg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * userMessageId : 13
         * messageContent : 哈哈哈哈哈哈哈哈哈哈
         * name : neo
         * date : 1506498984656
         * communityCommentId : 60
         * community : 1
         */

        private int userMessageId;
        private String messageContent;
        private String name;
        private long date;
        private String communityCommentId;
        private String community;

        public int getUserMessageId() {
            return userMessageId;
        }

        public void setUserMessageId(int userMessageId) {
            this.userMessageId = userMessageId;
        }

        public String getMessageContent() {
            return messageContent;
        }

        public void setMessageContent(String messageContent) {
            this.messageContent = messageContent;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getDate() {
            return date;
        }

        public void setDate(long date) {
            this.date = date;
        }

        public String getCommunityCommentId() {
            return communityCommentId;
        }

        public void setCommunityCommentId(String communityCommentId) {
            this.communityCommentId = communityCommentId;
        }

        public String getCommunity() {
            return community;
        }

        public void setCommunity(String community) {
            this.community = community;
        }
    }
}
