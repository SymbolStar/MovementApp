package com.yeapao.andorid.model;

import java.util.List;

/**
 * Created by fujindong on 2017/9/8.
 */

public class WareHouseListModel {

    /**
     * errcode : 0
     * errmsg : ok
     * data : {"reservationPrice":1,"myUserWarehouse":"0","warehouseListOut":[{"warehouseId":1,"warehouseName":"0210001","warehouseTypeId":1,"reservaStatus":"0","actualStatus":"0","status":"1","longitude":121.49527,"latitude":31.263777,"isMyReserva":"0","reservaStartTime":null,"duration":null,"isOfflineReminder":"0","isMyUserWarehouse":"0"},{"warehouseId":2,"warehouseName":"05120001","warehouseTypeId":1,"reservaStatus":"0","actualStatus":"0","status":"1","longitude":120.575989,"latitude":31.302918,"isMyReserva":"0","reservaStartTime":null,"duration":null,"isOfflineReminder":"0","isMyUserWarehouse":"0"},{"warehouseId":3,"warehouseName":"05120002","warehouseTypeId":1,"reservaStatus":"0","actualStatus":"0","status":"1","longitude":120.634,"latitude":31.296384,"isMyReserva":"0","reservaStartTime":null,"duration":null,"isOfflineReminder":"0","isMyUserWarehouse":"0"},{"warehouseId":4,"warehouseName":"05120003","warehouseTypeId":1,"reservaStatus":"0","actualStatus":"0","status":"1","longitude":120.574857,"latitude":31.304335,"isMyReserva":"0","reservaStartTime":null,"duration":null,"isOfflineReminder":"0","isMyUserWarehouse":"0"},{"warehouseId":5,"warehouseName":"05120004","warehouseTypeId":1,"reservaStatus":"0","actualStatus":"0","status":"1","longitude":120.582511,"latitude":31.301582,"isMyReserva":"0","reservaStartTime":null,"duration":null,"isOfflineReminder":"0","isMyUserWarehouse":"0"}],"isQualified":"0","isUnpaid":0,"warehouseId":0}
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
        /**
         * reservationPrice : 1
         * myUserWarehouse : 0
         * warehouseListOut : [{"warehouseId":1,"warehouseName":"0210001","warehouseTypeId":1,"reservaStatus":"0","actualStatus":"0","status":"1","longitude":121.49527,"latitude":31.263777,"isMyReserva":"0","reservaStartTime":null,"duration":null,"isOfflineReminder":"0","isMyUserWarehouse":"0"},{"warehouseId":2,"warehouseName":"05120001","warehouseTypeId":1,"reservaStatus":"0","actualStatus":"0","status":"1","longitude":120.575989,"latitude":31.302918,"isMyReserva":"0","reservaStartTime":null,"duration":null,"isOfflineReminder":"0","isMyUserWarehouse":"0"},{"warehouseId":3,"warehouseName":"05120002","warehouseTypeId":1,"reservaStatus":"0","actualStatus":"0","status":"1","longitude":120.634,"latitude":31.296384,"isMyReserva":"0","reservaStartTime":null,"duration":null,"isOfflineReminder":"0","isMyUserWarehouse":"0"},{"warehouseId":4,"warehouseName":"05120003","warehouseTypeId":1,"reservaStatus":"0","actualStatus":"0","status":"1","longitude":120.574857,"latitude":31.304335,"isMyReserva":"0","reservaStartTime":null,"duration":null,"isOfflineReminder":"0","isMyUserWarehouse":"0"},{"warehouseId":5,"warehouseName":"05120004","warehouseTypeId":1,"reservaStatus":"0","actualStatus":"0","status":"1","longitude":120.582511,"latitude":31.301582,"isMyReserva":"0","reservaStartTime":null,"duration":null,"isOfflineReminder":"0","isMyUserWarehouse":"0"}]
         * isQualified : 0
         * isUnpaid : 0
         * warehouseId : 0
         */

        private int reservationPrice;
        private String myUserWarehouse;
        private String isQualified;
        private int isUnpaid;
        private int warehouseId;
        private List<WarehouseListOutBean> warehouseListOut;

        public int getReservationPrice() {
            return reservationPrice;
        }

        public void setReservationPrice(int reservationPrice) {
            this.reservationPrice = reservationPrice;
        }

        public String getMyUserWarehouse() {
            return myUserWarehouse;
        }

        public void setMyUserWarehouse(String myUserWarehouse) {
            this.myUserWarehouse = myUserWarehouse;
        }

        public String getIsQualified() {
            return isQualified;
        }

        public void setIsQualified(String isQualified) {
            this.isQualified = isQualified;
        }

        public int getIsUnpaid() {
            return isUnpaid;
        }

        public void setIsUnpaid(int isUnpaid) {
            this.isUnpaid = isUnpaid;
        }

        public int getWarehouseId() {
            return warehouseId;
        }

        public void setWarehouseId(int warehouseId) {
            this.warehouseId = warehouseId;
        }

        public List<WarehouseListOutBean> getWarehouseListOut() {
            return warehouseListOut;
        }

        public void setWarehouseListOut(List<WarehouseListOutBean> warehouseListOut) {
            this.warehouseListOut = warehouseListOut;
        }

        public static class WarehouseListOutBean {
            /**
             * warehouseId : 1
             * warehouseName : 0210001
             * warehouseTypeId : 1
             * reservaStatus : 0
             * actualStatus : 0
             * status : 1
             * longitude : 121.49527
             * latitude : 31.263777
             * isMyReserva : 0
             * reservaStartTime : null
             * duration : null
             * isOfflineReminder : 0
             * isMyUserWarehouse : 0
             */

            private int warehouseId;
            private String warehouseName;
            private int warehouseTypeId;
            private String reservaStatus;
            private String actualStatus;
            private String status;
            private double longitude;
            private double latitude;
            private String isMyReserva;
            private String reservaStartTime;
            private String duration;
            private String isOfflineReminder;
            private String isMyUserWarehouse;

            public int getWarehouseId() {
                return warehouseId;
            }

            public void setWarehouseId(int warehouseId) {
                this.warehouseId = warehouseId;
            }

            public String getWarehouseName() {
                return warehouseName;
            }

            public void setWarehouseName(String warehouseName) {
                this.warehouseName = warehouseName;
            }

            public int getWarehouseTypeId() {
                return warehouseTypeId;
            }

            public void setWarehouseTypeId(int warehouseTypeId) {
                this.warehouseTypeId = warehouseTypeId;
            }

            public String getReservaStatus() {
                return reservaStatus;
            }

            public void setReservaStatus(String reservaStatus) {
                this.reservaStatus = reservaStatus;
            }

            public String getActualStatus() {
                return actualStatus;
            }

            public void setActualStatus(String actualStatus) {
                this.actualStatus = actualStatus;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public double getLongitude() {
                return longitude;
            }

            public void setLongitude(double longitude) {
                this.longitude = longitude;
            }

            public double getLatitude() {
                return latitude;
            }

            public void setLatitude(double latitude) {
                this.latitude = latitude;
            }

            public String getIsMyReserva() {
                return isMyReserva;
            }

            public void setIsMyReserva(String isMyReserva) {
                this.isMyReserva = isMyReserva;
            }

            public String getReservaStartTime() {
                return reservaStartTime;
            }

            public void setReservaStartTime(String reservaStartTime) {
                this.reservaStartTime = reservaStartTime;
            }

            public String getDuration() {
                return duration;
            }

            public void setDuration(String duration) {
                this.duration = duration;
            }

            public String getIsOfflineReminder() {
                return isOfflineReminder;
            }

            public void setIsOfflineReminder(String isOfflineReminder) {
                this.isOfflineReminder = isOfflineReminder;
            }

            public String getIsMyUserWarehouse() {
                return isMyUserWarehouse;
            }

            public void setIsMyUserWarehouse(String isMyUserWarehouse) {
                this.isMyUserWarehouse = isMyUserWarehouse;
            }
        }
    }
}
