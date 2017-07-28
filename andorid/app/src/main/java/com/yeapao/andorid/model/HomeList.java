package com.yeapao.andorid.model;

import com.yeapao.andorid.homepage.shopping.ShoppingContract;

import java.util.List;

/**
 * Created by fujindong on 2017/7/18.
 */

public class HomeList {
    private List<AdvertisementList> advertisementList;
    private List<ShopScheduleList> shopScheduleList;
    private String[] areaList;

    public String[] getAreaList() {
        return areaList;
    }

    public void setAreaList(String[] areaList) {
        this.areaList = areaList;
    }

    public List<AdvertisementList> getAdvertisementList() {
        return advertisementList;
    }

    public void setAdvertisementList(List<AdvertisementList> advertisementList) {
        this.advertisementList = advertisementList;
    }

    public List<ShopScheduleList> getShopScheduleList() {
        return shopScheduleList;
    }

    public void setShopScheduleList(List<ShopScheduleList> shopScheduleList) {
        this.shopScheduleList = shopScheduleList;
    }
}
