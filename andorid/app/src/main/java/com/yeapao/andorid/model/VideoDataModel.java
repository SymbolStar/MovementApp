package com.yeapao.andorid.model;

import java.util.List;

/**
 * Created by fujindong on 2017/8/12.
 */

public class VideoDataModel {

    /**
     * errcode : 0
     * errmsg : ok
     * data : [{"durationTime":5000,"imageUrl":"http://47.92.113.97:8080/yepaonull","id":1,"title":"aa","url":"http://47.92.113.97:8080/yepao/image/fd/b5/1501582871039.flv","desc":"bbb"},{"durationTime":2000,"imageUrl":"http://47.92.113.97:8080/yepaonull","id":2,"title":"adfse","url":"http://47.92.113.97:8080/yepao/image/0a/fd/1501584133270.mp4","desc":"wefadssfwefasdf"},{"durationTime":5000,"imageUrl":"http://47.92.113.97:8080/yepaonull","id":3,"title":"aa","url":"http://47.92.113.97:8080/yepao/image/fd/b5/1501582871039.flv","desc":"bbb"},{"durationTime":5000,"imageUrl":"http://47.92.113.97:8080/yepaonull","id":4,"title":"aa","url":"http://47.92.113.97:8080/yepao/image/fd/b5/1501582871039.flv","desc":"bbb"},{"durationTime":5000,"imageUrl":"http://47.92.113.97:8080/yepaonull","id":5,"title":"aa","url":"http://47.92.113.97:8080/yepao/image/fd/b5/1501582871039.flv","desc":"bbb"}]
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
         * durationTime : 5000
         * imageUrl : http://47.92.113.97:8080/yepaonull
         * id : 1
         * title : aa
         * url : http://47.92.113.97:8080/yepao/image/fd/b5/1501582871039.flv
         * desc : bbb
         */

        private int durationTime;
        private String imageUrl;
        private int id;
        private String title;
        private String url;
        private String desc;

        public int getDurationTime() {
            return durationTime;
        }

        public void setDurationTime(int durationTime) {
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
