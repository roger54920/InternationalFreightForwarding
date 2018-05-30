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
         * orderNo : 18042218023392178647
         * msgNum : 5
         */

        private String orderNo;
        private String msgNum;

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getMsgNum() {
            return msgNum;
        }

        public void setMsgNum(String msgNum) {
            this.msgNum = msgNum;
        }
    }
}
