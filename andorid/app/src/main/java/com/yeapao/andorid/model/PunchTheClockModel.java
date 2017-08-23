package com.yeapao.andorid.model;

import java.util.List;

/**
 * Created by fujindong on 2017/8/21.
 */

public class PunchTheClockModel {

    /**
     * errcode : 0
     * errmsg : ok
     * data : [{"userMessageId":3,"createTime":"2017-08-18 11-08-05","notificationContent":"减脂视频"},{"userMessageId":8,"createTime":"2017-08-18 11-08-05","notificationContent":"减脂视频"},{"userMessageId":11,"createTime":"2017-08-18 11-08-05","notificationContent":"减脂视频"},{"userMessageId":14,"createTime":"2017-08-18 11-08-05","notificationContent":"减脂视频"},{"userMessageId":17,"createTime":"2017-08-18 11-08-05","notificationContent":"减脂视频"}]
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
         * userMessageId : 3
         * createTime : 2017-08-18 11-08-05
         * notificationContent : 减脂视频
         */

        private int userMessageId;
        private String createTime;
        private String notificationContent;

        public int getUserMessageId() {
            return userMessageId;
        }

        public void setUserMessageId(int userMessageId) {
            this.userMessageId = userMessageId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getNotificationContent() {
            return notificationContent;
        }

        public void setNotificationContent(String notificationContent) {
            this.notificationContent = notificationContent;
        }
    }
}
