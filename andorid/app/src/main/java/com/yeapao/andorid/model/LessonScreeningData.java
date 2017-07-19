package com.yeapao.andorid.model;

/**
 * Created by fujindong on 2017/7/19.
 */

public class LessonScreeningData {


    private String scopeTime = "-1";
    private String status="0";
    private String region="区域";

    private String scopeTimeName = "全部时间";
    private String statusName = "全部状态";
    private String regionName = "全部区域";

    public String getScopeTimeName() {
        return scopeTimeName;
    }

    public void setScopeTimeName(String scopeTimeName) {
        this.scopeTimeName = scopeTimeName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getScopeTime() {
        return scopeTime;
    }

    public void setScopeTime(String scopeTime) {
        this.scopeTime = scopeTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }


}
