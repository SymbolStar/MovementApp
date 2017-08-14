package com.yeapao.andorid.model;

import java.util.List;

/**
 * Created by fujindong on 2017/7/22.
 */

public class ShoppingDataModel {


    /**
     * errcode : 0
     * errmsg : ok
     * data : [{"curriculumId":1,"curriculumName":"单车","onlinePrice":190,"linePrice":90},{"curriculumId":2,"curriculumName":"跑步","onlinePrice":200,"linePrice":100}]
     */

    private int errcode;
    private String errmsg;
    private List<DataBean> data;
    private boolean allChecked = false;

    public boolean isAllChecked() {
        return allChecked;
    }

    public void setAllChecked(boolean allChecked) {
        this.allChecked = allChecked;
    }

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
         * curriculumId : 1
         * curriculumName : 单车
         * onlinePrice : 190
         * linePrice : 90
         */

        private int curriculumId;
        private String curriculumName;
        private int onlinePrice;
        private int linePrice;
        private long map_curriculum_typesId;

        public long getMap_curriculum_typesId() {
            return map_curriculum_typesId;
        }

        public void setMap_curriculum_typesId(long map_curriculum_typesId) {
            this.map_curriculum_typesId = map_curriculum_typesId;
        }

        private boolean lineChecked = false;
        private boolean onlineChecked = false;

        public boolean isLineChecked() {
            return lineChecked;
        }

        public void setLineChecked(boolean lineChecked) {
            this.lineChecked = lineChecked;
        }

        public boolean isOnlineChecked() {
            return onlineChecked;
        }

        public void setOnlineChecked(boolean onlineChecked) {
            this.onlineChecked = onlineChecked;
        }

        public int getCurriculumId() {
            return curriculumId;
        }

        public void setCurriculumId(int curriculumId) {
            this.curriculumId = curriculumId;
        }

        public String getCurriculumName() {
            return curriculumName;
        }

        public void setCurriculumName(String curriculumName) {
            this.curriculumName = curriculumName;
        }

        public int getOnlinePrice() {
            return onlinePrice;
        }

        public void setOnlinePrice(int onlinePrice) {
            this.onlinePrice = onlinePrice;
        }

        public int getLinePrice() {
            return linePrice;
        }

        public void setLinePrice(int linePrice) {
            this.linePrice = linePrice;
        }
    }
}
