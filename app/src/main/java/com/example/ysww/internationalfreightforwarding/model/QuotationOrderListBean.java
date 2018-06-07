package com.example.ysww.internationalfreightforwarding.model;

import java.util.List;

/**
 * 报价订单 + 信息补录 + 历史订单 订单列表
 */

public class QuotationOrderListBean extends BaseBean{

    /**
     * page : {"totalCount":6,"pageSize":10,"totalPage":1,"currPage":1,"list":[{"orderNo":"18050117484262484944","marketUserId":29,"marketUserName":"小美","transType":"空运","transDemand":"门到门","payType":"到付","cleanCustomsType":"正式报关","sourceAddress":"4545","number":4545,"weight":45,"size":"45×45×45","export":"45","destinationCountry":"中国","departureDate":"2018-05-01 00:00:00.0","departureDateStr":null,"destinationCity":"4554","brand":"45","quote":45,"postcode":"4554","priceValue":"5445","transportationTime":"45","deliveryAddress":"45","forwardingUnit":"45","volumeSize":null,"packageType":null,"manufacturer":null,"contractNo":null,"licenseKey":null,"approvalNumber":null,"exemptionType":null,"tariffPayType":"","deliveryType":"","emsNo":null,"postingCharges":null,"postingTime":null,"billsUrl":null,"createDate":"2018-05-01 17:48:42","createBy":"展示","updateDate":"2018-05-02 00:41:05","lastUpdateBy":"展示","status":"1","orderStatus":"20","companyCode":"3","companyName":"印度分公司","channelName":null,"channelUserName":null,"quoteId":null,"channelQuoteId":null}]}
     */

    private PageBean page;

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public static class PageBean {
        /**
         * totalCount : 6
         * pageSize : 10
         * totalPage : 1
         * currPage : 1
         * list : [{"orderNo":"18050117484262484944","marketUserId":29,"marketUserName":"小美","transType":"空运","transDemand":"门到门","payType":"到付","cleanCustomsType":"正式报关","sourceAddress":"4545","number":4545,"weight":45,"size":"45×45×45","export":"45","destinationCountry":"中国","departureDate":"2018-05-01 00:00:00.0","departureDateStr":null,"destinationCity":"4554","brand":"45","quote":45,"postcode":"4554","priceValue":"5445","transportationTime":"45","deliveryAddress":"45","forwardingUnit":"45","volumeSize":null,"packageType":null,"manufacturer":null,"contractNo":null,"licenseKey":null,"approvalNumber":null,"exemptionType":null,"tariffPayType":"","deliveryType":"","emsNo":null,"postingCharges":null,"postingTime":null,"billsUrl":null,"createDate":"2018-05-01 17:48:42","createBy":"展示","updateDate":"2018-05-02 00:41:05","lastUpdateBy":"展示","status":"1","orderStatus":"20","companyCode":"3","companyName":"印度分公司","channelName":null,"channelUserName":null,"quoteId":null,"channelQuoteId":null}]
         */

        private int totalCount;
        private int pageSize;
        private int totalPage;
        private int currPage;
        private List<ListBean> list;

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getCurrPage() {
            return currPage;
        }

        public void setCurrPage(int currPage) {
            this.currPage = currPage;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * orderNo : 18050117484262484944
             * marketUserId : 29
             * marketUserName : 小美
             * transType : 空运
             * transDemand : 门到门
             * payType : 到付
             * cleanCustomsType : 正式报关
             * sourceAddress : 4545
             * number : 4545
             * weight : 45
             * size : 45×45×45
             * export : 45
             * destinationCountry : 中国
             * departureDate : 2018-05-01 00:00:00.0
             * departureDateStr : null
             * destinationCity : 4554
             * brand : 45
             * quote : 45
             * postcode : 4554
             * priceValue : 5445
             * transportationTime : 45
             * deliveryAddress : 45
             * forwardingUnit : 45
             * volumeSize : null
             * packageType : null
             * manufacturer : null
             * contractNo : null
             * licenseKey : null
             * approvalNumber : null
             * exemptionType : null
             * tariffPayType :
             * deliveryType :
             * emsNo : null
             * postingCharges : null
             * postingTime : null
             * billsUrl : null
             * createDate : 2018-05-01 17:48:42
             * createBy : 展示
             * updateDate : 2018-05-02 00:41:05
             * lastUpdateBy : 展示
             * status : 1
             * orderStatus : 20
             * companyCode : 3
             * companyName : 印度分公司
             * channelName : null
             * channelUserName : null
             * quoteId : null
             * channelQuoteId : null
             */

            private String orderId;
            private int marketUserId;
            private String marketUserName;
            private String transType;
            private String transDemand;
            private String payType;
            private String cleanCustomsType;
            private String sourceAddress;
            private int number;
            private int weight;
            private String size;
            private String export;
            private String destinationCountry;
            private String departureDate;
            private String departureDateStr;
            private String destinationCity;
            private String brand;
            private int quote;
            private String postcode;
            private String priceValue;
            private String transportationTime;
            private String deliveryAddress;
            private String forwardingUnit;
            private String volumeSize;
            private String packageType;
            private String manufacturer;
            private String contractNo;
            private String licenseKey;
            private String approvalNumber;
            private String exemptionType;
            private String tariffPayType;
            private String deliveryType;
            private String emsNo;
            private String postingCharges;
            private String postingTime;
            private String billsUrl;
            private String createDate;
            private String createBy;
            private String updateDate;
            private String lastUpdateBy;
            private String status;
            private String orderStatus;
            private String companyCode;
            private String companyName;
            private String channelName;
            private String channelUserName;
            private String quoteId;
            private String channelQuoteId;
            private String orderStatusName;
            private String channelUserId;

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
            }

            public String getChannelUserId() {
                return channelUserId;
            }

            public void setChannelUserId(String channelUserId) {
                this.channelUserId = channelUserId;
            }

            public String getOrderStatusName() {
                return orderStatusName;
            }

            public void setOrderStatusName(String orderStatusName) {
                this.orderStatusName = orderStatusName;
            }

            public String getorderId() {
                return orderId;
            }

            public void setorderId(String orderId) {
                this.orderId = orderId;
            }

            public int getMarketUserId() {
                return marketUserId;
            }

            public void setMarketUserId(int marketUserId) {
                this.marketUserId = marketUserId;
            }

            public String getMarketUserName() {
                return marketUserName;
            }

            public void setMarketUserName(String marketUserName) {
                this.marketUserName = marketUserName;
            }

            public String getTransType() {
                return transType;
            }

            public void setTransType(String transType) {
                this.transType = transType;
            }

            public String getTransDemand() {
                return transDemand;
            }

            public void setTransDemand(String transDemand) {
                this.transDemand = transDemand;
            }

            public String getPayType() {
                return payType;
            }

            public void setPayType(String payType) {
                this.payType = payType;
            }

            public String getCleanCustomsType() {
                return cleanCustomsType;
            }

            public void setCleanCustomsType(String cleanCustomsType) {
                this.cleanCustomsType = cleanCustomsType;
            }

            public String getSourceAddress() {
                return sourceAddress;
            }

            public void setSourceAddress(String sourceAddress) {
                this.sourceAddress = sourceAddress;
            }

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            public int getWeight() {
                return weight;
            }

            public void setWeight(int weight) {
                this.weight = weight;
            }

            public String getSize() {
                return size;
            }

            public void setSize(String size) {
                this.size = size;
            }

            public String getExport() {
                return export;
            }

            public void setExport(String export) {
                this.export = export;
            }

            public String getDestinationCountry() {
                return destinationCountry;
            }

            public void setDestinationCountry(String destinationCountry) {
                this.destinationCountry = destinationCountry;
            }

            public String getDepartureDate() {
                return departureDate;
            }

            public void setDepartureDate(String departureDate) {
                this.departureDate = departureDate;
            }

            public String getDepartureDateStr() {
                return departureDateStr;
            }

            public void setDepartureDateStr(String departureDateStr) {
                this.departureDateStr = departureDateStr;
            }

            public String getDestinationCity() {
                return destinationCity;
            }

            public void setDestinationCity(String destinationCity) {
                this.destinationCity = destinationCity;
            }

            public String getBrand() {
                return brand;
            }

            public void setBrand(String brand) {
                this.brand = brand;
            }

            public int getQuote() {
                return quote;
            }

            public void setQuote(int quote) {
                this.quote = quote;
            }

            public String getPostcode() {
                return postcode;
            }

            public void setPostcode(String postcode) {
                this.postcode = postcode;
            }

            public String getPriceValue() {
                return priceValue;
            }

            public void setPriceValue(String priceValue) {
                this.priceValue = priceValue;
            }

            public String getTransportationTime() {
                return transportationTime;
            }

            public void setTransportationTime(String transportationTime) {
                this.transportationTime = transportationTime;
            }

            public String getDeliveryAddress() {
                return deliveryAddress;
            }

            public void setDeliveryAddress(String deliveryAddress) {
                this.deliveryAddress = deliveryAddress;
            }

            public String getForwardingUnit() {
                return forwardingUnit;
            }

            public void setForwardingUnit(String forwardingUnit) {
                this.forwardingUnit = forwardingUnit;
            }

            public String getVolumeSize() {
                return volumeSize;
            }

            public void setVolumeSize(String volumeSize) {
                this.volumeSize = volumeSize;
            }

            public String getPackageType() {
                return packageType;
            }

            public void setPackageType(String packageType) {
                this.packageType = packageType;
            }

            public String getManufacturer() {
                return manufacturer;
            }

            public void setManufacturer(String manufacturer) {
                this.manufacturer = manufacturer;
            }

            public String getContractNo() {
                return contractNo;
            }

            public void setContractNo(String contractNo) {
                this.contractNo = contractNo;
            }

            public String getLicenseKey() {
                return licenseKey;
            }

            public void setLicenseKey(String licenseKey) {
                this.licenseKey = licenseKey;
            }

            public String getApprovalNumber() {
                return approvalNumber;
            }

            public void setApprovalNumber(String approvalNumber) {
                this.approvalNumber = approvalNumber;
            }

            public String getExemptionType() {
                return exemptionType;
            }

            public void setExemptionType(String exemptionType) {
                this.exemptionType = exemptionType;
            }

            public String getTariffPayType() {
                return tariffPayType;
            }

            public void setTariffPayType(String tariffPayType) {
                this.tariffPayType = tariffPayType;
            }

            public String getDeliveryType() {
                return deliveryType;
            }

            public void setDeliveryType(String deliveryType) {
                this.deliveryType = deliveryType;
            }

            public String getEmsNo() {
                return emsNo;
            }

            public void setEmsNo(String emsNo) {
                this.emsNo = emsNo;
            }

            public String getPostingCharges() {
                return postingCharges;
            }

            public void setPostingCharges(String postingCharges) {
                this.postingCharges = postingCharges;
            }

            public String getPostingTime() {
                return postingTime;
            }

            public void setPostingTime(String postingTime) {
                this.postingTime = postingTime;
            }

            public String getBillsUrl() {
                return billsUrl;
            }

            public void setBillsUrl(String billsUrl) {
                this.billsUrl = billsUrl;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public String getCreateBy() {
                return createBy;
            }

            public void setCreateBy(String createBy) {
                this.createBy = createBy;
            }

            public String getUpdateDate() {
                return updateDate;
            }

            public void setUpdateDate(String updateDate) {
                this.updateDate = updateDate;
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

            public String getOrderStatus() {
                return orderStatus;
            }

            public void setOrderStatus(String orderStatus) {
                this.orderStatus = orderStatus;
            }

            public String getCompanyCode() {
                return companyCode;
            }

            public void setCompanyCode(String companyCode) {
                this.companyCode = companyCode;
            }

            public String getCompanyName() {
                return companyName;
            }

            public void setCompanyName(String companyName) {
                this.companyName = companyName;
            }

            public String getChannelName() {
                return channelName;
            }

            public void setChannelName(String channelName) {
                this.channelName = channelName;
            }

            public String getChannelUserName() {
                return channelUserName;
            }

            public void setChannelUserName(String channelUserName) {
                this.channelUserName = channelUserName;
            }

            public String getQuoteId() {
                return quoteId;
            }

            public void setQuoteId(String quoteId) {
                this.quoteId = quoteId;
            }

            public String getChannelQuoteId() {
                return channelQuoteId;
            }

            public void setChannelQuoteId(String channelQuoteId) {
                this.channelQuoteId = channelQuoteId;
            }
        }
    }
}
