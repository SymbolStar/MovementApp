package com.yeapao.andorid.model;

/**
 * Created by fujindong on 2017/8/21.
 */

public class MessageListModel {

    /**
     * errcode : 0
     * errmsg : ok
     * data : {"punchTheClocks":0,"videos":0,"appointments":0}
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
         * punchTheClocks : 0
         * videos : 0
         * appointments : 0
         */

        private int punchTheClocks;
        private int videos;
        private int appointments;
        private int communityComments;

        public int getCommunityComments() {
            return communityComments;
        }

        public void setCommunityComments(int communityComments) {
            this.communityComments = communityComments;
        }

        public int getPunchTheClocks() {
            return punchTheClocks;
        }

        public void setPunchTheClocks(int punchTheClocks) {
            this.punchTheClocks = punchTheClocks;
        }

        public int getVideos() {
            return videos;
        }

        public void setVideos(int videos) {
            this.videos = videos;
        }

        public int getAppointments() {
            return appointments;
        }

        public void setAppointments(int appointments) {
            this.appointments = appointments;
        }
    }
}
