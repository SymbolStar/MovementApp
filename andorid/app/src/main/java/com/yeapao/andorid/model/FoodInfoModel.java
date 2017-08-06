package com.yeapao.andorid.model;

import java.util.List;

/**
 * Created by fujindong on 2017/8/5.
 */

public class FoodInfoModel {

    /**
     * errcode : 0
     * errmsg : ok
     * data : {"lunch":[{"image":"http://localhost:8080/image/sp/wuc/1/3.jpg","measure":"lunch","name":"全麦面包"},{"image":"http://localhost:8080/image/sp/wuc/2/4.jpg","measure":"lunch","name":"黄鱼"},{"image":"http://localhost:8080/image/sp/wuc/3/3.jpg","measure":"lunch","name":"春笋"},{"image":"http://localhost:8080/image/sp/wuc/4/3.jpg","measure":"lunch","name":"核桃"},{"image":"http://localhost:8080/image/sp/wuc/5/3.jpg","measure":"lunch","name":"芒果"}],"breakfast":[{"image":"http://localhost:8080/image/sp/zc/1.jpg","measure":"breakfast","name":"白开水"},{"image":"http://localhost:8080/image/sp/zc/2.jpg","measure":"breakfast","name":"水煮蛋"}],"dinner":[{"image":"http://localhost:8080/image/sp/wanc/1/1.jpg","measure":"dinner","name":"鹌鹑"},{"image":"http://localhost:8080/image/sp/wanc/2/3.jpg","measure":"dinner","name":"甘蓝"},{"image":"http://localhost:8080/image/sp/wanc/3/1.jpg","measure":"dinner","name":"鳊鱼"}]}
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
        private List<LunchBean> lunch;
        private List<LunchBean> breakfast;
        private List<LunchBean> dinner;

        public List<LunchBean> getLunch() {
            return lunch;
        }

        public void setLunch(List<LunchBean> lunch) {
            this.lunch = lunch;
        }

        public List<LunchBean> getBreakfast() {
            return breakfast;
        }

        public void setBreakfast(List<LunchBean> breakfast) {
            this.breakfast = breakfast;
        }

        public List<LunchBean> getDinner() {
            return dinner;
        }

        public void setDinner(List<LunchBean> dinner) {
            this.dinner = dinner;
        }

        public static class LunchBean {
            /**
             * image : http://localhost:8080/image/sp/wuc/1/3.jpg
             * measure : lunch
             * name : 全麦面包
             */

            private String image;
            private String measure;
            private String name;

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
        }

        public static class BreakfastBean {
            /**
             * image : http://localhost:8080/image/sp/zc/1.jpg
             * measure : breakfast
             * name : 白开水
             */

            private String image;
            private String measure;
            private String name;

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
        }

        public static class DinnerBean {
            /**
             * image : http://localhost:8080/image/sp/wanc/1/1.jpg
             * measure : dinner
             * name : 鹌鹑
             */

            private String image;
            private String measure;
            private String name;

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
        }
    }
}
