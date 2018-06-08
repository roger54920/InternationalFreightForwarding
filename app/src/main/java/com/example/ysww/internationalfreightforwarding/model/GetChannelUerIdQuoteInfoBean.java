package com.example.ysww.internationalfreightforwarding.model;

/**
 * Created by ysww on 2018/6/8.
 */

public class GetChannelUerIdQuoteInfoBean extends BaseBean {

    /**
     * data : {"companyCode":"3","updateDate":1528009771000,"workday":1,"orderId":"18060314554671058138","additionalQuote":0,"fuelOilQuote":0,"remark":"111","quoteStatus":"10","createBy":"展示","channelUserId":30,"isFuelRate":"0","id":"0c0c2545164b4e1e8bc071a7e585f939","channelQuote":1,"isTax":"0","channelId":"2","createDate":1528009112000,"lastUpdateBy":"万渠道","status":"1"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * companyCode : 3
         * updateDate : 1528009771000
         * workday : 1
         * orderId : 18060314554671058138
         * additionalQuote : 0
         * fuelOilQuote : 0
         * remark : 111
         * quoteStatus : 10
         * createBy : 展示
         * channelUserId : 30
         * isFuelRate : 0
         * id : 0c0c2545164b4e1e8bc071a7e585f939
         * channelQuote : 1
         * isTax : 0
         * channelId : 2
         * createDate : 1528009112000
         * lastUpdateBy : 万渠道
         * status : 1
         */

        private String companyCode;
        private long updateDate;
        private int workday;
        private String orderId;
        private int additionalQuote;
        private int fuelOilQuote;
        private String remark;
        private String quoteStatus;
        private String createBy;
        private int channelUserId;
        private String isFuelRate;
        private String id;
        private double channelQuote;
        private String isTax;
        private String channelId;
        private long createDate;
        private String lastUpdateBy;
        private String status;
        private double fuelRate;

        public double getFuelRate() {
            return fuelRate;
        }

        public void setFuelRate(double fuelRate) {
            this.fuelRate = fuelRate;
        }

        public String getCompanyCode() {
            return companyCode;
        }

        public void setCompanyCode(String companyCode) {
            this.companyCode = companyCode;
        }

        public long getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(long updateDate) {
            this.updateDate = updateDate;
        }

        public int getWorkday() {
            return workday;
        }

        public void setWorkday(int workday) {
            this.workday = workday;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public int getAdditionalQuote() {
            return additionalQuote;
        }

        public void setAdditionalQuote(int additionalQuote) {
            this.additionalQuote = additionalQuote;
        }

        public int getFuelOilQuote() {
            return fuelOilQuote;
        }

        public void setFuelOilQuote(int fuelOilQuote) {
            this.fuelOilQuote = fuelOilQuote;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getQuoteStatus() {
            return quoteStatus;
        }

        public void setQuoteStatus(String quoteStatus) {
            this.quoteStatus = quoteStatus;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public int getChannelUserId() {
            return channelUserId;
        }

        public void setChannelUserId(int channelUserId) {
            this.channelUserId = channelUserId;
        }

        public String getIsFuelRate() {
            return isFuelRate;
        }

        public void setIsFuelRate(String isFuelRate) {
            this.isFuelRate = isFuelRate;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public double getChannelQuote() {
            return channelQuote;
        }

        public void setChannelQuote(double channelQuote) {
            this.channelQuote = channelQuote;
        }

        public String getIsTax() {
            return isTax;
        }

        public void setIsTax(String isTax) {
            this.isTax = isTax;
        }

        public String getChannelId() {
            return channelId;
        }

        public void setChannelId(String channelId) {
            this.channelId = channelId;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public String getLastUpdateBy() {
            return lastUpdateBy;
        }

        public void setLastUpdateBy(String lastUpdateBy) {
            this.lastUpdateBy = lastUpdateBy;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
