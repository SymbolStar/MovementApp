package com.yeapao.andorid.model;

/**
 * Created by fujindong on 2017/7/26.
 */

public class VerificationCodeModel {


    /**
     * errcode : 0
     * errmsg : ok
     * data : {"requestId":null,"messageId":"EB257818FC9F2392-1-15D7C691589-400000082","messageBodyMD5":"3BF48D1C9AB2974E85D1C1FE8371B3EB","messageTag":null,"messageBodyAsString":null,"messageBody":null,"messageBodyAsBytes":null}
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
         * requestId : null
         * messageId : EB257818FC9F2392-1-15D7C691589-400000082
         * messageBodyMD5 : 3BF48D1C9AB2974E85D1C1FE8371B3EB
         * messageTag : null
         * messageBodyAsString : null
         * messageBody : null
         * messageBodyAsBytes : null
         */

        private Object requestId;
        private String messageId;
        private String messageBodyMD5;
        private Object messageTag;
        private Object messageBodyAsString;
        private Object messageBody;
        private Object messageBodyAsBytes;

        public Object getRequestId() {
            return requestId;
        }

        public void setRequestId(Object requestId) {
            this.requestId = requestId;
        }

        public String getMessageId() {
            return messageId;
        }

        public void setMessageId(String messageId) {
            this.messageId = messageId;
        }

        public String getMessageBodyMD5() {
            return messageBodyMD5;
        }

        public void setMessageBodyMD5(String messageBodyMD5) {
            this.messageBodyMD5 = messageBodyMD5;
        }

        public Object getMessageTag() {
            return messageTag;
        }

        public void setMessageTag(Object messageTag) {
            this.messageTag = messageTag;
        }

        public Object getMessageBodyAsString() {
            return messageBodyAsString;
        }

        public void setMessageBodyAsString(Object messageBodyAsString) {
            this.messageBodyAsString = messageBodyAsString;
        }

        public Object getMessageBody() {
            return messageBody;
        }

        public void setMessageBody(Object messageBody) {
            this.messageBody = messageBody;
        }

        public Object getMessageBodyAsBytes() {
            return messageBodyAsBytes;
        }

        public void setMessageBodyAsBytes(Object messageBodyAsBytes) {
            this.messageBodyAsBytes = messageBodyAsBytes;
        }
    }
}
