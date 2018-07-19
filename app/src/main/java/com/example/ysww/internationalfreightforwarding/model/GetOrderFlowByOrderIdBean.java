package com.example.ysww.internationalfreightforwarding.model;

import java.util.List;

/**
 * 通过订单获取物流信息接口
 */

public class GetOrderFlowByOrderIdBean extends BaseBean{

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * flowStation : 1
         * createBy : 万渠道
         * channelUserId : 30
         * orderId : 2018071180283
         * flowPlay : 1
         * flowRemark : 1
         * flowExplain : 1
         * id : 5d87c359fefb4cb1b851d51e72a508dd
         * flowDate : 2018-07-07
         * quoteId : baa5a160db47428f8d3e33f8a5f09ea4
         * createDate : 1531759262000
         * flowType : 1
         */

        private String flowStation;
        private String createBy;
        private String channelUserId;
        private String orderId;
        private String flowPlay;
        private String flowRemark;
        private String flowExplain;
        private String id;
        private String flowDate;
        private String quoteId;
        private long createDate;
        private String flowType;

        public String getFlowStation() {
            return flowStation;
        }

        public void setFlowStation(String flowStation) {
            this.flowStation = flowStation;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getChannelUserId() {
            return channelUserId;
        }

        public void setChannelUserId(String channelUserId) {
            this.channelUserId = channelUserId;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getFlowPlay() {
            return flowPlay;
        }

        public void setFlowPlay(String flowPlay) {
            this.flowPlay = flowPlay;
        }

        public String getFlowRemark() {
            return flowRemark;
        }

        public void setFlowRemark(String flowRemark) {
            this.flowRemark = flowRemark;
        }

        public String getFlowExplain() {
            return flowExplain;
        }

        public void setFlowExplain(String flowExplain) {
            this.flowExplain = flowExplain;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFlowDate() {
            return flowDate;
        }

        public void setFlowDate(String flowDate) {
            this.flowDate = flowDate;
        }

        public String getQuoteId() {
            return quoteId;
        }

        public void setQuoteId(String quoteId) {
            this.quoteId = quoteId;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public String getFlowType() {
            return flowType;
        }

        public void setFlowType(String flowType) {
            this.flowType = flowType;
        }
    }
}
