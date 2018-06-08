package com.example.ysww.internationalfreightforwarding.model;

import java.util.List;

/**
 * 获取订单消息接口
 */

public class QueryMarketlMsgBean extends BaseBean{


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * companyCode : 3
         * msgType : 0
         * orderId : 2018060714292768149011
         * addrss : right
         * createDateStr : 2018-06-08 11:02:38
         * userName : 刘先生01
         * userId : 36
         * content : 土灰
         * createDate : 1528426958000
         */

        private String companyCode;
        private String msgType;
        private String orderId;
        private String addrss;
        private String createDateStr;
        private String userName;
        private int userId;
        private String content;
        private long createDate;

        public String getCompanyCode() {
            return companyCode;
        }

        public void setCompanyCode(String companyCode) {
            this.companyCode = companyCode;
        }

        public String getMsgType() {
            return msgType;
        }

        public void setMsgType(String msgType) {
            this.msgType = msgType;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getAddrss() {
            return addrss;
        }

        public void setAddrss(String addrss) {
            this.addrss = addrss;
        }

        public String getCreateDateStr() {
            return createDateStr;
        }

        public void setCreateDateStr(String createDateStr) {
            this.createDateStr = createDateStr;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }
    }
}
