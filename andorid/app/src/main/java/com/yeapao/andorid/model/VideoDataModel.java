package com.yeapao.andorid.model;

import java.util.List;

/**
 * Created by fujindong on 2017/8/12.
 */

public class VideoDataModel {

    /**
     * errcode : 0
     * errmsg : ok
     * data : [{"durationTime":2036854775808,"imageUrl":"http://47.92.113.97:8080/yepao/image/1a/65/1a65c9d60cd8454eb421827cdd6815eb.jpg","id":12,"title":"减脂","url":"http://47.92.113.97:8080/yepao/media/appsp/K2.mp4","desc":"减脂要坚持"},{"durationTime":2036854775808,"imageUrl":"http://47.92.113.97:8080/yepao/image/1a/65/1a65c9d60cd8454eb421827cdd6815eb.jpg","id":18,"title":"减脂","url":"http://47.92.113.97:8080/yepao/media/appsp/K2.mp4","desc":"减脂要坚持"},{"durationTime":2036854775808,"imageUrl":"http://47.92.113.97:8080/yepao/image/1a/65/1a65c9d60cd8454eb421827cdd6815eb.jpg","id":21,"title":"减脂","url":"http://47.92.113.97:8080/yepao/media/appsp/K2.mp4","desc":"减脂要坚持"},{"durationTime":2036854775808,"imageUrl":"http://47.92.113.97:8080/yepao/image/1a/65/1a65c9d60cd8454eb421827cdd6815eb.jpg","id":24,"title":"减脂","url":"http://47.92.113.97:8080/yepao/media/appsp/K2.mp4","desc":"减脂要坚持"}]
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
         * durationTime : 2036854775808
         * imageUrl : http://47.92.113.97:8080/yepao/image/1a/65/1a65c9d60cd8454eb421827cdd6815eb.jpg
         * id : 12
         * title : 减脂
         * url : http://47.92.113.97:8080/yepao/media/appsp/K2.mp4
         * desc : 减脂要坚持
         */

        private long durationTime;
        private String imageUrl;
        private int id;
        private String title;
        private String url;
        private String desc;

        public long getDurationTime() {
            return durationTime;
        }

        public void setDurationTime(long durationTime) {
            this.durationTime = durationTime;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}
