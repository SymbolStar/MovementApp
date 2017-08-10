package com.yeapao.andorid.model;

import java.util.List;

/**
 * Created by fujindong on 2017/8/9.
 */

public class RollCallListModel {

    /**
     * errcode : 0
     * errmsg : ok
     * data : [{"name":"le","head":"/image/head02.png","customerId":17,"reservationDetailsId":36,"painDegreeList":[{"id":4,"position":"右小臂","degree":4,"reservationDetailsId":36},{"id":5,"position":"左小腿","degree":5,"reservationDetailsId":36},{"id":6,"position":"右脚","degree":2,"reservationDetailsId":36}]},{"name":"123","head":"/image/head04.png","customerId":21,"reservationDetailsId":37,"painDegreeList":[{"id":7,"position":"左大腿","degree":6,"reservationDetailsId":37},{"id":8,"position":"右大腿","degree":6,"reservationDetailsId":37},{"id":9,"position":"右脚","degree":7,"reservationDetailsId":37}]}]
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
         * name : le
         * head : /image/head02.png
         * customerId : 17
         * reservationDetailsId : 36
         * painDegreeList : [{"id":4,"position":"右小臂","degree":4,"reservationDetailsId":36},{"id":5,"position":"左小腿","degree":5,"reservationDetailsId":36},{"id":6,"position":"右脚","degree":2,"reservationDetailsId":36}]
         */

        private String name;
        private String head;
        private int customerId;
        private int reservationDetailsId;
        private List<PainDegreeListBean> painDegreeList;
        private boolean status;

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }

        public int getCustomerId() {
            return customerId;
        }

        public void setCustomerId(int customerId) {
            this.customerId = customerId;
        }

        public int getReservationDetailsId() {
            return reservationDetailsId;
        }

        public void setReservationDetailsId(int reservationDetailsId) {
            this.reservationDetailsId = reservationDetailsId;
        }

        public List<PainDegreeListBean> getPainDegreeList() {
            return painDegreeList;
        }

        public void setPainDegreeList(List<PainDegreeListBean> painDegreeList) {
            this.painDegreeList = painDegreeList;
        }

        public static class PainDegreeListBean {
            /**
             * id : 4
             * position : 右小臂
             * degree : 4
             * reservationDetailsId : 36
             */

            private int id;
            private String position;
            private int degree;
            private int reservationDetailsId;


            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }

            public int getDegree() {
                return degree;
            }

            public void setDegree(int degree) {
                this.degree = degree;
            }

            public int getReservationDetailsId() {
                return reservationDetailsId;
            }

            public void setReservationDetailsId(int reservationDetailsId) {
                this.reservationDetailsId = reservationDetailsId;
            }
        }
    }
}
