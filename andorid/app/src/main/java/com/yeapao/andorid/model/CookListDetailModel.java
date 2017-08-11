package com.yeapao.andorid.model;

import java.util.List;

/**
 * Created by fujindong on 2017/8/11.
 */

public class CookListDetailModel {

    /**
     * errcode : 0
     * errmsg : ok
     * data : [{"image":"http://47.92.113.97:8080/yepao/image/sp/wuc/1/1.jpg","measure":"500克","name":"红薯","id":3,"type":"1"},{"image":"http://47.92.113.97:8080/yepao/image/sp/wuc/1/2.jpg","measure":"500克","name":"南瓜","id":13,"type":"1"},{"image":"http://47.92.113.97:8080/yepao/image/sp/wuc/1/3.jpg","measure":"500克","name":"全麦面包","id":23,"type":"1"},{"image":"http://47.92.113.97:8080/yepao/image/sp/wuc/1/4.jpg","measure":"500克","name":"玉米","id":33,"type":"1"},{"image":"http://47.92.113.97:8080/yepao/image/sp/wuc/1/1.jpg","measure":"500克","name":"红薯","id":43,"type":"1"},{"image":"http://47.92.113.97:8080/yepao/image/sp/wuc/1/2.jpg","measure":"500克","name":"南瓜","id":53,"type":"1"},{"image":"http://47.92.113.97:8080/yepao/image/sp/wuc/1/4.jpg","measure":"500克","name":"玉米","id":63,"type":"1"}]
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
         * image : http://47.92.113.97:8080/yepao/image/sp/wuc/1/1.jpg
         * measure : 500克
         * name : 红薯
         * id : 3
         * type : 1
         */

        private String image;
        private String measure;
        private String name;
        private int id;
        private String type;

        private String meal;
        private String position;

        public String getMeal() {
            return meal;
        }

        public void setMeal(String meal) {
            this.meal = meal;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getMeasure() {
            return measure;
        }

        public void setMeasure(String measure) {
            this.measure = measure;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
