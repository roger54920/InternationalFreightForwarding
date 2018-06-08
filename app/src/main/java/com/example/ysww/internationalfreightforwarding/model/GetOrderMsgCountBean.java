package com.example.ysww.internationalfreightforwarding.model;

import java.util.List;

/**
 * 获取营销员订单未读消息列表接口
 */

public class GetOrderMsgCountBean extends BaseBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * channelUserId : 30
         * msgNum : 1
         * orderDetailList : []
         * msgType : 0
         * orderId : 18060314554671058138
         * brand : 1111
         */

        private String channelUserId;
        private String msgNum;
        private String msgType;
        private String orderId;
        private String brand;
        private List<?> orderDetailList;

        public String getChannelUserId() {
            return channelUserId;
        }

        public void setChannelUserId(String channelUserId) {
            this.channelUserId = channelUserId;
        }

        public String getMsgNum() {
            return msgNum;
        }

        public void setMsgNum(String msgNum) {
            this.msgNum = msgNum;
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

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public List<?> getOrderDetailList() {
            return orderDetailList;
        }

        public void setOrderDetailList(List<?> orderDetailList) {
            this.orderDetailList = orderDetailList;
        }
    }
}
