package com.yeapao.andorid.model;

/**
 * Created by fujindong on 2017/8/12.
 */

public class BodySideDetailModel {


    /**
     * errcode : 0
     * errmsg : ok
     * data : {"bodySideTwo":{"id":2,"upperRight":"70","upperLeft":"80","abdomen":"79","waist":"90","hips":"89","lowerRight":"80","lowerLeft":"80","bodySideId":11},"bodySideThree":{"id":1,"upperLimbStrength":"60","lowerExtremityStrength":"70","precursor":"80","heartRateOne":"65","heartRateTwo":"75","heartRateThree":"85","bodySideId":11},"bodySideFour":{"id":1,"positive":"/image/74/f3/74f317f248dd491b9b42371f6671871d.png","side":"/image/ad/89/ad89b1eebf2342d69c6fdefcf24c167c.png","back":"/image/20/39/2039604e7715473cb4342bd31ed14d75.png","furredTongue":"/image/4c/4e/4c4eb0d686c4406d87dba592f6ddac20.png","bodySideId":11},"bodySideOne":{"id":11,"quietHeartRate":"69","bloodPressure":"_","height":"180","weight":"60","inBody":"70","presentation":"/image/8a/36/8a365712bce040329f8135c76bd65951.png","bodySideId":11}}
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
         * bodySideTwo : {"id":2,"upperRight":"70","upperLeft":"80","abdomen":"79","waist":"90","hips":"89","lowerRight":"80","lowerLeft":"80","bodySideId":11}
         * bodySideThree : {"id":1,"upperLimbStrength":"60","lowerExtremityStrength":"70","precursor":"80","heartRateOne":"65","heartRateTwo":"75","heartRateThree":"85","bodySideId":11}
         * bodySideFour : {"id":1,"positive":"/image/74/f3/74f317f248dd491b9b42371f6671871d.png","side":"/image/ad/89/ad89b1eebf2342d69c6fdefcf24c167c.png","back":"/image/20/39/2039604e7715473cb4342bd31ed14d75.png","furredTongue":"/image/4c/4e/4c4eb0d686c4406d87dba592f6ddac20.png","bodySideId":11}
         * bodySideOne : {"id":11,"quietHeartRate":"69","bloodPressure":"_","height":"180","weight":"60","inBody":"70","presentation":"/image/8a/36/8a365712bce040329f8135c76bd65951.png","bodySideId":11}
         */

        private BodySideTwoBean bodySideTwo;
        private BodySideThreeBean bodySideThree;
        private BodySideFourBean bodySideFour;
        private BodySideOneBean bodySideOne;

        public BodySideTwoBean getBodySideTwo() {
            return bodySideTwo;
        }

        public void setBodySideTwo(BodySideTwoBean bodySideTwo) {
            this.bodySideTwo = bodySideTwo;
        }

        public BodySideThreeBean getBodySideThree() {
            return bodySideThree;
        }

        public void setBodySideThree(BodySideThreeBean bodySideThree) {
            this.bodySideThree = bodySideThree;
        }

        public BodySideFourBean getBodySideFour() {
            return bodySideFour;
        }

        public void setBodySideFour(BodySideFourBean bodySideFour) {
            this.bodySideFour = bodySideFour;
        }

        public BodySideOneBean getBodySideOne() {
            return bodySideOne;
        }

        public void setBodySideOne(BodySideOneBean bodySideOne) {
            this.bodySideOne = bodySideOne;
        }

        public static class BodySideTwoBean {
            /**
             * id : 2
             * upperRight : 70
             * upperLeft : 80
             * abdomen : 79
             * waist : 90
             * hips : 89
             * lowerRight : 80
             * lowerLeft : 80
             * bodySideId : 11
             */

            private int id;
            private String upperRight;
            private String upperLeft;
            private String abdomen;
            private String waist;
            private String hips;
            private String lowerRight;
            private String lowerLeft;
            private int bodySideId;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUpperRight() {
                return upperRight;
            }

            public void setUpperRight(String upperRight) {
                this.upperRight = upperRight;
            }

            public String getUpperLeft() {
                return upperLeft;
            }

            public void setUpperLeft(String upperLeft) {
                this.upperLeft = upperLeft;
            }

            public String getAbdomen() {
                return abdomen;
            }

            public void setAbdomen(String abdomen) {
                this.abdomen = abdomen;
            }

            public String getWaist() {
                return waist;
            }

            public void setWaist(String waist) {
                this.waist = waist;
            }

            public String getHips() {
                return hips;
            }

            public void setHips(String hips) {
                this.hips = hips;
            }

            public String getLowerRight() {
                return lowerRight;
            }

            public void setLowerRight(String lowerRight) {
                this.lowerRight = lowerRight;
            }

            public String getLowerLeft() {
                return lowerLeft;
            }

            public void setLowerLeft(String lowerLeft) {
                this.lowerLeft = lowerLeft;
            }

            public int getBodySideId() {
                return bodySideId;
            }

            public void setBodySideId(int bodySideId) {
                this.bodySideId = bodySideId;
            }
        }

        public static class BodySideThreeBean {
            /**
             * id : 1
             * upperLimbStrength : 60
             * lowerExtremityStrength : 70
             * precursor : 80
             * heartRateOne : 65
             * heartRateTwo : 75
             * heartRateThree : 85
             * bodySideId : 11
             */

            private int id;
            private String upperLimbStrength;
            private String lowerExtremityStrength;
            private String precursor;
            private String heartRateOne;
            private String heartRateTwo;
            private String heartRateThree;
            private int bodySideId;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUpperLimbStrength() {
                return upperLimbStrength;
            }

            public void setUpperLimbStrength(String upperLimbStrength) {
                this.upperLimbStrength = upperLimbStrength;
            }

            public String getLowerExtremityStrength() {
                return lowerExtremityStrength;
            }

            public void setLowerExtremityStrength(String lowerExtremityStrength) {
                this.lowerExtremityStrength = lowerExtremityStrength;
            }

            public String getPrecursor() {
                return precursor;
            }

            public void setPrecursor(String precursor) {
                this.precursor = precursor;
            }

            public String getHeartRateOne() {
                return heartRateOne;
            }

            public void setHeartRateOne(String heartRateOne) {
                this.heartRateOne = heartRateOne;
            }

            public String getHeartRateTwo() {
                return heartRateTwo;
            }

            public void setHeartRateTwo(String heartRateTwo) {
                this.heartRateTwo = heartRateTwo;
            }

            public String getHeartRateThree() {
                return heartRateThree;
            }

            public void setHeartRateThree(String heartRateThree) {
                this.heartRateThree = heartRateThree;
            }

            public int getBodySideId() {
                return bodySideId;
            }

            public void setBodySideId(int bodySideId) {
                this.bodySideId = bodySideId;
            }
        }

        public static class BodySideFourBean {
            /**
             * id : 1
             * positive : /image/74/f3/74f317f248dd491b9b42371f6671871d.png
             * side : /image/ad/89/ad89b1eebf2342d69c6fdefcf24c167c.png
             * back : /image/20/39/2039604e7715473cb4342bd31ed14d75.png
             * furredTongue : /image/4c/4e/4c4eb0d686c4406d87dba592f6ddac20.png
             * bodySideId : 11
             */

            private int id;
            private String positive;
            private String side;
            private String back;
            private String furredTongue;
            private int bodySideId;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPositive() {
                return positive;
            }

            public void setPositive(String positive) {
                this.positive = positive;
            }

            public String getSide() {
                return side;
            }

            public void setSide(String side) {
                this.side = side;
            }

            public String getBack() {
                return back;
            }

            public void setBack(String back) {
                this.back = back;
            }

            public String getFurredTongue() {
                return furredTongue;
            }

            public void setFurredTongue(String furredTongue) {
                this.furredTongue = furredTongue;
            }

            public int getBodySideId() {
                return bodySideId;
            }

            public void setBodySideId(int bodySideId) {
                this.bodySideId = bodySideId;
            }
        }

        public static class BodySideOneBean {
            /**
             * id : 11
             * quietHeartRate : 69
             * bloodPressure : _
             * height : 180
             * weight : 60
             * inBody : 70
             * presentation : /image/8a/36/8a365712bce040329f8135c76bd65951.png
             * bodySideId : 11
             */

            private int id;
            private String quietHeartRate;
            private String bloodPressure;
            private String height;
            private String weight;
            private String inBody;
            private String presentation;
            private int bodySideId;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getQuietHeartRate() {
                return quietHeartRate;
            }

            public void setQuietHeartRate(String quietHeartRate) {
                this.quietHeartRate = quietHeartRate;
            }

            public String getBloodPressure() {
                return bloodPressure;
            }

            public void setBloodPressure(String bloodPressure) {
                this.bloodPressure = bloodPressure;
            }

            public String getHeight() {
                return height;
            }

            public void setHeight(String height) {
                this.height = height;
            }

            public String getWeight() {
                return weight;
            }

            public void setWeight(String weight) {
                this.weight = weight;
            }

            public String getInBody() {
                return inBody;
            }

            public void setInBody(String inBody) {
                this.inBody = inBody;
            }

            public String getPresentation() {
                return presentation;
            }

            public void setPresentation(String presentation) {
                this.presentation = presentation;
            }

            public int getBodySideId() {
                return bodySideId;
            }

            public void setBodySideId(int bodySideId) {
                this.bodySideId = bodySideId;
            }
        }
    }
}
