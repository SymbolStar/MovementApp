package com.yeapao.brake.bean;

/**
 * Created by fujindong on 2017/8/28.
 */

public class CheckInOrOutModel {

    /**
     * rs : Y
     * flag : CHECKIN
     * pic2 : http://oixty02vf.bkt.clouddn.com/6a098cbf-5130-4e7a-ac1c-f9ae7c097fdd_._d742fc1edd184118bd963ce1c5928e08.jpg
     * user : {"birthday":"2016.12.06","user_name":"里","box_num":"a02","amt":"1397768604","all_amt":"0","checkin_state_str":"场内","pic1":"QR2?s=5852389e55040f60b903a3b4","pt_id":"5865b75655040f529722bbda","nickname":"里","pri_num":"14","id":"5852389e55040f60b903a3b4","state":"001","pic2":"http://oixty02vf.bkt.clouddn.com/6a098cbf-5130-4e7a-ac1c-f9ae7c097fdd_._d742fc1edd184118bd963ce1c5928e08.jpg","deadline":"2017-09-04 08:42:50.0","gift_amt":"0","sales_id":"595c80f555040f6473affad4","all_cents":"0","given":"Y","gift_amtStr":"0.00","sex":"male","box_start_time":"2017-08-05 00:00:00.0","has_box":"Y","mem_no":"8023","cents":"0","first_start_time":"2016-12-15","wx_open_id":"","stateStr":"激活","times_num":0,"login_name":"system","box_end_time":"2017-08-10","phone":"13260156620","sales_name":"臧冬雨","amtStr":"13977686.04","gym":"yepao","all_cash_amt":"0","how2know":"未知","pt_name":"周天","checkin_state":"002","emp_id":"5850d3a655040f4478679b5c"}
     */

    private String rs;
    private String flag;
    private String pic2;
    private UserBean user;

    public String getRs() {
        return rs;
    }

    public void setRs(String rs) {
        this.rs = rs;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getPic2() {
        return pic2;
    }

    public void setPic2(String pic2) {
        this.pic2 = pic2;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean {
        /**
         * birthday : 2016.12.06
         * user_name : 里
         * box_num : a02
         * amt : 1397768604
         * all_amt : 0
         * checkin_state_str : 场内
         * pic1 : QR2?s=5852389e55040f60b903a3b4
         * pt_id : 5865b75655040f529722bbda
         * nickname : 里
         * pri_num : 14
         * id : 5852389e55040f60b903a3b4
         * state : 001
         * pic2 : http://oixty02vf.bkt.clouddn.com/6a098cbf-5130-4e7a-ac1c-f9ae7c097fdd_._d742fc1edd184118bd963ce1c5928e08.jpg
         * deadline : 2017-09-04 08:42:50.0
         * gift_amt : 0
         * sales_id : 595c80f555040f6473affad4
         * all_cents : 0
         * given : Y
         * gift_amtStr : 0.00
         * sex : male
         * box_start_time : 2017-08-05 00:00:00.0
         * has_box : Y
         * mem_no : 8023
         * cents : 0
         * first_start_time : 2016-12-15
         * wx_open_id :
         * stateStr : 激活
         * times_num : 0
         * login_name : system
         * box_end_time : 2017-08-10
         * phone : 13260156620
         * sales_name : 臧冬雨
         * amtStr : 13977686.04
         * gym : yepao
         * all_cash_amt : 0
         * how2know : 未知
         * pt_name : 周天
         * checkin_state : 002
         * emp_id : 5850d3a655040f4478679b5c
         */

        private String birthday;
        private String user_name;
        private String box_num;
        private String amt;
        private String all_amt;
        private String checkin_state_str;
        private String pic1;
        private String pt_id;
        private String nickname;
        private String pri_num;
        private String id;
        private String state;
        private String pic2;
        private String deadline;
        private String gift_amt;
        private String sales_id;
        private String all_cents;
        private String given;
        private String gift_amtStr;
        private String sex;
        private String box_start_time;
        private String has_box;
        private String mem_no;
        private String cents;
        private String first_start_time;
        private String wx_open_id;
        private String stateStr;
        private int times_num;
        private String login_name;
        private String box_end_time;
        private String phone;
        private String sales_name;
        private String amtStr;
        private String gym;
        private String all_cash_amt;
        private String how2know;
        private String pt_name;
        private String checkin_state;
        private String emp_id;

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getBox_num() {
            return box_num;
        }

        public void setBox_num(String box_num) {
            this.box_num = box_num;
        }

        public String getAmt() {
            return amt;
        }

        public void setAmt(String amt) {
            this.amt = amt;
        }

        public String getAll_amt() {
            return all_amt;
        }

        public void setAll_amt(String all_amt) {
            this.all_amt = all_amt;
        }

        public String getCheckin_state_str() {
            return checkin_state_str;
        }

        public void setCheckin_state_str(String checkin_state_str) {
            this.checkin_state_str = checkin_state_str;
        }

        public String getPic1() {
            return pic1;
        }

        public void setPic1(String pic1) {
            this.pic1 = pic1;
        }

        public String getPt_id() {
            return pt_id;
        }

        public void setPt_id(String pt_id) {
            this.pt_id = pt_id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPri_num() {
            return pri_num;
        }

        public void setPri_num(String pri_num) {
            this.pri_num = pri_num;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getPic2() {
            return pic2;
        }

        public void setPic2(String pic2) {
            this.pic2 = pic2;
        }

        public String getDeadline() {
            return deadline;
        }

        public void setDeadline(String deadline) {
            this.deadline = deadline;
        }

        public String getGift_amt() {
            return gift_amt;
        }

        public void setGift_amt(String gift_amt) {
            this.gift_amt = gift_amt;
        }

        public String getSales_id() {
            return sales_id;
        }

        public void setSales_id(String sales_id) {
            this.sales_id = sales_id;
        }

        public String getAll_cents() {
            return all_cents;
        }

        public void setAll_cents(String all_cents) {
            this.all_cents = all_cents;
        }

        public String getGiven() {
            return given;
        }

        public void setGiven(String given) {
            this.given = given;
        }

        public String getGift_amtStr() {
            return gift_amtStr;
        }

        public void setGift_amtStr(String gift_amtStr) {
            this.gift_amtStr = gift_amtStr;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getBox_start_time() {
            return box_start_time;
        }

        public void setBox_start_time(String box_start_time) {
            this.box_start_time = box_start_time;
        }

        public String getHas_box() {
            return has_box;
        }

        public void setHas_box(String has_box) {
            this.has_box = has_box;
        }

        public String getMem_no() {
            return mem_no;
        }

        public void setMem_no(String mem_no) {
            this.mem_no = mem_no;
        }

        public String getCents() {
            return cents;
        }

        public void setCents(String cents) {
            this.cents = cents;
        }

        public String getFirst_start_time() {
            return first_start_time;
        }

        public void setFirst_start_time(String first_start_time) {
            this.first_start_time = first_start_time;
        }

        public String getWx_open_id() {
            return wx_open_id;
        }

        public void setWx_open_id(String wx_open_id) {
            this.wx_open_id = wx_open_id;
        }

        public String getStateStr() {
            return stateStr;
        }

        public void setStateStr(String stateStr) {
            this.stateStr = stateStr;
        }

        public int getTimes_num() {
            return times_num;
        }

        public void setTimes_num(int times_num) {
            this.times_num = times_num;
        }

        public String getLogin_name() {
            return login_name;
        }

        public void setLogin_name(String login_name) {
            this.login_name = login_name;
        }

        public String getBox_end_time() {
            return box_end_time;
        }

        public void setBox_end_time(String box_end_time) {
            this.box_end_time = box_end_time;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getSales_name() {
            return sales_name;
        }

        public void setSales_name(String sales_name) {
            this.sales_name = sales_name;
        }

        public String getAmtStr() {
            return amtStr;
        }

        public void setAmtStr(String amtStr) {
            this.amtStr = amtStr;
        }

        public String getGym() {
            return gym;
        }

        public void setGym(String gym) {
            this.gym = gym;
        }

        public String getAll_cash_amt() {
            return all_cash_amt;
        }

        public void setAll_cash_amt(String all_cash_amt) {
            this.all_cash_amt = all_cash_amt;
        }

        public String getHow2know() {
            return how2know;
        }

        public void setHow2know(String how2know) {
            this.how2know = how2know;
        }

        public String getPt_name() {
            return pt_name;
        }

        public void setPt_name(String pt_name) {
            this.pt_name = pt_name;
        }

        public String getCheckin_state() {
            return checkin_state;
        }

        public void setCheckin_state(String checkin_state) {
            this.checkin_state = checkin_state;
        }

        public String getEmp_id() {
            return emp_id;
        }

        public void setEmp_id(String emp_id) {
            this.emp_id = emp_id;
        }
    }
}
