package com.yeapao.brake.bean;

import java.util.List;

/**
 * Created by fujindong on 2017/8/29.
 */

public class ScreenModel {

    /**
     * rs : Y
     * weekRank : []
     * user_name : 里
     * classes : []
     * count : 13
     * monthRank : [{"user_name":"里","mem_id":"5852389e55040f60b903a3b4","count":31},{"user_name":"单国太","mem_id":"584e10f855040f694cf25a24","count":21},{"user_name":"韩","mem_id":"5865d5fa55040f529722c32a","count":11},{"user_name":"lilili","mem_id":"584f558055040f3bebb4c397","count":6},{"user_name":"臧冬雨","mem_id":"5936315524189033c24f9322","count":4},{"user_name":"周天","mem_id":"5865b7aa55040f529722bbea","count":3},{"user_name":"M","mem_id":"591034092418904a6d4f749f","count":1},{"user_name":"李奇峰","mem_id":"585b990055040f170e0df158","count":1}]
     * pic2 : http://oixty02vf.bkt.clouddn.com/6a098cbf-5130-4e7a-ac1c-f9ae7c097fdd_._d742fc1edd184118bd963ce1c5928e08.jpg
     */

    private String rs;
    private String user_name;
    private int count;
    private String pic2;
    private List<WeekRankBean> weekRank;
    private List<ClassesBean> classes;
    private List<MonthRankBean> monthRank;

    public String getRs() {
        return rs;
    }

    public void setRs(String rs) {
        this.rs = rs;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getPic2() {
        return pic2;
    }

    public void setPic2(String pic2) {
        this.pic2 = pic2;
    }

    public List<WeekRankBean> getWeekRank() {
        return weekRank;
    }

    public void setWeekRank(List<WeekRankBean> weekRank) {
        this.weekRank = weekRank;
    }

    public List<ClassesBean> getClasses() {
        return classes;
    }

    public void setClasses(List<ClassesBean> classes) {
        this.classes = classes;
    }

    public List<MonthRankBean> getMonthRank() {
        return monthRank;
    }

    public void setMonthRank(List<MonthRankBean> monthRank) {
        this.monthRank = monthRank;
    }


    public static class WeekRankBean{
        private String user_name;
        private String mem_id;
        private String count;

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getMem_id() {
            return mem_id;
        }

        public void setMem_id(String mem_id) {
            this.mem_id = mem_id;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }
    }

    public static class ClassesBean{
        private String lesson_name;
        private String emp_name;
        private String place_name;
        private String psize;
        private String start_time;
        private String start_time_long;
        private String order_numbers;

        public String getLesson_name() {
            return lesson_name;
        }

        public void setLesson_name(String lesson_name) {
            this.lesson_name = lesson_name;
        }

        public String getEmp_name() {
            return emp_name;
        }

        public void setEmp_name(String emp_name) {
            this.emp_name = emp_name;
        }

        public String getPlace_name() {
            return place_name;
        }

        public void setPlace_name(String place_name) {
            this.place_name = place_name;
        }

        public String getPsize() {
            return psize;
        }

        public void setPsize(String psize) {
            this.psize = psize;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getStart_time_long() {
            return start_time_long;
        }

        public void setStart_time_long(String start_time_long) {
            this.start_time_long = start_time_long;
        }

        public String getOrder_numbers() {
            return order_numbers;
        }

        public void setOrder_numbers(String order_numbers) {
            this.order_numbers = order_numbers;
        }
    }

    public static class MonthRankBean {
        /**
         * user_name : 里
         * mem_id : 5852389e55040f60b903a3b4
         * count : 31
         */

        private String user_name;
        private String mem_id;
        private int count;

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getMem_id() {
            return mem_id;
        }

        public void setMem_id(String mem_id) {
            this.mem_id = mem_id;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
