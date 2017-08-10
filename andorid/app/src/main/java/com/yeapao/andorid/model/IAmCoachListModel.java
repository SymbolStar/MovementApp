package com.yeapao.andorid.model;

import java.util.List;

/**
 * Created by fujindong on 2017/8/9.
 */

public class IAmCoachListModel {

    /**
     * errcode : 0
     * errmsg : ok
     * data : [{"scheduleId":5,"scheduleTypeName":"体侧课","isStart":"2","startDate":"19:40","endDate":"20:40","bespeak":2,"moreMember":2,"coach":"马敏","shopName":"也跑总店"}]
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
         * scheduleId : 5
         * scheduleTypeName : 体侧课
         * isStart : 2
         * startDate : 19:40
         * endDate : 20:40
         * bespeak : 2
         * moreMember : 2
         * coach : 马敏
         * shopName : 也跑总店
         */

        private int scheduleId;
        private String scheduleTypeName;
        private String isStart;
        private String startDate;
        private String endDate;
        private int bespeak;
        private int moreMember;
        private String coach;
        private String shopName;

        public int getScheduleId() {
            return scheduleId;
        }

        public void setScheduleId(int scheduleId) {
            this.scheduleId = scheduleId;
        }

        public String getScheduleTypeName() {
            return scheduleTypeName;
        }

        public void setScheduleTypeName(String scheduleTypeName) {
            this.scheduleTypeName = scheduleTypeName;
        }

        public String getIsStart() {
            return isStart;
        }

        public void setIsStart(String isStart) {
            this.isStart = isStart;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public int getBespeak() {
            return bespeak;
        }

        public void setBespeak(int bespeak) {
            this.bespeak = bespeak;
        }

        public int getMoreMember() {
            return moreMember;
        }

        public void setMoreMember(int moreMember) {
            this.moreMember = moreMember;
        }

        public String getCoach() {
            return coach;
        }

        public void setCoach(String coach) {
            this.coach = coach;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }
    }
}
