package com.yeapao.andorid.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fujindong on 2017/8/5.
 */

public class BodySideListModel implements Serializable{

    /**
     * errcode : 0
     * errmsg : ok
     * data : [{"step":"4","startTime":"09:40","scheduleId":3,"bodySideUserOut":[{"userName":"马敏","isMember":"1","head":"/image/head03.png","gender":"男","customerId":1},{"userName":"le","isMember":"1","head":"/image/head02.png","gender":"女","customerId":17}]},{"step":"3","startTime":"19:40","scheduleId":4,"bodySideUserOut":[{"userName":"马敏","isMember":"1","head":"/image/head03.png","gender":"男","customerId":1},{"userName":"le","isMember":"1","head":"/image/head02.png","gender":"女","customerId":17}]}]
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

    public static class DataBean implements Serializable{
        /**
         * step : 4
         * startTime : 09:40
         * scheduleId : 3
         * bodySideUserOut : [{"userName":"马敏","isMember":"1","head":"/image/head03.png","gender":"男","customerId":1},{"userName":"le","isMember":"1","head":"/image/head02.png","gender":"女","customerId":17}]
         */

        private String step;
        private String startTime;
        private int scheduleId;
        private List<BodySideUserOutBean> bodySideUserOut;

        private boolean status = false;

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }


        public String getStep() {
            return step;
        }

        public void setStep(String step) {
            this.step = step;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public int getScheduleId() {
            return scheduleId;
        }

        public void setScheduleId(int scheduleId) {
            this.scheduleId = scheduleId;
        }

        public List<BodySideUserOutBean> getBodySideUserOut() {
            return bodySideUserOut;
        }

        public void setBodySideUserOut(List<BodySideUserOutBean> bodySideUserOut) {
            this.bodySideUserOut = bodySideUserOut;
        }

        public static class BodySideUserOutBean implements Serializable{
            /**
             * userName : 马敏
             * isMember : 1
             * head : /image/head03.png
             * gender : 男
             * customerId : 1
             */

            private String userName;
            private String isMember;
            private String head;
            private String gender;
            private int customerId;

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getIsMember() {
                return isMember;
            }

            public void setIsMember(String isMember) {
                this.isMember = isMember;
            }

            public String getHead() {
                return head;
            }

            public void setHead(String head) {
                this.head = head;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public int getCustomerId() {
                return customerId;
            }

            public void setCustomerId(int customerId) {
                this.customerId = customerId;
            }
        }
    }
}
