package com.yeapao.andorid.model;

import java.io.File;

/**
 * Created by fujindong on 2017/8/8.
 */

public class BodySideForthSaveModel {
    private File positive;
    private File side;
    private File back;
    private File furredTongue;
    private String bodySideId;

    public File getPositive() {
        return positive;
    }

    public void setPositive(File positive) {
        this.positive = positive;
    }

    public File getSide() {
        return side;
    }

    public void setSide(File side) {
        this.side = side;
    }

    public File getBack() {
        return back;
    }

    public void setBack(File back) {
        this.back = back;
    }

    public File getFurredTongue() {
        return furredTongue;
    }

    public void setFurredTongue(File furredTongue) {
        this.furredTongue = furredTongue;
    }

    public String getBodySideId() {
        return bodySideId;
    }

    public void setBodySideId(String bodySideId) {
        this.bodySideId = bodySideId;
    }
}
