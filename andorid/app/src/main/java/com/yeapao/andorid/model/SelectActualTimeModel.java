package com.yeapao.andorid.model;

/**
 * Created by fujindong on 2017/9/14.
 */

public class SelectActualTimeModel {

    /**
     * errcode : 0
     * errmsg : ok
     * data : {"warehouseId":"1","warehouseName":"0210001","status":"1","isMyWarehouse":"1","price":"1"}
     */

    private String errcode;
    private String errmsg;
    private DataBean data;

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
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
         * warehouseId : 1
         * warehouseName : 0210001
         * status : 1
         * isMyWarehouse : 1
         * price : 1
         */

        private String warehouseId;
        private String warehouseName;
        private String status;
        private String isMyWarehouse;
        private String price;

        public String getWarehouseId() {
            return warehouseId;
        }

        public void setWarehouseId(String warehouseId) {
            this.warehouseId = warehouseId;
        }

        public String getWarehouseName() {
            return warehouseName;
        }

        public void setWarehouseName(String warehouseName) {
            this.warehouseName = warehouseName;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getIsMyWarehouse() {
            return isMyWarehouse;
        }

        public void setIsMyWarehouse(String isMyWarehouse) {
            this.isMyWarehouse = isMyWarehouse;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
}
